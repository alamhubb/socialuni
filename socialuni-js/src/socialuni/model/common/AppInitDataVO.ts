import CenterUserDetailRO from '../social/CenterUserDetailRO'
import DistrictVO from '../DistrictVO'
// import ChatVO from "@/model/chat/ChatVO";
import TagTypeVO from '../community/tag/TagTypeVO'
import HomeSwiperVO from '../HomeSwiperVO'
import TagVO from '../community/tag/TagVO'
import UnreadNotifyVO from '../UnreadNotifyVO'
import TalkVO from '../talk/TalkVO'

export default class AppInitDataVO {
  user: CenterUserDetailRO = null
  notifies: UnreadNotifyVO [] = []

  wx_talkTemplateId: string = ''
  wx_commentTemplateId: string = ''
  wx_reportResultTemplateId: string = ''
  wx_violationTemplateId: string = ''

  qq_talkTemplateId: string = ''
  qq_commentTemplateId: string = ''
  qq_reportResultTemplateId: string = ''
  qq_violationTemplateId: string = ''

  // chats: ChatVO[] = []

  appConfig: any = null
  tags: TagVO [] = []
  tagTypes: TagTypeVO [] = []
  reportTypes: string [] = []
  homeSwipers: HomeSwiperVO[] = []
  districts: DistrictVO[] = []
  onlineUsersCount: number = 0

  talks: TalkVO[] = []
  district: DistrictVO = null
}
