import SocialuniPageQueryQO from './SocialuniPageQueryQO'

export default class SocialuniPageQueryUtil {
  queryQO = new SocialuniPageQueryQO()
  api = null

  constructor (api: Function) {
    this.queryQO = new SocialuniPageQueryQO()
    this.api = api
  }

  async initQuery () {
    this.queryQO.firstLoad = true
    this.queryQO.queryTime = new Date()
    try {
      const res = await this.api(this.queryQO)
      this.queryQO.firstLoad = false
      this.queryQO.listData = res.data
      this.queryQO
    } catch (e) {
      this.queryQO.firstLoad = true
      this.queryQO.listData = []
      throw(e)
    }
  }

  nextPageQuery () {

  }
}
