import type { Plugin } from 'vite';
import * as ts from 'typescript';

// 工具函数：生成 @Reflect.metadata 装饰器的代码
function generateMetadataDecorator(interfaceNames: string[]): string {
    const metadataKey = 'a9360b695cff4e40aa417121d9b004a7';
    const metadataValue = `'${interfaceNames.join(', ')}'`;
    return `@Reflect.metadata('${metadataKey}', ${metadataValue})`;
}

// TypeScript 转换器
function transformer(context: ts.TransformationContext) {
    return (rootNode: ts.SourceFile) => {
        function visitor(node: ts.Node): ts.Node {
            console.log(ts.isClassDeclaration(node))
            if (ts.isClassDeclaration(node)) {
                const className = node.name?.text;
                if (ts.isClassDeclaration(node)) {
                    for (const nodeKey in node) {
                        console.log(nodeKey)
                        console.log(node[nodeKey])
                    }

                    // 打印每个成员的详细信息
                    node.members.forEach(member => {
                        console.log('Member:', member);
                    });
                }
                console.log(className)
                console.log(node.heritageClauses)
                const implementsInterfaces = node.heritageClauses?.flatMap(clause =>
                    clause.types.map(type => type.getText())
                );

                console.log(implementsInterfaces)
                // if (implementsInterfaces && implementsInterfaces.length > 0) {
                    // console.log(implementsInterfaces[0])
                    // 生成 @Reflect.metadata 装饰器
                    /*const decorator = generateMetadataDecorator(implementsInterfaces);
                    const decorators = ts.createDecorator(
                        ts.createCall(ts.createIdentifier('Reflect.metadata'), undefined, [
                            ts.createStringLiteral('a9360b695cff4e40aa417121d9b004a7'),
                            ts.createArrayLiteral(implementsInterfaces.map(name => ts.createStringLiteral(name)))
                        ])
                    );

                    // 在类声明的开头添加装饰器
                    const classWithDecorator = ts.updateClassDeclaration(
                        node,
                        [decorators],
                        node.modifiers,
                        node.name,
                        node.typeParameters,
                        node.heritageClauses,
                        node.members
                    );

                    return classWithDecorator;*/
                }
            // }

            return ts.visitEachChild(node, visitor, context);
        }

        return ts.visitNode(rootNode, visitor);
    };
}

// Vite 插件
export default function addReflectMetadataPlugin(): Plugin {
    return {
        name: 'vite-plugin-add-reflect-metadata',

        transform(src, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // 创建 TypeScript 代码转换器
                if (id.includes('Testaa')){
                    const sourceFile = ts.createSourceFile(id, src, ts.ScriptTarget.Latest, true);
                    ts.transform(sourceFile, [transformer]);
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
