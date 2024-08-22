export default class SocialuniPartnerUserContributeRO {
    //今日日期
    todayDate: number = null
    //今日在线时长
    onlineMinute: number = null
    //今日贡献值
    contributeValue: number = null
    //不计算加成的贡献值
    originalContributeValue: number = null
    //使用倍数加成，
    useMultiple: number = null

    //占比都应该是最大4位数
    //贡献值
    valuePercent: number = null
    //不计算加成的分配占比
    originalValuePercent: number = null


    assignPercent: number = null
    originalAssignPercent: number = null

    //今日获得积分
    originalAssignPoints: number = null
    assignPoints: number = null

    //贡献排名
    contributeRank: number = null
    //原始贡献排名
    originalContributeRank: number = null


    //发送消息数量
    sendMsgNum: number = null

    //使用倍数加成，
    payMultiple: number = null
}
