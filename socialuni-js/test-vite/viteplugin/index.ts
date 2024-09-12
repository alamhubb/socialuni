import type {Plugin} from 'vite';
import * as ts from "typescript";

/*function transformDynamicImportCodeCompile(code){
    const printer: ts.Printer = ts.createPrinter();

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

    console.log(JSON.stringify(result));
}*/

// Vite 插件
export default function addReflectMetadataPlugin(): Plugin {
    return {
        name: 'vite-plugin-add-reflect-metadata',
        enforce: 'pre',
        transform(code, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // 创建 TypeScript 代码转换器
                if (id.includes('Testaa')) {
                    const sourceFile = ts.createSourceFile(id, code, ts.ScriptTarget.Latest, true);
                    console.log(printer.printFile(sourceFile));
                    // transformDynamicImportCodeCompile(code)
                    console.log(123123)
                }
            }
            // 添加 reflect-metadata 引入
            /*let code = src;
            if (!src.includes("import 'reflect-metadata';")) {
                code = "import 'reflect-metadata';\n" + src;
            }

            // 创建 TypeScript 代码转换器
            const sourceFile = ts.createSourceFile(id, code, ts.ScriptTarget.Latest, true);
            const result = ts.transform(sourceFile, [transformer]);

            // 获取转换后的代码
            const printer = ts.createPrinter();
            const transformedCode = printer.printFile(result.transformed[0] as ts.SourceFile);*/
        }
    };
}
