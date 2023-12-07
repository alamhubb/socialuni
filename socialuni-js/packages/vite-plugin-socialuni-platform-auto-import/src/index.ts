const {transform} = require('@babel/core');

function transformDynamicImportCodeCompile(code) {
    const transformedCode = transform(code, {
        plugins: [
            function ({types}) {
                return {
                    visitor: {
                        CallExpression(path) {
                            // 检查调用表达式是否是 `Platform.dynamicImport()`
                            if (
                                path.node.callee.type === 'MemberExpression' &&
                                path.node.callee.object.name === 'PlatformModuleLoadUtil' &&
                                path.node.callee.property.name === 'dynamicImport' &&
                                path.node.arguments.length > 0
                            ) {
                                const errorMsg = '调用PlatformModuleLoadUtil.dynamicImport只能传递字面量，不支持变量'
                                const argument = path.node.arguments[0];
                                const suffix = path.node.arguments[1];
                                if (!types.isStringLiteral(argument)) {
                                    console.error(errorMsg)
                                    throw new Error(errorMsg)
                                }
                                let suffixStr = 'src/index.ts'
                                if (suffix) {
                                    if (!types.isStringLiteral(argument)) {
                                        console.error(errorMsg)
                                        throw new Error(errorMsg)
                                    }
                                    suffixStr = suffix.value
                                }
                                const src = argument.value
                                argument.value = src + (process.env.UNI_PLATFORM ? '-uni/' : '-h5/') + suffixStr

                                path.replaceWith(
                                    types.callExpression(types.import(), [argument])
                                );
                            }
                        },
                    },
                };
            },
        ],
    })
    return transformedCode.code
}

//自动导入对应的包功能
module.exports = function socialuniPlatformAutoImportPlugin() {
    return {
        name: 'transform-file',
        transform(code, id) {
            if (/.js$|.ts$|.vue$/.test(id) && (/\/socialuni-[(\w)-]*\/src/.test(id) || id.includes('socialuni/src'))) {
                const modifiedScriptContent = transformDynamicImportCodeCompile(code)
                return modifiedScriptContent
            }
        }
    }
}
