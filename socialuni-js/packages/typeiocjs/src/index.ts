import type {Plugin} from 'vite';
import ts, {isMethodDeclaration, SyntaxKind} from "typescript";
import {parse as parseSfc, SFCBlock} from '@vue/compiler-sfc';
import {TypeIocBean, TypeIocResource, TypeIocService} from "./TypeIocDecorator";
import path from 'node:path';
import {tryNodeResolve} from "./node/plugins/resolve";
import {DEFAULT_EXTENSIONS} from "./node/constants";

function toPosixPath(inputPath) {
    // 使用 path.normalize 将路径标准化（处理 . 和 .. 等）
    let normalizedPath = path.normalize(inputPath);
    // 将 Windows 风格的反斜杠 \ 替换为 POSIX 风格的斜杠 /
    return normalizedPath.split(path.sep).join(path.posix.sep);
}

// 自定义装饰器名称
// 检查节点是否是带有指定装饰器的类
function getDecoratorIndex(node: ts.ClassDeclaration, decoratorName: string): boolean {
    if (!node.modifiers) {
        return -1;
    }
    const modifiers = node.modifiers;
    const decoratorIndex = modifiers.findIndex(decorator => {
        const decoratorExpression = decorator.expression as ts.CallExpression;
        return decoratorExpression?.text === decoratorName;
    });
    return decoratorIndex;
}

function addMetadataTransformer(sourceFile, id, packageCache) {
    // 自定义转换器，用于在所有类上添加 @Reflect.metadata
    return (context: ts.TransformationContext) => {
        function visit(node: ts.Node): ts.Node {
            try {
                if (ts.isClassDeclaration(node)) {
                    let decoratorIndex = getDecoratorIndex(node, TypeIocService.name);
                    if (decoratorIndex < 0) {
                        return node;
                    }
                    // 定义类名 + 全路径类名的map，模仿java的包名
                    const classAllPathMap: Map<string, string> = new Map();

                    function setClassAllPathMap(className, fullPath) {
                        classAllPathMap.set(className, toPosixPath(fullPath));
                    }

                    //查看类的信息
                    //有实现接口，则设置接口信息
                    //设置类型信息
                    const className = node.name.text;
                    const setFullPath = id + '$$' + className;
                    setClassAllPathMap(className, setFullPath);
                    const decoratorAry = [className];
                    // 遍历导入声明
                    sourceFile.statements.forEach(statement => {
                        if (ts.isInterfaceDeclaration(statement)) {
                            const interfaceName = statement.name.text;
                            const interfaceNameFullPath = id + '$$' + interfaceName;
                            setClassAllPathMap(interfaceName, interfaceNameFullPath);
                        } else if (ts.isClassDeclaration(statement)) {
                            const declClassName = statement.name.text;
                            const declClassNameFullPath = id + '$$' + declClassName;
                            setClassAllPathMap(declClassName, declClassNameFullPath);
                        } else if (ts.isImportDeclaration(statement)) {
                            const filePath = (statement.moduleSpecifier as ts.StringLiteral).text;
                            //判断是否 ./开始 ，判断
                            let fileFullPath;
                            //为相对路径
                            const directoryPath = path.dirname(id);

                            console.log('zhixingle')
                            const filsadf = tryNodeResolve(
                                filePath,
                                id,
                                {
                                    root: directoryPath,
                                    isBuild: false,
                                    isProduction: false,
                                    preferRelative: false,
                                    tryIndex: true,
                                    mainFields: [],
                                    conditions: [],
                                    overrideConditions: ['node'],
                                    dedupe: [],
                                    extensions: DEFAULT_EXTENSIONS,
                                    preserveSymlinks: false,
                                    packageCache,
                                    isRequire: false,
                                },
                                false,
                            )

                            console.log(filsadf?.id)
                            if (filePath.startsWith('\.')) {
                                fileFullPath = path.resolve(directoryPath, filePath);
                            } else {
                                fileFullPath = path.resolve(directoryPath, filePath);
                            }
                            const importClause = statement.importClause;
                            if (!importClause) {
                                throw new Error('typeiocjsError, unexpected exception');
                            }
                            if (importClause.namedBindings) {
                                const importNames = importClause.namedBindings.elements;
                                if (importNames) {
                                    for (const importName of importNames) {
                                        if (importName.name) {
                                            const importNameText = importName.name.text;
                                            const importNameTextFullPath = fileFullPath + '$$' + importNameText;
                                            setClassAllPathMap(importNameText, importNameTextFullPath);
                                        }
                                    }
                                }
                            } else {
                                const importNameText = importClause.name.text;
                                const importNameTextFullPath = fileFullPath + '$$' + importNameText;
                                setClassAllPathMap(importNameText, importNameTextFullPath);
                            }
                        }
                    });

                    let members = [];
                    //设置属性信息
                    node.members.forEach((member) => {
                        let newMember = member;
                        if (ts.isPropertyDeclaration(member)) {
                            let resourceDecoratorIndex = getDecoratorIndex(member, TypeIocResource.name);
                            if (resourceDecoratorIndex > -1) {
                                const memTypeName = member.type?.typeName?.text;
                                if (memTypeName) {
                                    const fullPath = classAllPathMap.get(memTypeName);
                                    const newResourceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(TypeIocResource.name), undefined, [
                                        ts.factory.createStringLiteral(fullPath)
                                    ]));
                                    let resourceModifiers = member.modifiers;
                                    //没有splice方法，
                                    const filterModifiers = resourceModifiers.filter((item, index) => index !== resourceDecoratorIndex);
                                    const filterDecoratorModifiers = filterModifiers.filter((item, index) => item.kind === ts.SyntaxKind.Decorator);
                                    const filterNotDecoratorModifiers = filterModifiers.filter((item, index) => item.kind !== ts.SyntaxKind.Decorator);
                                    const newModifiers = member.modifiers ? [newResourceDecorator, ...filterModifiers] : [newResourceDecorator];
                                    newMember = ts.factory.updatePropertyDeclaration(member, newModifiers, // 修饰符，添加新的装饰器
                                        member.name, member.questionToken, member.type, member.initializer);
                                }
                            }
                        } else if (ts.isMethodDeclaration(member)) {
                            let beanDecoratorIndex = getDecoratorIndex(member, TypeIocBean.name);
                            if (beanDecoratorIndex > -1) {
                                const memTypeName = member.type?.typeName?.text;
                                if (memTypeName) {
                                    const fullPath = classAllPathMap.get(memTypeName);
                                    const newBeanDecorator = ts.factory.createDecorator(
                                        ts.factory.createCallExpression(
                                            ts.factory.createIdentifier(TypeIocBean.name),
                                            undefined,
                                            [
                                                ts.factory.createStringLiteral(fullPath)
                                            ]));
                                    let resourceModifiers = member.modifiers;
                                    //没有splice方法，
                                    const filterModifiers = resourceModifiers.filter((item, index) => index !== beanDecoratorIndex);
                                    const newModifiers = member.modifiers ? [newBeanDecorator, ...filterModifiers] : [newBeanDecorator];
                                    newMember = ts.factory.updateMethodDeclaration(member, newModifiers, // 修饰符，添加新的装饰器
                                        member.asteriskToken, member.name, member.questionToken, member.typeParameters, member.parameters, member.type, member.body);
                                }
                            }
                        }
                        members.push(newMember);
                    });
                    if (node.heritageClauses) {
                        const implementedInterfaces: string = [];
                        // 访问类的继承
                        node.heritageClauses.forEach(heritageClause => {
                            if (heritageClause.token === SyntaxKind.ImplementsKeyword) {
                                heritageClause.types.forEach(type => {
                                    if (ts.isExpressionWithTypeArguments(type)) {
                                        const typeName = type.expression.text;
                                        implementedInterfaces.push(typeName);
                                    }
                                });
                            }
                        });
                        if (implementedInterfaces.length) {
                            decoratorAry.push(...implementedInterfaces);
                        }
                    }
                    let newServiceDecorator;
                    if (decoratorAry.length > 1) {
                        newServiceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(TypeIocService.name), undefined, [
                            ts.factory.createArrayLiteralExpression(decoratorAry.map(name => {
                                const fullPath = classAllPathMap.get(name);
                                return ts.factory.createStringLiteral(fullPath);
                            }))
                        ]));
                    } else {
                        const fullPath = classAllPathMap.get(decoratorAry[0]);
                        newServiceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(TypeIocService.name), undefined, [
                            ts.factory.createStringLiteral(fullPath)
                        ]));
                    }
                    let modifiers = node.modifiers;
                    //没有splice方法，
                    const filterModifiers = modifiers.filter((item, index) => index !== decoratorIndex);
                    const decorators = filterModifiers.filter(item => item.kind === ts.SyntaxKind.Decorator);
                    const notDecorators = filterModifiers.filter(item => item.kind !== ts.SyntaxKind.Decorator);
                    modifiers = modifiers ? [...decorators, newServiceDecorator, ...notDecorators] : [newServiceDecorator];
                    return ts.factory.updateClassDeclaration(node, modifiers, // 修饰符，添加新的装饰器
                        node.name, // 类名
                        node.typeParameters, // 类型参数
                        node.heritageClauses, // 继承子句
                        members // 类成员
                    );
                }
            } catch (e) {
                console.error(e);
                throw new Error(e);
            }
            // 递归访问子节点
            return ts.visitEachChild(node, visit, context);
        }

        return (node: ts.Node) => ts.visitNode(node, visit);
    };
}

