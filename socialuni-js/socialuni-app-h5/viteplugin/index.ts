import type { Plugin } from 'vite';
const {transform} = require('@babel/core');
import generate from '@babel/generator'

// 工具函数：生成 @Reflect.metadata 装饰器的代码
function transformDynamicImportCodeCompile(code) {
    const transformedCode = transform(code, {
        plugins: [
            function ({types}) {
                return {
                    visitor: {
                        ClassDeclaration(path) {
                            const heritage = path.node.implements;
                            const className = path.node.id.name
                            const { code: classCode } = generate(path.node);

                            // 输出类的名称和代码
                            console.log('Class Name:', className);
                            console.log('Class Code:\n', classCode);
                            if (heritage && heritage.length > 0) {
                                const interfaceNames = heritage.map(h => h.expression.name);

                                /*// 使用 Reflect.metadata 插入元数据装饰器
                                const decorator = t.decorator(
                                    t.callExpression(
                                        t.memberExpression(t.identifier('Reflect'), t.identifier('metadata')),
                                        [t.stringLiteral('a9360b695cff4e40aa417121d9b004a7'), t.arrayExpression(interfaceNames.map(name => t.stringLiteral(name)))]
                                    )
                                );

                                // 将装饰器添加到类上
                                path.node.decorators = path.node.decorators || [];
                                path.node.decorators.push(decorator);*/
                            }
                        }
                    }
                };
            },
        ],
    })
    return transformedCode.code
}

// Vite 插件
export default function addReflectMetadataPlugin(): Plugin {
    return {
        name: 'vite-plugin-add-reflect-metadata',

        transform(code, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // 创建 TypeScript 代码转换器
                if (id.includes('Testaa')){
                    // transformDynamicImportCodeCompile(code)
                    console.log(123123)
                    console.log(code)
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
