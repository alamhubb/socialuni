import type {Plugin} from 'vite';
import ts from "typescript";

/*function addMetadataTransformer(context: ts.TransformationContext) {
    function visit(node: ts.Node): ts.Node {
        if (ts.isClassDeclaration(node)) {
            console.log(node)
            /!*const className = node.name?.text;
            console.log(className)
            for (const member of node.members) {
                // console.log(member)
                console.log(member.type)
                console.log(member.kind)
                if (ts.isTypeReferenceNode(member)){
                    console.log('zhaodao yin yong')
                }
            }*!/
            /!*const className = node.name?.text;


            // 查找实现的接口
            node.heritages.forEach(heritage => {
                if (ts.isExpressionWithTypeArguments(heritage.expression)) {
                    const typeName = heritage.expression.getText();
                    implementedInterfaces.push(typeName);
                }
            });

            // 如果实现了接口，添加 Reflect.metadata 装饰器
            if (implementedInterfaces.length > 0) {
                const decorator = ts.factory.createDecorator(
                    ts.factory.createCallExpression(
                        ts.factory.createIdentifier('Reflect.metadata'),
                        undefined,
                        [
                            ts.factory.createStringLiteral('a9360b695cff4e40aa417121d9b004a7'),
                            ts.factory.createArrayLiteralExpression(
                                implementedInterfaces.map(iface => ts.factory.createStringLiteral(iface))
                            ),
                        ]
                    )
                );

                return ts.factory.updateClassDeclaration(
                    node,
                    node.decorators ? [decorator, ...node.decorators] : [decorator],
                    node.modifiers,
                    node.name,
                    node.typeParameters,
                    node.heritages,
                    node.members
                );
            }*!/
        }

        return ts.visitEachChild(node, visit, context);
    }

    return (node: ts.Node) => ts.visitNode(node, visit);
}*/

function printNodesTransformer(context: ts.TransformationContext) {
    function visit(node: ts.Node): ts.Node {
        if (ts.isClassDeclaration(node)) {
            const implementedInterfaces: string = []
            // console.log(node.heritageClauses)
            // console.log(node.heritageClauses)
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
            // 打印节点的类型和文本
            // console.log(`Node Type: ${ts.SyntaxKind[node.kind]}`);
            // console.log(node.text);

            /* if (ts.isHeritageClause(node)) {
                 // console.log(node.typeArguments);
                 // console.log(node.expression);
                 // console.log(node.expression.text);
                 // console.log(`Node Text: ${node.getText()}`);

                 const implementedInterfaces: string = []
                 // 查找实现的接口
                 node.types.forEach(type => {
                     console.log(type)
                     if (ts.isExpressionWithTypeArguments(type)) {
                         const typeName = type.expression.text;
                         implementedInterfaces.push(typeName);
                         console.log(typeName)
                         console.log(type.kind)
                     }
                 });*/

            /*if (implementedInterfaces.length > 0) {
                const decorator = ts.factory.createDecorator(
                    ts.factory.createCallExpression(
                        ts.factory.createIdentifier('Reflect.metadata'),
                        undefined,
                        [
                            ts.factory.createStringLiteral('a9360b695cff4e40aa417121d9b004a7'),
                            ts.factory.createArrayLiteralExpression(
                                implementedInterfaces.map(iface => ts.factory.createStringLiteral(iface))
                            ),
                        ]
                    )
                );

                /!*return ts.factory.updateClassDeclaration(
                    node,
                    node.decorators ? [decorator, ...node.decorators] : [decorator],
                    node.modifiers,
                    node.name,
                    node.typeParameters,
                    node.heritages,
                    node.members
                );*!/
            }*/

            /*// 如果实现了接口，添加 Reflect.metadata 装饰器
            */
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
                    // console.log(ts.version)
                    const printer: ts.Printer = ts.createPrinter();
                    const sourceFile = ts.createSourceFile(id, code, ts.ScriptTarget.Latest);
                    ts.transform(sourceFile, [printNodesTransformer])
                    // console.log(printer.printFile(sourceFile));
                    console.log(123123)
                }
            }
        }
    };
}
