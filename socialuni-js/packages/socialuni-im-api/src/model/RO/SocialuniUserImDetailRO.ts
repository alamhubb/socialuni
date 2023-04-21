export default class SocialuniImUserDetailRO {
  //是否为加入了黑名单
  blackUser: boolean = null
  //你是否被对方拉黑了
  beBlackUser: boolean = null
  //对方是否接收陌生人消息
  beAllowStrangerMsg: boolean = null
  // 关闭陌生人消息后将无法给陌生人发送消息，且无法接收陌生人消息
  friendUser: boolean = null
}
