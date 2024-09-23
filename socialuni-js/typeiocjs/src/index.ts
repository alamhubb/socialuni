import type {Plugin} from 'vite';
import ts, {SyntaxKind} from "typescript";
import {parse as parseSfc, SFCBlock} from '@vue/compiler-sfc';
import {Resource, Service} from "./TypeIocDecorator";
import path from 'node:path';

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

function addMetadataTransformer(sourceFile, id) {
    // 自定义转换器，用于在所有类上添加 @Reflect.metadata
    return (context: ts.TransformationContext) => {
        function visit(node: ts.Node): ts.Node {
            try {
                if (ts.isClassDeclaration(node)) {
                    let decoratorIndex = getDecoratorIndex(node, Service.name);
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
                            if (filePath.startsWith('\.')) {
                                const directoryPath = path.dirname(id);
                                fileFullPath = path.resolve(directoryPath, filePath);
                            } else {
                                fileFullPath = filePath;
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
                            let resourceDecoratorIndex = getDecoratorIndex(member, Resource.name);
                            if (resourceDecoratorIndex > -1) {
                                const memTypeName = member.type?.typeName?.text;
                                if (memTypeName) {
                                    const fullPath = classAllPathMap.get(memTypeName);
                                    const newResourceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(Resource.name), undefined, [
                                        ts.factory.createStringLiteral(fullPath)
                                    ]));
                                    let resourceModifiers = member.modifiers;
                                    //没有splice方法，
                                    const filterModifiers = resourceModifiers.filter((item, index) => index !== resourceDecoratorIndex);
                                    const newModifiers = member.modifiers ? [...filterModifiers, newResourceDecorator] : [newResourceDecorator];
                                    newMember = ts.factory.updatePropertyDeclaration(member, newModifiers, // 修饰符，添加新的装饰器
                                        member.name, member.questionToken, member.type, member.initializer);
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
                        newServiceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(Service.name), undefined, [
                            ts.factory.createArrayLiteralExpression(decoratorAry.map(name => {
                                const fullPath = classAllPathMap.get(name);
                                return ts.factory.createStringLiteral(fullPath);
                            }))
                        ]));
                    } else {
                        const fullPath = classAllPathMap.get(decoratorAry[0]);
                        newServiceDecorator = ts.factory.createDecorator(ts.factory.createCallExpression(ts.factory.createIdentifier(Service.name), undefined, [
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
                console.log(id)
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
                const result = ts.transform(sourceFile, [addMetadataTransformer(sourceFile, id)]);
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


// export const typeIocJs = {
//     async install(app: App, componentScan: string) {
//         const modules = import.meta.glob(componentScan);
//         console.log(modules)
//         /*for (const module in modules) {
//             console.log('jiazai:', module)
//         }*/
//     }
// }
