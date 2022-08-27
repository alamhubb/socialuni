function test(...args) {
    console.log(args)
    return {
        configResolved(resolvedConfig) {
            console.log(resolvedConfig.resolve)
            console.log(resolvedConfig.resolve.alias)
        },
    }
}

export default test
