module.exports = {
  lintOnSave: false,
  devServer: {
    port: 8024,
    // 防止控制台报错 localhost:8080无法连接
    disableHostCheck: true,
    proxy: {
      // change xxx-api/login => mock/login
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_URL]: {
        // target: 'http://localhost:8180/',
        target: process.env.VUE_APP_BASE_URL,
        // target: 'https://admin.socialuni.cn:8081',
        // target: `http://172.16.7.169:8080`,
        // target: 'http://172.16.7.169:8080',
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
  },
  transpileDependencies: ['@dcloudio/uni-ui', 'uview-ui']
}
