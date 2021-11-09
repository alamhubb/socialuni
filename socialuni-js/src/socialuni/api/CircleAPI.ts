import socialHttp from '../plugins/http/socialHttp'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import Toast from '@/socialuni/utils/Toast'


export default class CircleAPI {
  static createCircleAPI (createQO: CircleCreateQO) {
    return socialHttp.post<CircleCreateQO>('circle/createCircle', createQO).then(res => {
      Toast.toast('创建成功')
      return res
    })
  }
}
