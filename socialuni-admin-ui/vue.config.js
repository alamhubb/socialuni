const isProduction = process.env.NODE_ENV === 'production'
const cdn = {
  css: [],
  js: [
    // vue
    'https://cdn.bootcdn.net/ajax/libs/vue/2.6.14/vue.min.js',
    'https://cdn.bootcss.com/vue-router/3.2.0/vue-router.min.js',
    'https://cdn.bootcss.com/vuex/3.6.2/vuex.min.js',
    'https://cdn.bootcss.com/axios/0.19.2/axios.min.js',
    'https://cdn.bootcdn.net/ajax/libs/element-ui/2.14.1/index.min.js'
  ]
}
module.exports = {
  publicPath: '/',
  outputDir: 'dist',
  lintOnSave: false,
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
        target: 'https://admin.qingchiapp.com:8081',
        // target: `http://172.16.7.169:8080`,
        // target: 'http://172.16.7.169:8080',
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
  },
  chainWebpack: config => {
    if (isProduction) {
      config.entry('app').clear().add('./src/main-prod.ts')
      config.externals({
        'vue': 'Vue',
        'vue-router': 'VueRouter',
        'vuex': 'Vuex',
        'axios': 'axios',
        'element-ui': 'Element'
      })
      config.plugin('html')
        .tap(args => {
          args[0].cdn = cdn
          return args
        })
    } else {
      config => {
        config.entry('app').clear().add('./src/main-dev.ts')
      }
    }
  }
}
