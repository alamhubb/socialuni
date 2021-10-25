import TalkImg from "@/model/talk/TalkImg"
import User from "@/model/user/User"
import Comment from "@/model/comment/Comment"
import District from "@/model/District"
import Tag from "@/model/tag/Tag"

export default class Talk {
  public id: number = null
  public userId: number = null
  public content: string = null
  public status: string = null
  public imgs: TalkImg [] = null
  public user: User = null
  public createTime: Date = null
  public updateTime: Date = null
  public comments: Comment[] = null
  public commentNum: number = null
  public hugNum: number = null
  public reportNum: number = null
  public hasHugged: boolean = null
  //是否已关注
  public hasFollowed: boolean = null
  public district: District = null
  public tags: Tag [] = null
  public distance: number = null
  public globalTop: number = null
  //以后支持修改了，可以修改，但是要支持查看历史版本，显示，修改过，查看历史版本
}
