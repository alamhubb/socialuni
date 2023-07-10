module.exports = function ({types}) {
    return {
        visitor: {
            CallExpression(path) {
                console.log(path)
                if (
                    types.isIdentifier(path.node.callee, {name: 'dynamicImport'}) &&
                    path.node.arguments.length === 1
                ) {
                    const argument = path.node.arguments[0];

                    if (types.isStringLiteral(argument)) {

                        const src = argument.value
                        argument.value = src + (process.env.UNI_PLATFORM ? '-uni' : '-h5' + '/src/index.ts')

                        console.log(src)
                        path.replaceWith(
                            types.callExpression(types.import(), [argument])
                        );
                    }
                }
            },
        },
    };
}
