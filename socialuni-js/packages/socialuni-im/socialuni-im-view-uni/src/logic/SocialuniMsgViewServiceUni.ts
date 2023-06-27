import SocialuniMsgViewService from "socialuni-im-sdk/src/logic/SocialuniMsgViewService";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SelectorQuery = UniNamespace.SelectorQuery;
import NodesRef = UniNamespace.NodesRef;
import NodeInfo = UniNamespace.NodeInfo;

export default class SocialuniMsgViewServiceUni extends SocialuniMsgViewService {

    queryMessages(initQuery: boolean = false) {
        // console.trace('chaxun')
        return MessageAPI.queryMessagesAPI(this.chat.id, this.queryTime).then((res) => {
            const resMessages: MessageVO[] = res.data
            //获取拼接消息之前，顶部消息的位置
            // const nodeBox: NodesRef = query.select('.scrollView')
            const query: SelectorQuery = uni.createSelectorQuery().in(this.thisInstance)
            //保持当前位置使用
            let preFirstMsgId: string = null
            if (this.messages.length) {
                preFirstMsgId = '#m' + this.messages[0].id
            }
            const nodeBox: NodesRef = query.select(preFirstMsgId)
            nodeBox.boundingClientRect((preNodeRes: NodeInfo) => {
                let preTop = 0
                if (preNodeRes) {
                    preTop = preNodeRes.top
                }
                // this.topId = lastFirstMsgId
                // 如果还有大于等于30个就还可以加载
                if (resMessages && resMessages.length >= this.lazyLoadNum) {
                    this.chat.loadMore = LoadMoreType.more
                } else {
                    // 否则没有了
                    this.chat.loadMore = LoadMoreType.noMore
                }
                if (resMessages.length) {
                    this.queryTime = resMessages[0].createTime
                    if (initQuery) {
                        socialuniChatModule.chat.messages = resMessages
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                    } else {
                        socialuniChatModule.chat.messages.unshift(...resMessages)
                        socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                        //获取添加后的之前顶部位置，然后滚动到此位置
                        nextTick(() => {
                            const query: SelectorQuery = uni.createSelectorQuery().in(this.thisInstance)
                            // const nodeBox: NodesRef = query.select('.scrollView')
                            const nodeBox: NodesRef = query.select(preFirstMsgId)
                            nodeBox.boundingClientRect((lastNodeRes: NodeInfo) => {
                                if (lastNodeRes) {
                                    socialuniChatModule.scrollTop = lastNodeRes.top - preTop
                                }
                            }).exec()
                        })
                    }
                }
            }).exec()
            /* setTimeout(() => {
               const query: SelectorQuery = uni.createSelectorQuery().in(this)
               // const nodeBox: NodesRef = query.select('.scrollView')
               const nodeBox: NodesRef = query.select(preFirstMsgId)
               nodeBox.boundingClientRect((lastNodeRes) => {
                 if (res) {
                   console.log(lastNodeRes)
                   console.log(preTop)
                   chatModule.scrollTop = lastNodeRes.top - preTop
                   console.log(chatModule.scrollTop)
                 }
               }).exec()
               // this.topId = lastFirstMsgId
               // 如果还有大于等于30个就还可以加载
               if (res.data && res.data.length >= this.lazyLoadNum) {
                 this.chat.loadMore = LoadMoreType.more
               } else {
                 // 否则没有了
                 this.chat.loadMore = LoadMoreType.noMore
               }
             }, 100)*/
        })
    }
}
