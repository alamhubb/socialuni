import type {Plugin} from 'vite';
import ts, {
    ExclamationToken, Expression,
    ModifierLike,
    PropertyDeclaration,
    PropertyName,
    QuestionToken,
    SyntaxKind, TypeNode
} from "typescript";
import {Resource, Service, TypeIocContainer} from '../src/plugins/TypeIoc/TypeIocContainer'
import {parse as parseSfc, SFCBlock} from '@vue/compiler-sfc';
// 自定义装饰器名称

// 检查节点是否是带有指定装饰器的类
function hasDecorator(node: ts.ClassDeclaration, decoratorName: string): boolean {
    return node.modifiers
        ? node.modifiers.some(decorator => {
            const decoratorExpression = decorator.expression as ts.CallExpression;
            return decoratorExpression?.text === decoratorName;
        })
        : false;
}


// 自定义转换器，用于在所有类上添加 @Reflect.metadata
function addMetadataTransformer(context: ts.TransformationContext) {
    function visit(node: ts.Node): ts.Node {
        if (ts.isClassDeclaration(node)) {
            if (hasDecorator(node, Service.name)) {
                let members = []
                let modifiers = node.modifiers
                //设置属性信息
                node.members.forEach((member) => {
                    let newMember = member
                    if (ts.isPropertyDeclaration(member)) {
                        if (hasDecorator(member, Resource.name)) {
                            if (member.type?.typeName?.text) {
                                console.log(member.type.typeName.text);
                                const decorator = ts.factory.createDecorator(
                                    ts.factory.createCallExpression(
                                        ts.factory.createIdentifier('Reflect.metadata'),
                                        undefined,
                                        [
                                            ts.factory.createStringLiteral(TypeIocContainer.propertyResourceKey),
                                            ts.factory.createStringLiteral(member.type?.typeName?.text)
                                        ]
                                    )
                                );
                                const newModifiers = member.modifiers ? [...member.modifiers, decorator] : [decorator]
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

                //有实现接口，则设置接口信息
                if (node.heritageClauses) {
                    const implementedInterfaces: string = []
                    // 访问类的继承
                    node.heritageClauses.forEach(heritageClause => {
                        heritageClause.types.forEach(type => {
                            if (ts.isExpressionWithTypeArguments(type)) {
                                const typeName = type.expression.text;
                                implementedInterfaces.push(typeName);
                            }
                        });
                    });
                    if (implementedInterfaces.length) {
                        const implementedInterface = implementedInterfaces[0]
                        const decorator = ts.factory.createDecorator(
                            ts.factory.createCallExpression(
                                ts.factory.createIdentifier('Reflect.metadata'),
                                undefined,
                                [
                                    ts.factory.createStringLiteral(TypeIocContainer.interfaceResourceKey),
                                    ts.factory.createStringLiteral(implementedInterface)
                                ]
                            )
                        );
                        const decorators = modifiers.filter(item => item.kind === ts.SyntaxKind.Decorator)
                        const notDecorators = modifiers.filter(item => item.kind !== ts.SyntaxKind.Decorator)
                        modifiers = modifiers ? [...decorators, decorator, ...notDecorators] : [decorator]
                    }
                }
                //设置类型信息
                const className = node.name.text
                const decorator = ts.factory.createDecorator(
                    ts.factory.createCallExpression(
                        ts.factory.createIdentifier('Reflect.metadata'),
                        undefined,
                        [
                            ts.factory.createStringLiteral(TypeIocContainer.classResourceKey),
                            ts.factory.createStringLiteral(className)
                        ]
                    )
                );
                const decorators = modifiers.filter(item => item.kind === ts.SyntaxKind.Decorator)
                const notDecorators = modifiers.filter(item => item.kind !== ts.SyntaxKind.Decorator)
                modifiers = modifiers ? [...decorators, decorator, ...notDecorators] : [decorator]

                return ts.factory.updateClassDeclaration(
                    node,
                    modifiers, // 修饰符，添加新的装饰器
                    node.name,                     // 类名
                    node.typeParameters,           // 类型参数
                    node.heritageClauses,          // 继承子句
                    members                   // 类成员
                )
            }
        }
        // 递归访问子节点
        return ts.visitEachChild(node, visit, context);
    }

    return (node: ts.Node) => ts.visitNode(node, visit);
}

// Vite 插件
export default function addReflectMetadataPlugin(): Plugin {
    return {
        name: 'vite-plugin-add-reflect-metadata',
        enforce: 'pre',
        transform(code, id) {
            // if (!id.includes('TestSerivce')) return
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // console.log(id)
                let content
                if (/.ts$|.tsx$/.test(id)) {
                    content = code
                } else if (/.vue$/.test(id)) {
                    const sfcDescriptor = parseSfc(code);
                    content = sfcDescriptor.descriptor.script.content
                }
                const printer: ts.Printer = ts.createPrinter();
                const sourceFile = ts.createSourceFile(id, content, ts.ScriptTarget.Latest);

                const result = ts.transform(sourceFile, [addMetadataTransformer])

                const transformedSourceFile: ts.SourceFile = result.transformed[0];

                let str = printer.printFile(transformedSourceFile)
                if (!str.includes('reflect-metadata')) {
                    str = "\nimport 'reflect-metadata';\n" + str
                }

                const newCode = code.replace(content, str)

                // console.log(newCode)
                // return newCode
            }
        }
    };
}
