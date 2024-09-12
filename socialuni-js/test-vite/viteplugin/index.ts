import type {Plugin} from 'vite';
import ts from "typescript";
import TypeIocContainer from '../src/plugins/TypeIoc/TypeIocContainer'

// 自定义装饰器名称
const DECORATOR_NAME = 'Service';

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
            if (hasDecorator(node, DECORATOR_NAME)) {
                const implementedInterfaces: string = []
                // 访问类的继承
                node.heritageClauses.forEach(heritageClause => {
                    // console.log(heritageClause);
                    // console.log(`Heritage Clause Token: ${ts.SyntaxKind[heritageClause.token]}`);
                    heritageClause.types.forEach(type => {
                        if (ts.isExpressionWithTypeArguments(type)) {
                            const typeName = type.expression.text;
                            implementedInterfaces.push(typeName);
                        }
                    });
                });
                console.log(implementedInterfaces)
                if (implementedInterfaces.length) {
                    const implementedInterface = implementedInterfaces[0]
                    const decorator = ts.factory.createDecorator(
                        ts.factory.createCallExpression(
                            ts.factory.createIdentifier('Reflect.metadata'),
                            undefined,
                            [
                                ts.factory.createStringLiteral(TypeIocContainer.classInterfaceKey),
                                ts.factory.createStringLiteral(implementedInterface)
                            ]
                        )
                    );
                    return ts.factory.updateClassDeclaration(
                        node,
                        node.modifiers ? [decorator, ...node.modifiers] : [decorator], // 修饰符，添加新的装饰器
                        node.name,                     // 类名
                        node.typeParameters,           // 类型参数
                        node.heritageClauses,          // 继承子句
                        node.members                   // 类成员
                    )
                }
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
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // 创建 TypeScript 代码转换器
                if (id.includes('Testaa')) {
                    console.log(id)
                    // console.log(ts.version)
                    const printer: ts.Printer = ts.createPrinter();
                    const sourceFile = ts.createSourceFile(id, code, ts.ScriptTarget.Latest);

                    const result = ts.transform(sourceFile, [addMetadataTransformer])

                    const transformedSourceFile: ts.SourceFile = result.transformed[0];

                    let str = printer.printFile(transformedSourceFile)
                    if (!str.includes('reflect-metadata')) {
                        str = "import 'reflect-metadata';\n" + str
                    }

                    console.log(str);
                    console.log(123123)
                    return str
                }
            }
        }
    };
}
