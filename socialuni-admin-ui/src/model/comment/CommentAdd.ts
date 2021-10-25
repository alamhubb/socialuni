import Comment from "@/model/comment/Comment"

export default class CommentAdd {
  public content: string
  public talkId: number
  public commentId: number
  public formId: string
  public replyCommentId: number

  constructor(content: string, talkId: number,formId:string) {
    this.content = content
    this.talkId = talkId
    this.formId = formId
  }

  public toComment(): Comment {
    const comment: Comment = new Comment()
    comment.content = this.content
    return comment
  }
}
