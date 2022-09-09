module.exports = {
  lintOnSave: false,
  devServer: {
    port: 8024,
    // 防止控制台报错 localhost:8080无法连接
    disableHostCheck: true,
    proxy: {
      '/': {
        target: process.env.VUE_APP_BASE_URL,
        changeOrigin: true,
        pathRewrite: {
          '^/': '/'
        }
      }
    }
  },
  transpileDependencies: ['@dcloudio/uni-ui', 'uview-ui']
}
