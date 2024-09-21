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
            try {
                if (ts.isClassDeclaration(node)) {
                    let decoratorIndex = getDecoratorIndex(node, Service.name)
                    if (decoratorIndex < 0) {
                        return node
                    }


                    // 定义类名 + 全路径类名的map，模仿java的包名
                    const classAllPathMap: Map<string, string> = new Map()

                    //查看类的信息
                    //有实现接口，则设置接口信息
                    //设置类型信息
                    const className = node.name.text
                    classAllPathMap.set(className, id + '$$' + className)

                    console.log(className)

                    const decoratorAry = [className]
                    // 遍历导入声明
                    sourceFile.statements.forEach(statement => {
                        if (ts.isInterfaceDeclaration(statement)) {
                            const interfaceName = statement.name.text
                            classAllPathMap.set(interfaceName, id + '$$' + interfaceName)
                        } else if (ts.isClassDeclaration(statement)) {
                            const declClassName = statement.name.text
                            classAllPathMap.set(declClassName, id + '$$' + declClassName)
                        } else if (ts.isImportDeclaration(statement)) {
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
                            console.log(111222)
                            if (importClause.namedBindings) {
                                console.log(1112333)
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
                                console.log(11123444)
                                let importNameText = importClause.name.text
                                classAllPathMap.set(importNameText, fileFullPath + '$$' + importNameText)
                            }
                        }
                    });

                    console.log(222222)
                    let members = []
                    //设置属性信息
                    node.members.forEach((member) => {
                        let newMember = member
                        if (ts.isPropertyDeclaration(member)) {
                            let resourceDecoratorIndex = getDecoratorIndex(member, Resource.name)
                            if (resourceDecoratorIndex > -1) {
                                const memTypeName = member.type?.typeName?.text
                                if (memTypeName) {
                                    console.log(233333)
                                    const fullPath = classAllPathMap.get(memTypeName)
                                    console.log(fullPath)
                                    const newResourceDecorator = ts.factory.createDecorator(
                                        ts.factory.createCallExpression(
                                            ts.factory.createIdentifier(Resource.name),
                                            undefined,
                                            [
                                                ts.factory.createStringLiteral(fullPath)
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

                    console.log(333333)

                    console.log(4444)
                    if (node.heritageClauses) {
                        console.log(5555)
                        const implementedInterfaces: string = []
                        // 访问类的继承
                        node.heritageClauses.forEach(heritageClause => {
                            console.log(55577)
                            if (heritageClause.token === SyntaxKind.ImplementsKeyword) {
                                console.log(5555888)
                                heritageClause.types.forEach(type => {
                                    if (ts.isExpressionWithTypeArguments(type)) {
                                        console.log(66666)
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
                    console.log(77777)
                    let newServiceDecorator
                    if (decoratorAry.length > 1) {
                        console.log(8888)

                        newServiceDecorator = ts.factory.createDecorator(
                            ts.factory.createCallExpression(
                                ts.factory.createIdentifier(Service.name),
                                undefined,
                                [
                                    ts.factory.createArrayLiteralExpression(decoratorAry.map(name => {
                                        const fullPath = classAllPathMap.get(name)
                                        console.log(898989)
                                        console.log(name)
                                        console.log(fullPath)
                                        console.log(classAllPathMap)
                                        return ts.factory.createStringLiteral(fullPath)
                                    }))
                                ]
                            )
                        );
                    } else {
                        console.log(88999)
                        console.log(classAllPathMap)
                        console.log(decoratorAry[0])
                        const fullPath = classAllPathMap.get(decoratorAry[0])
                        console.log(fullPath)

                        newServiceDecorator = ts.factory.createDecorator(
                            ts.factory.createCallExpression(
                                ts.factory.createIdentifier(Service.name),
                                undefined,
                                [
                                    ts.factory.createStringLiteral(fullPath)
                                ]
                            )
                        );
                    }
                    console.log(classAllPathMap)
                    console.log(99999)

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
            } catch (e) {
                console.error(e)
                throw new Error(e)
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
