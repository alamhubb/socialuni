import socialHttp from '@/plugins/social/http'
import CommentAddVO from '@/model/comment/CommentAddVO'
import CommentDeleteVO from '@/model/comment/CommentDeleteVO'

export default class CommentAPI {
  static addCommentAPI (comment: CommentAddVO) {
    return socialHttp.post('comment/addComment', comment)
  }
  static deleteCommentAPI (commentId: number) {
    return socialHttp.post('comment/deleteComment', new CommentDeleteVO(commentId))
  }
}
