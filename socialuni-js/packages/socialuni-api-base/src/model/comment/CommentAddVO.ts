import CommentVO from './CommentVO'

export default class CommentAddVO {
  public content: string
  public talkId: string
  public commentId: string
  public replyCommentId: string

  constructor(content: string, talkId: string) {
    this.content = content
    this.talkId = talkId
  }

  public toComment(): CommentVO {
    const comment: CommentVO = new CommentVO()
    comment.content = this.content
    return comment
  }
}
