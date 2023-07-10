import {defineConfig} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import requireTransform from 'vite-plugin-require-transform';
import commonjs from "@rollup/plugin-commonjs";
import {fileURLToPath, URL} from "node:url";
import replace from 'rollup-plugin-replace';
import babel from '@rollup/plugin-babel';
import {transform} from '@babel/core';
import {type} from "os";
import fs from "fs";
import {parse, compileScript} from "@vue/compiler-sfc";

function processVueFile(filePath) {
    if (!filePath.endsWith('.vue')) {
        throw new Error('Not a Vue file.');
    }

    const fileContent = fs.readFileSync(filePath, 'utf8');
    const {descriptor} = parse(fileContent);

    if (descriptor.script) {
        const scriptContent = descriptor.script.content;

        // 修改变量名为 'age'
        const modifiedScriptContent = scriptContent.replace(/name(?=\s*:)/g, 'age');

        descriptor.script.content = modifiedScriptContent;

        // 重新编译为 JavaScript 代码
        const compiledScript = compileScript(descriptor, {
            id: filePath,
        });

        // 更新完整的 .vue 文件源码
        const updatedVueFileContent = `<template>${descriptor.template.content}</template>\n<script>${compiledScript.content}</script>\n${descriptor.styles.map(style => `<style${style.lang ? ` lang="${style.lang}"` : ''}>${style.content}</style>`).join('\n')}`;

        return updatedVueFileContent
    }
    return fileContent
}

const IN_PRODUCTION = process.env.NODE_ENV === 'production'

let plugins = []

if (IN_PRODUCTION) {
    plugins = [
        require('@fullhuman/postcss-purgecss')({
            // rejected: true,
            // require('@fullhuman/postcss-purgecss')({
            // content: ['./public/**/*.html', './src/**/*.vue'],
            content: ['./public/**/*.html', './node_modules/@socialuni/socialuni-*/**/*.scss', './node_modules/@dcloudio/**/*.scss', './node_modules/@dcloudio/**/*.vue', './node_modules/@socialuni/socialuni-*/**/*.vue', './src/**/*.vue', './src/**/*.scss'],
            defaultExtractor(content) {
                const contentWithoutStyleBlocks = content.replace(
                    /<style[^]+?<\/style>/gi,
                    ''
                )
                return (
                    contentWithoutStyleBlocks.match(
                        /[A-Za-z0-9-_/:]*[A-Za-z0-9-_/]+/g
                    ) || []
                )
            },
            safelist: [
                /-(leave|enter|appear)(|-(to|from|active))$/,
                /^(?!(|.*?:)cursor-move).+-move$/,
                /^router-link(|-exact)-active$/,
                /data-v-.*/,
                /uicon-.*/,
                /uniui-.*/,
                /q-.*/,
                /u-.*/,
                /uni-.*/,
                /mdi-.*/,
                /page/
            ]
        })
    ]
}
const fileRegex = /\.(my-file-ext)$/

function myBabelPlugin() {
    return {
        visitor: {
            // 处理你想要修改的 AST 节点类型
            // 访问不同类型的节点并进行相应的修改
            // 这是一个示例，可以根据你的需求进行定制
            Identifier(path) {
                // 将所有的标识符名称转为大写
                path.node.name = path.node.name.toUpperCase()
            }
        }
    };
}


function transformDynamicImportCodeCompile(code) {
    const transformedCode = transform(code, {
        plugins: [
            function ({ types }) {
              return {
                visitor: {
                  CallExpression(path) {
                      console.log(path.node.callee)
                      if (
                        types.isIdentifier(path.node.callee, { name: 'dynamicImport' }) &&
                        path.node.arguments.length === 1
                      ) {
                        const argument = path.node.arguments[0];

                        console.log(path.node.callee)
                        if (types.isStringLiteral(argument)) {

                          const src = argument.value

                            console.log(src)
                          argument.value = src + (process.env.UNI_PLATFORM ? '-uni' : '-h5' + '/src/index.ts')

                          path.replaceWith(
                            types.callExpression(types.import(), [argument])
                          );
                        }
                      }
                    },
                },
              };
            },
        ],
    })
    return transformedCode.code
}

function myPlugin() {
    return {
        name: 'transform-file',
        transform(code, id) {
            if (/.js$|.ts$|.vue$/.test(id)) {

                const modifiedScriptContent = transformDynamicImportCodeCompile(code)
                return modifiedScriptContent
            }

        }
    }
}

// vite.config.ts
// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        uni(),
        myPlugin(),
        commonjs(),
        requireTransform({
            fileRegex: /.js$|.vue$/
        })
    ],
    server: {
        host: "0.0.0.0"
    },
    css: {
        postcss: {
            plugins: plugins
        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
        }
    }
})
