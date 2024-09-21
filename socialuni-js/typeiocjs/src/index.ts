import type {Plugin} from 'vite';
import ts, {SyntaxKind} from "typescript";
import {parse as parseSfc, SFCBlock} from '@vue/compiler-sfc';
import {Resource, Service} from "./TypeIocDecorator";
import path from 'node:path';
// 自定义装饰器名称

// 检查节点是否是带有指定装饰器的类
function getDecoratorIndex(node: ts.ClassDeclaration, decoratorName: string): boolean {
    if (!node.modifiers) {
        return -1
    }
    const modifiers = node.modifiers
    const decoratorIndex = modifiers.findIndex(decorator => {
        const decoratorExpression = decorator.expression as ts.CallExpression;
        return decoratorExpression?.text === decoratorName;
    })
    return decoratorIndex
}


function addMetadataTransformer(sourceFile, id) {
    // 自定义转换器，用于在所有类上添加 @Reflect.metadata
    return (context: ts.TransformationContext) => {
        function visit(node: ts.Node): ts.Node {
            if (ts.isClassDeclaration(node)) {
                let decoratorIndex = getDecoratorIndex(node, Service.name)
                if (decoratorIndex < 0) {
                    return node
                }

                // 定义类名 + 全路径类名的map，模仿java的包名
                const classAllPathMap: Map<string, string> = new Map()
                // 遍历导入声明
                sourceFile.statements.forEach(statement => {
                    if (ts.isImportDeclaration(statement)) {
                        const filePath = (statement.moduleSpecifier as ts.StringLiteral).text;

                        //判断是否 ./开始 ，判断
                        let fileFullPath
                        //为相对路径
                        if (filePath.startsWith('\.')) {
                            const directoryPath = path.dirname(id);
                            fileFullPath = path.resolve(directoryPath, filePath);
                        } else {
                            fileFullPath = filePath
                        }
                        const importClause = statement.importClause
                        if (!importClause) {
                            throw new Error('typeiocjsError, unexpected exception')
                        }
                        if (importClause.namedBindings) {
                            const importNames = importClause.namedBindings.elements
                            if (importNames) {
                                for (const importName of importNames) {
                                    if (importName.name) {
                                        let importNameText = importName.name.text
                                        classAllPathMap.set(importNameText, fileFullPath + '$$' + importNameText)
                                    }
                                }
                            }
                        } else {
                            let importNameText = importClause.name.text
                            classAllPathMap.set(importNameText, fileFullPath + '$$' + importNameText)
                        }
                    }
                });

                let members = []
                //设置属性信息
                node.members.forEach((member) => {
                    let newMember = member
                    if (ts.isPropertyDeclaration(member)) {
                        let resourceDecoratorIndex = getDecoratorIndex(member, Resource.name)
                        if (resourceDecoratorIndex > -1) {
                            if (member.type?.typeName?.text) {
                                const newResourceDecorator = ts.factory.createDecorator(
                                    ts.factory.createCallExpression(
                                        ts.factory.createIdentifier(Resource.name),
                                        undefined,
                                        [
                                            ts.factory.createStringLiteral(classAllPathMap.get(member.type?.typeName?.text))
                                        ]
                                    )
                                );
                                let resourceModifiers = member.modifiers
                                //没有splice方法，
                                const filterModifiers = resourceModifiers.filter((item, index) => index !== resourceDecoratorIndex)
                                const newModifiers = member.modifiers ? [...filterModifiers, newResourceDecorator] : [newResourceDecorator]
                                newMember = ts.factory.updatePropertyDeclaration(
                                    member,
                                    newModifiers, // 修饰符，添加新的装饰器
                                    member.name,
                                    member.questionToken,
                                    member.type,
                                    member.initializer
                                );
                            }
                        }
                    }
                    members.push(newMember)
                })


                //查看类的信息
                //有实现接口，则设置接口信息
                //设置类型信息
                const className = node.name.text
                const decoratorAry = [className]


                if (node.heritageClauses) {
                    const implementedInterfaces: string = []
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
                        decoratorAry.push(...implementedInterfaces)
                    }
                }

                let newServiceDecorator
                if (decoratorAry.length > 1) {
                    newServiceDecorator = ts.factory.createDecorator(
                        ts.factory.createCallExpression(
                            ts.factory.createIdentifier(Service.name),
                            undefined,
                            [
                                ts.factory.createArrayLiteralExpression(decoratorAry.map(name => ts.factory.createStringLiteral(classAllPathMap.get(name))))
                            ]
                        )
                    );
                } else {
                    newServiceDecorator = ts.factory.createDecorator(
                        ts.factory.createCallExpression(
                            ts.factory.createIdentifier(Service.name),
                            undefined,
                            [
                                ts.factory.createStringLiteral(classAllPathMap.get(decoratorAry[0]))
                            ]
                        )
                    );
                }

                let modifiers = node.modifiers
                //没有splice方法，
                const filterModifiers = modifiers.filter((item, index) => index !== decoratorIndex)
                const decorators = filterModifiers.filter(item => item.kind === ts.SyntaxKind.Decorator)
                const notDecorators = filterModifiers.filter(item => item.kind !== ts.SyntaxKind.Decorator)
                modifiers = modifiers ? [...decorators, newServiceDecorator, ...notDecorators] : [newServiceDecorator]

                return ts.factory.updateClassDeclaration(
                    node,
                    modifiers, // 修饰符，添加新的装饰器
                    node.name,                     // 类名
                    node.typeParameters,           // 类型参数
                    node.heritageClauses,          // 继承子句
                    members                   // 类成员
                )
            }
            // 递归访问子节点
            return ts.visitEachChild(node, visit, context);
        }


        return (node: ts.Node) => ts.visitNode(node, visit);
    }
}


// Vite 插件
export default function typeIocJsPlugin(): Plugin {
    return {
        name: 'vite-plugin-typeiocjs',
        enforce: 'pre',
        transform(code, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                let content
                if (/.ts$|.tsx$/.test(id)) {
                    content = code
                } else if (/.vue$/.test(id)) {
                    const sfcDescriptor = parseSfc(code);
                    content = sfcDescriptor.descriptor.script.content
                }
                const printer: ts.Printer = ts.createPrinter();
                // const sourceFile = ts.createSourceFile(id, content, ts.ScriptTarget.Latest, true);
                const sourceFile = ts.createSourceFile(id, content, ts.ScriptTarget.Latest);

                const result = ts.transform(sourceFile, [addMetadataTransformer(sourceFile, id)])

                const transformedSourceFile: ts.SourceFile = result.transformed[0];

                let str = printer.printFile(transformedSourceFile)
                /* if (!str.includes('reflect-metadata')) {
                     str = "\nimport 'reflect-metadata';\n" + str
                 }*/

                const newCode = code.replace(content, str)

                return newCode
            }
        }
    };
}
