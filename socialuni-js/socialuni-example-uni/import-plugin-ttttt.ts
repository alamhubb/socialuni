import {createFilter} from '@rollup/pluginutils';
import {parse} from '@babel/parser';
import traverse from "@babel/traverse";
import {transformFromAstSync} from '@babel/core';

const code = `function square(n) {
  return n * n;
}`;

const ast = parse(code);

traverse(ast, {
    enter(path) {
        if (path.isIdentifier({name: "n"})) {
            path.node.name = "x";
        }
    },
});


console.log('traverse', traverse);
export default function replaceDynamicImportPlugin() {
    const filter = createFilter(['**/*.js', '**/*.ts']);

    return {
        name: 'replace-dynamic-import',
        transform(code, id) {
            if (!filter(id)) {
                return null;
            }

            const ast = parse(code, {
                sourceType: 'module',
                plugins: ['jsx'],
            });

            traverse(ast, {
                CallExpression(path) {
                    const {callee, arguments: args} = path.node;

                    if (callee.name === 'dynamicImport') {
                        console.log(callee.name)
                    }
                    if (
                        callee.name === 'dynamicImport' &&
                        args.length === 1 &&
                        args[0].type === 'StringLiteral'
                    ) {
                        const src = args[0].value;

                        console.log(src)
                        const platform = process.env.UNI_PLATFORM ? '-uni' : '-h5';

                        const newImport = {
                            type: 'AwaitExpression',
                            argument: {
                                type: 'CallExpression',
                                callee: {
                                    type: 'Import',
                                },
                                arguments: [
                                    {
                                        type: 'StringLiteral',
                                        value: `${src}${platform}/src/index.ts`,
                                    },
                                ],
                            },
                        };

                        path.replaceWith(newImport);
                    }
                },
            });

            const {code: transformedCode} = transformFromAstSync(ast, code, {
                ast: true,
                babelrc: false,
                configFile: false,
                sourceMaps: false,
                compact: true,
            });

            return transformedCode;
        },
    };
}
