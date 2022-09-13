export default class UserTalkQueryVO {
  public userId: string
  public talkIds: string[]

  constructor(userId: string, talkIds: string[]) {
    this.userId = userId
    this.talkIds = talkIds
  }
}
