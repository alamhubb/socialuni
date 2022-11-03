export default class CommentDelete {
  public commentId: number
  public deleteReason: string
  public violation: boolean

  constructor(commentId: number, deleteReason: string, violation: boolean) {
    this.commentId = commentId
    this.deleteReason = deleteReason
    this.violation = violation
  }
}