// Vite 插件
export default function typeIocJsPlugin(): Plugin {
    return {
        name: 'vite-plugin-typeiocjs',
        enforce: 'pre',
        transform(code, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                let content;
                if (/.ts$|.tsx$/.test(id)) {
                    content = code;
                } else if (/.vue$/.test(id)) {
                    const sfcDescriptor = parseSfc(code);
                    content = sfcDescriptor.descriptor.script.content;
                }
                const printer: ts.Printer = ts.createPrinter();
                // const sourceFile = ts.createSourceFile(id, content, ts.ScriptTarget.Latest, true);
                const sourceFile = ts.createSourceFile(id, content, ts.ScriptTarget.Latest);
                const packageCache = new Map()
                const result = ts.transform(sourceFile, [addMetadataTransformer(sourceFile, id, packageCache)]);
                const transformedSourceFile: ts.SourceFile = result.transformed[0];
                let str = printer.printFile(transformedSourceFile);
                /* if (!str.includes('reflect-metadata')) {
                     str = "\nimport 'reflect-metadata';\n" + str
                 }*/
                const newCode = code.replace(content, str);
                return newCode;
            }
        }
    };
}


export const typeIocJs = {
    async install(app: App) {
        // const __filename = fileURLToPath(import.meta.url);
        // const __dirname = dirname(__filename);
        console.log(1111)
        console.log(import.meta.url)
        // const modules = import.meta.glob('./**/*.ts', {eager: true});
    }
}

export const loadSrcAllTs = () => {
    return import.meta.glob('./**/*.ts', {eager: true});
};
