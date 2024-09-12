import type {Plugin} from 'vite';
import {transformSync} from '@babel/core';
import babelPresetEnv from '@babel/preset-env';
import babelPresetTypescript from '@babel/preset-typescript';
import babelPluginProposalDecorators from '@babel/plugin-proposal-decorators';
import babelPluginTransformTypescript from '@babel/plugin-transform-typescript';
import * as ts from 'typescript';

// 工具函数：生成 @Reflect.metadata 装饰器的代码
function transformDynamicImportCodeCompile(code, id) {
    const transformedCode = transformSync(code, {
        filename: id,
        presets: [babelPresetEnv, babelPresetTypescript],
        plugins: [
            babelPluginTransformTypescript,
            [
                babelPluginProposalDecorators,
                {legacy: true} // Use legacy decorators syntax
            ],
            function ({types}) {
                return {
                    visitor: {
                        ClassDeclaration(path) {
                            const heritage = path.node.implements;
                            const className = path.node.id.name
                            // const {code: classCode} = generate(path.node);

                            // 输出类的名称和代码
                            console.log(path.node);
                            console.log('Class Name:', className);
                            console.log('heritage Name:', heritage);
                            // console.log('Class Code:\n', classCode);
                            if (heritage && heritage.length > 0) {
                                const interfaceNames = heritage.map(h => h.expression.name);
                                console.log('interfaceNames Name:', interfaceNames);
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
        enforce: 'pre',
        transform(code, id) {
            if (/.ts$|.tsx$|.vue$/.test(id)) {
                // 创建 TypeScript 代码转换器
                if (id.includes('Testaa')) {
                    transformDynamicImportCodeCompile(code, id)
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
