import { Store} from "pinia-class-component"
import {socialChatModule} from "socialuni-sdk/src/store/store";
import {AccessFriendParams, AddFriendParams} from "socialuni-sdk/src/plugins/open-im-sdk";

@Store
export default class SocialChatFriendModule  {
    /**
     * 发送好友请求
     * @param toUserID 用户ID
     * @param reqMsg 申请验证信息
     */
    addFriend(toUserID : string ,reqMsg:string ){
        const options:AddFriendParams = {
            toUserID:toUserID,
            reqMsg:reqMsg
        };
        socialChatModule.openIm.addFriend(options).then(({ data })=>{
            console.error(data);
        }).catch(err=>{
            console.error(err);
        })
    }

    /**
     * 接受好友请求。
     * @param toUserID 申请者ID
     * @param handleMsg 回复消息
     */
    acceptFriendApplication(toUserID : string ,handleMsg:string ){
        const options:AccessFriendParams = {
            toUserID , handleMsg
        }
        socialChatModule.openIm.acceptFriendApplication(options).then(({ data })=>{
        }).catch(err=>{
        })

    }

    /**
     * 拒绝好友请求
     * @param toUserID 申请者ID
     * @param handleMsg 回复消息
     */
    refuseFriendApplication(toUserID : string ,handleMsg:string ){
        const options:AccessFriendParams = {
            toUserID , handleMsg
        }
        socialChatModule.openIm.refuseFriendApplication(options).then(({ data })=>{
        }).catch(err=>{
        })
    }

    /**
     * 发送好友请求
     * @param toUserID 用户ID
     * @param reqMsg 申请验证信息
     */
    deleteFriend(toUserID : string ,reqMsg:string ){
        const options:AddFriendParams = {
            toUserID:toUserID,
            reqMsg:reqMsg
        };
        socialChatModule.openIm.deleteFriend(options).then(({ data })=>{
        }).catch(err=>{
        })
    }
}

/*
  pushMessagesAction (msgs: MessageVO[]) {

  }*/
/*

  pushChatAction (newChat: SocialuniChatRO) {
    this.chats.unshift(newChat)
  }*/

