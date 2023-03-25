export default class SocialCircleRO {
  public id: number = null
  public name: string = null
  //群聊id
  public groupId: string = null
  public selected = false
  public count: number = null
  public talkCount: number = null
  public avatar: string = null
  public description: string = null


  constructor (name?: string) {
    this.name = name || null
  }
}
