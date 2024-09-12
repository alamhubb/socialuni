import type {Plugin} from 'vite';
import ts from "typescript";

function transformSourceFile(sourceFile: ts.SourceFile, sourceText: string): string {
    const transformer = (context: ts.TransformationContext) => (rootNode: ts.Node) => {
        function visit(node: ts.Node): ts.Node {
            if (ts.isClassDeclaration(node)) {
                const className = node.name?.text;
                const implementedInterfaces: string[] = [];

                node.heritages.forEach(heritage => {
                    if (ts.isExpressionWithTypeArguments(heritage.expression)) {
                        const typeName = heritage.expression.getText(sourceFile);
                        implementedInterfaces.push(typeName);
                    }
                });

            }
        }

        return ts.visitEachChild(node, visit, context);
    }

    return ts.transform(rootNode, [visit]).transformed[0];
}

function transformDynamicImportCodeCompile(code) {
    /*const printer: ts.Printer = ts.createPrinter();

    const source: string = `var x = 1 + 2 + 3;`;

    const transformer = <T extends ts.Node>(context: ts.TransformationContext) => (rootNode: T) => {
        function visit(node: ts.Node): ts.Node {
            console.log("Visiting " + ts.SyntaxKind[node.kind]);
            node = ts.visitEachChild(node, visit, context);
            if (node.kind === ts.SyntaxKind.BinaryExpression) {
                const binary = node as ts.BinaryExpression;
                if (binary.left.kind === ts.SyntaxKind.NumericLiteral && binary.right.kind === ts.SyntaxKind.NumericLiteral) {
                    const left = binary.left as ts.NumericLiteral;
                    const leftVal = parseFloat(left.text);
                    const right = binary.right as ts.NumericLiteral;
                    const rightVal = parseFloat(right.text);
                    switch (binary.operatorToken.kind) {
                        case ts.SyntaxKind.PlusToken:
                            return ts.createLiteral(leftVal + rightVal);
                        case ts.SyntaxKind.AsteriskToken:
                            return ts.createLiteral(leftVal * rightVal);
                        case ts.SyntaxKind.MinusToken:
                            return ts.createLiteral(leftVal - rightVal);
                    }
                }
            }
            return node;
        }
        return ts.visitNode(rootNode, visit);
    };

    const sourceFile: ts.SourceFile = ts.createSourceFile(
        'test.ts', source, ts.ScriptTarget.ES2015, true, ts.ScriptKind.TS
    );

    console.log(printer.printFile(sourceFile));

// Options may be passed to transform
    const result: ts.TransformationResult<ts.SourceFile> = ts.transform<ts.SourceFile>(
        sourceFile, [ transformer ]
    );

    const transformedSourceFile: ts.SourceFile = result.transformed[0];


    console.log(printer.printFile(transformedSourceFile));



    ts.transform()
    const source = "let x: string  = 'string'";
    const sourceFile = ts.createSourceFile(id, src, ts.ScriptTarget.Latest, true);

    let result = ts.transpileModule(source, { compilerOptions: { module: ts.ModuleKind.ES2015 }});

    console.log(JSON.stringify(result));*/
}

function addMetadataTransformer(context: ts.TransformationContext) {
    function visit(node: ts.Node): ts.Node {
        if (ts.isClassDeclaration(node)) {
            const className = node.name?.text;
            console.log(className)
            /*const className = node.name?.text;
            const implementedInterfaces: string[] = [];

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
            }*/
        }

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
                    ts.transform(sourceFile, addMetadataTransformer)
                    // console.log(printer.printFile(sourceFile));
                    console.log(123123)
                }
            }
        }
    };
}
