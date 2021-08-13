/**
 * @Author qingchi
 * @Date 2021-05-29 16:08
 * @Version 1.0
 */
const talkApiConfig = {
    addTalk: 'talk/addTalk',
    deleteTalk: 'talk/deleteTalk',
    queryTalks: 'talk/queryTalks',
    queryUserTalks: 'talk/queryUserTalks',
    queryTalkDetail: 'talk/queryTalkDetail'
}

const socialAPIConfig = Object.assign({}, talkApiConfig)

export default socialAPIConfig
