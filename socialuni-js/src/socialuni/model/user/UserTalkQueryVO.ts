export default class UserTalkQueryVO {
  public userId: string
  public talkIds: number[]

  constructor (userId: string, talkIds: number[]) {
    this.userId = userId
    this.talkIds = talkIds
  }
}
