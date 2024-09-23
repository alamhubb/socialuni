import path from 'path';
import ts from 'typescript';
import fs from 'fs';
import { dirname } from "node:path";
import { fileURLToPath } from "node:url";
// 删除 console.log 的 Transformer
function removeConsoleLogTransformer<T extends ts.Node>(context: ts.TransformationContext) {
    return (rootNode: T): T => {
        function visit(node: ts.Node): ts.Node | undefined {
            // 检查是否为 console.log 表达式
            if (ts.isExpressionStatement(node) &&
                ts.isCallExpression(node.expression) &&
                ts.isPropertyAccessExpression(node.expression.expression) &&
                node.expression.expression.expression.getText() === 'console' &&
                node.expression.expression.name.getText() === 'log') {
                // 返回 undefined 表示删除该节点
                return undefined;
            }
            return ts.visitEachChild(node, visit, context);
        }
        return ts.visitNode(rootNode, visit) as T;
    };
}
// 获取 __dirname 的值
const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const srcDir = path.resolve(__dirname);
recursiveReadDirectory(srcDir);
function recursiveReadDirectory(srcDir: string): void {
    // 读取目录中的所有文件和子目录
    fs.readdir(srcDir, (fileErr, files) => {
        for (const file of files) {
            if (fileErr)
                throw fileErr;
            const filePath = path.join(srcDir, file);
            // 获取文件或目录的状态
            fs.stat(filePath, (statErr, entryStat) => {
                if (statErr)
                    throw statErr;
                if (entryStat.isDirectory()) {
                    recursiveReadDirectory(filePath);
                }
                else {
                    if (['.js', '.ts'].includes(path.extname(file))) {
                        delConsole(filePath);
                    }
                    // 如果是文件，打印文件路径
                }
            });
        }
    });
}
function delConsole(filePath: string) {
    fs.readFile(filePath, 'utf8', (err, code) => {
        if (err)
            throw err;
        // 解析代码为 AST
        const sourceFile = ts.createSourceFile(filePath, code, ts.ScriptTarget.Latest, true);
        // 使用 Transformer 删除 console.log
        const result = ts.transform(sourceFile, [removeConsoleLogTransformer]);
        // 打印出最终的代码，保留空格和换行符
        const printer = ts.createPrinter({ newLine: ts.NewLineKind.LineFeed });
        const transformedSourceFile = result.transformed[0].getSourceFile();
        const newCode = printer.printFile(transformedSourceFile);
        fs.writeFile(filePath, newCode, (err) => {
            if (err)
                throw err;
        });
    });
}
