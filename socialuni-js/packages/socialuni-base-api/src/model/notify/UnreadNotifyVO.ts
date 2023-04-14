export default class UnreadNotifyVO {
  public talkId: number
  public talkUserId: string
  public avatar: string
  public nickname: string
  public content: string
  public replyContent: string
  public replyImg: string
  public createTime: Date
  public vipFlag: boolean
  public hasRead: boolean
}
