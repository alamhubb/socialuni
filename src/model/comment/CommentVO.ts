import SocialUserRO from '@/model/social/SocialUserRO'

export default class CommentVO {
  public id: number = null
  public no: number = null
  public hugNum: number = null
  public childCommentNum: number = null
  public content: string = null
  public user: SocialUserRO = null
  public createTime: Date = null
  public childComments: CommentVO[] = []
  public replyComment: CommentVO = null
  public reportNum: number = null
}
