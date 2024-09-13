// vue.config.js
const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    lintOnSave: false,
    configureWebpack: {
        devtool: false // 禁用 source map
    }
})
