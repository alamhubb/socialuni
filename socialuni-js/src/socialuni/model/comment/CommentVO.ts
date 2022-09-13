import CenterUserDetailRO from '../social/CenterUserDetailRO'

export default class CommentVO {
  public id: string = null
  public no: number = null
  public hugNum: number = null
  public childCommentNum: number = null
  public content: string = null
  public user: CenterUserDetailRO = null
  public createTime: Date = null
  public childComments: CommentVO[] = []
  public replyComment: CommentVO = null
  public reportNum: number = null
}
