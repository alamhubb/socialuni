import UniLoginUtil from './UniLoginUtil'
import GetUserInfoRes = UniApp.GetUserInfoRes
import UniProviderLoginQO from "socialuni/src/model/login/UniProviderLoginQO";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

export default class UniUserUtil {
    public static async getUniProviderLoginQO(provider: string): Promise<UniProviderLoginQO> {
        //顺序不能改变，必须先获取用户信息，再获取code，要不然会报错因为获取code会重置事件
        const userInfo: GetUserInfoRes = await UniUserUtil.getUserInfo(provider)
        const providerCode = await UniLoginUtil.getLoginCode(provider)
        const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(providerCode, userInfo, userInfo.userInfo as any, provider)
        return providerLoginQO
    }

    public static getUserInfo(provider?: any) {
        return new Promise<GetUserInfoRes>((resolve, reject) => {
            if (socialuniSystemModule.isMpWx) {
                //只有为小程序，且为微信小程序时才
                uni.getUserProfile({
                    provider: provider,
                    desc: 'getUserInfo',
                    success(userInfo) {
                        console.log(userInfo)
                        resolve(userInfo)
                    },
                    fail(e) {
                        reject(e)
                    }
                })
            } else {
                uni.getUserInfo({
                    provider: provider,
                    success(userInfo) {
                        resolve(userInfo)
                    },
                    fail(e) {
                        reject(e)
                    }
                })
            }
        })
    }
}
