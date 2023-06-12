import { App, defineComponent } from "vue"
import UniUtil from "socialuni-app/src/util/UniUtil"
import { socialuniPluginsModule } from "./store/SocialuniPluginsModule"
import { SocialuniPlugin } from "./interface/SocialuniPlugin"
import { ImportModule } from "./interface/ImportModule"
import { Router } from "vue-router"
import { SocialuniOption } from "./interface/socialuniOption"

const socialuniInitPlugin: SocialuniPlugin = {
  async onLaunch() {
    /*socialuniUserModule.initSocialuniUserModule()
    UserService.getAppLunchDataByHasUser()
    socialuniConfigModule.getAppConfigAction()
    socialuniConfigModule.getReportTypesAction()
    CosAPI.getCosPathAPI().then(res => {
      socialAppModule.cosHttpPath = res.data
    })
    socialAppModule.getHomeSwipersAction()*/
  }
}

async function installSocialuniPluginIns() {

  socialuniPluginsModule.addPlugin(socialuniInitPlugin)
  try {
    //查询是否包含community模块，如果存在则加载
    const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-app/src/index')
    console.log('tianjia app')
    socialuniPluginsModule.addPlugin(socialuniPlugin.default)
  } catch (e) {
    console.error(e)
    // 如果导入失败，则不触发任何操作
  }
  try {
    //查询是否包含community模块，如果存在则加载
    const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-user/src/index')
    console.log('tianjia user')
    socialuniPluginsModule.addPlugin(socialuniPlugin.default)
  } catch (e) {
    console.error(e)
    // 如果导入失败，则不触发任何操作
  }
  try {
    //查询是否包含community模块，如果存在则加载
    const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-community/src/index.ts')
    socialuniPluginsModule.addPlugin(socialuniPlugin.default)
  } catch (e) {
    console.error(e)
    // 如果导入失败，则不触发任何操作
  }
  // 社交联盟内置支持的插件
  try {
    console.log(12312)
    //查询是否包含community模块，如果存在则加载
    const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-im/src/index')
    socialuniPluginsModule.addPlugin(socialuniPlugin.default)
  } catch (e) {
    console.error(e)
    console.log(12312)
    // 如果导入失败，则不触发任何操作
  }
}


const Socialuni = {
  async install(app: App, socialuniOption: SocialuniOption) {
    // router: Router, socialuniPlugins: SocialuniPlugin[]
    // socialuniPluginsModule.init(socialuniOptions?.requestConfig)

    if (socialuniOption && socialuniOption.router) {
      socialuniPluginsModule.setRouter(socialuniOption.router)
    }

    // 社交联盟内置支持的插件
    await installSocialuniPluginIns()

    // if (socialuniOptions) {
    if (socialuniOption && socialuniOption.plugins) {
      socialuniPluginsModule.addPlugin(...socialuniOption.plugins)
    }
    // }

    for (const plugin of socialuniPluginsModule.plugins) {
      await plugin && plugin.onLaunch && plugin.onLaunch()
    }

    const shareComponent = defineComponent({
      onShareAppMessage() {
        const title = '年轻人生活分享社区'
        const path = '/pages/home/home"'
        const imageUrl = 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/full.jpg!avatar'
        return {
          title: title,
          path: path,
          imageUrl: imageUrl
        }
      },
      created(){
        console.log(1778787)
      },
      mounted() {
        console.log(12312312)
        console.log(this)
        console.log(this.$route)
        console.log(this.$data)
        const fasdf = this
        for (let key in fasdf) {
          console.log(`${key}:${fasdf[key]}`) // foo, bar
        }
        const data = this.$data
        const obj = new Object()
        for (let key in data) {
          console.log(data[key]) // foo, bar
        }
        console.log(45646)
        /*onLoad((params) => {
          UniUtil.showShareMenu()
        })*/
      }
    })
    app.mixin(shareComponent)
  }
}

export default Socialuni
