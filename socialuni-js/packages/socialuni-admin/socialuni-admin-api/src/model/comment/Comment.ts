import User from '@/model/user/User'

export default class Comment {
  public id: number = null
  public no: number = null
  public hugNum: number = null
  public childCommentNum: number = null
  public content: string = null
  public user: User = null
  public createTime: Date = null
  public childComments: Comment[] = []
  public replyComment: Comment = null
}
