module.exports = {
  publicPath: '/',
  outputDir: 'dist',
  lintOnSave: false,
  productionSourceMap: false,
  runtimeCompiler: true,
  devServer: {
    disableHostCheck: true,
    // 防止控制台报错 localhost:8080无法连接
    host: '0.0.0.0',
    port: 8031,
    proxy: {
      // change xxx-api/login => mock/login
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]: {
        // target: 'http://localhost:8180/',
        // target: 'https://localpc.qingchiapp.com:8081',
        target: 'https://admin.socialuni.cn:8081',
        // target: `http://172.16.7.169:8080`,
        // target: 'http://172.16.7.169:8080',
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
  }
}
