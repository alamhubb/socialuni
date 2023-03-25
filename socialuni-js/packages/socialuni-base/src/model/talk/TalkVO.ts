import CenterUserDetailRO from '../social/CenterUserDetailRO'
import CommentVO from '../comment/CommentVO'
import DistrictVO from '../DistrictVO'
import TagVO from '../community/tag/TagVO'
import ImgFileVO from '../../../../socialuni-api/src/model/ImgFileVO'

export default class TalkVO {
  public id: string = null
  public content: string = null
  public imgs: ImgFileVO [] = null
  public user: CenterUserDetailRO = null
  public createTime: Date = null
  public updateTime: Date = null
  public comments: CommentVO[] = null
  public commentNum: number = null
  public hugNum: number = null
  public reportNum: number = null
  public hasHugged: boolean = null
  // 是否已关注
  public hasFollowed: boolean = null
  public district: DistrictVO = null
  public tags: TagVO [] = null
  public circles: TagVO [] = null
  public distance: number = null
  public globalTop: number = null

  //talk三方app相关
  public threeAppName: string = null
  public threeMpQqAppId: string = null
  public threeMpWxAppId: string = null
  public threeAppId: string = null
  //自身app相关
  public threeContent: boolean = null
  public threeId: string = null
  public threeTalkPath: string = null
  public visibleGender: string = null
  public status: string = null
  // 以后支持修改了，可以修改，但是要支持查看历史版本，显示，修改过，查看历史版本
}
