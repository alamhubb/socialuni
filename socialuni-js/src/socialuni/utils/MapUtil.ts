import AppConfig from '../config/AppConfig'
import socialHttp from '@/socialuni/plugins/http/socialHttp'
import LocationQueryQO from '@/socialuni/model/location/LocationQueryQO'
import { QQMapResult } from '@/socialuni/model/location/QQMapResult'
import ResultRO from '@/socialuni/model/social/ResultRO'
import { socialSystemModule } from '@/socialuni/store'

export default class MapUtil {
  static async getLocationBySdk (): Promise<QQMapResult> {
    return new Promise((resolve, reject) => {
      uni.getLocation({
        type: 'gcj02 ',
        success (res) {
          //小程序，且注册了qq的地图key
          if (socialSystemModule.isMp && AppConfig.qq_mp_map_key) {
            //小程序平台可直接调用
            socialHttp.get('https://apis.map.qq.com/ws/geocoder/v1/?key=' + AppConfig.qq_mp_map_key + '&location=' + res.latitude + ',' + res.longitude).then((res: any) => {
              resolve(res.result.ad_info)
            }).catch(() => {
              reject()
            })
          } else {
            socialHttp.post<QQMapResult>('location/queryLocation', new LocationQueryQO(res.latitude, res.longitude)).then((res: ResultRO<QQMapResult>) => {
              resolve(res.data)
            }).catch(() => {
              reject()
            })
          }
        },
        fail () {
          reject()
        }
      })
    })
  }

  static getLocationByWeb (): Promise<QQMapResult> {
    return new Promise((resolve, reject) => {
      //小程序，且注册了qq的地图key
      if (socialSystemModule.isMp && AppConfig.qq_mp_map_key) {
        //小程序平台可直接调用
        socialHttp.get('https://apis.map.qq.com/ws/location/v1/ip?key=' + AppConfig.qq_mp_map_key).then((res: any) => {
          const result: QQMapResult = res.result.ad_info
          result.location = res.result.location
          resolve(result)
        }).catch(() => {
          reject()
        })
      } else {
        //其他平台需借用后台
        socialHttp.post<QQMapResult>('location/queryLocation').then((res: ResultRO<QQMapResult>) => {
          resolve(res.data)
        }).catch(() => {
          reject()
        })
      }
    })
  }
}
