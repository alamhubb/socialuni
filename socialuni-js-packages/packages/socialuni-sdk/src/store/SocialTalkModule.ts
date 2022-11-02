import {Pinia, Store} from "pinia-class-component"
import TalkFilterUtil from "../utils/TalkFilterUtil";
import TalkVueUtil from "../utils/TalkVueUtil";
import TalkTabVO from "socialuni-api/src/model/talk/TalkTabVO";
import TalkVO from "socialuni-api/src/model/talk/TalkVO";
import CommentVO from "socialuni-api/src/model/comment/CommentVO";
import CommentAddVO from "socialuni-api/src/model/comment/CommentAddVO";
import {socialCircleModule, socialUserModule} from "./store";
import SocialuniTalkAPI from "socialuni-api/src/api/socialuni/SocialuniTalkAPI";
import MsgUtil from "../utils/MsgUtil";
import CommonUtil from "../utils/CommonUtil";
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";
import TalkTabType from "socialuni-constant/constant/TalkTabType";
import StorageUtil from "../utils/StorageUtil";

@Store
export default class SocialTalkModule extends Pinia {
    // filter内容
    userMinAge: number = TalkFilterUtil.getMinAgeFilter()
    userMaxAge: number = TalkFilterUtil.getMaxAgeFilter()
    userGender: string = TalkFilterUtil.getGenderFilter()
    talkTabs: TalkTabVO [] = TalkVueUtil.getTalkTabs()
    currentTabIndex: number = TalkVueUtil.getCurTalkTabIndex()

    // state
    currentContent: null
    talk: TalkVO = null
    comment: CommentVO = null
    replyComment: CommentVO = null
    inputContentFocus = false

    // talk和评论的举报删除相关操作的dialog的显示
    commentActionShow = false
    reportDialogShow = false
    reportContentType = ''


    addComment({content}) {
        // 使输入框失去焦点，隐藏
        const commentAdd: CommentAddVO = new CommentAddVO(content, this.talk.id)
        const tempComment: CommentVO = commentAdd.toComment()
        tempComment.user = socialUserModule.user
        if (this.comment) {
            commentAdd.commentId = this.comment.id
            if (this.replyComment) {
                commentAdd.replyCommentId = this.replyComment.id
                tempComment.replyComment = this.replyComment
            }
        }
        if (this.comment) {
            this.comment.childComments.unshift(tempComment)
            this.comment.childCommentNum++
        } else {
            this.talk.comments.unshift(tempComment)
            this.talk.commentNum++
        }
        SocialuniTalkAPI.addCommentAPI(commentAdd).then((res: any) => {
            if (this.comment) {
                this.comment.childComments.splice(0, 1, res.data)
            } else {
                this.talk.comments.splice(0, 1, res.data)
            }
        }).catch(() => {
            if (this.comment) {
                this.comment.childComments.splice(0, 1)
            } else {
                this.talk.comments.splice(0, 1)
            }
        })
    }


    setTalk(talk) {
        const user = socialUserModule.user
        if (user && user.phoneNum) {
            this.talk = talk
            this.comment = null
            this.replyComment = null
            this.currentContent = talk.content
            this.inputContentFocusEvent()
        } else {
            MsgUtil.unBindPhoneNum()
        }
    }


    setComment({
                   talk,
                   comment
               }) {
        if (socialUserModule.user) {
            this.talk = talk
            this.comment = comment
            this.replyComment = null
            this.currentContent = comment.content
            this.inputContentFocusEvent()
        } else {
            MsgUtil.unLoginMessage()
        }
    }


    setReplyComment({
                        talk,
                        comment,
                        replyComment
                    }) {
        if (socialUserModule.user) {
            this.talk = talk
            this.comment = comment
            this.replyComment = replyComment
            this.currentContent = replyComment.content
            this.inputContentFocusEvent()
        } else {
            MsgUtil.unLoginMessage()
        }
    }


    inputContentFocusEvent() {
        MsgUtil.cantPopupPromptToast()
        // 需要有延迟，要不然无法成功切换
        CommonUtil.delayTime(200).then(() => {
            this.inputContentFocus = true
        })
    }


    inputContentBlur() {
        // 需要有延迟，要不然无法触发按钮事件
        CommonUtil.delayTime(100).then(() => {
            this.inputContentFocus = false
        })
    }

    setFilterData(genderFilter: string, minAge: number, maxAge: number) {
        this.userGender = genderFilter
        this.userMinAge = minAge
        this.userMaxAge = maxAge
        TalkFilterUtil.setFilterData(genderFilter, minAge, maxAge)
    }


    async getTalkTabs() {
        const res = await SocialuniAppAPI.queryHomeTabsAPI()
        //根据后端返回的tab更新tabs
        this.updateTalkTabs(res.data)
        this.updateCircleByTabIndex()
    }

    updateTalkTabs(talkTabs: TalkTabVO []) {
        //兼容旧版本，还未设置这个属性
        for (const talkTab of this.talkTabs) {
            if (TalkVueUtil.TalkTabsDefaultNames.includes(talkTab.name)) {
                talkTab.appDefaultTab = true
            }
        }
        const newTabs = []
        for (const talkTab of talkTabs) {
            const oldTab = this.talkTabs.find(item => item.name === talkTab.name)
            if (oldTab) {
                newTabs.push(oldTab)
                //后台返回的则为默认
                oldTab.appDefaultTab = true
            } else {
                const newTab = new TalkTabVO(talkTab.name)
                //后台返回的则为默认
                newTab.appDefaultTab = true
                newTabs.push(newTab)
            }
        }
        //获取不为用户自定义的
        const userCustomTabs = this.talkTabs.filter(item => !item.appDefaultTab)
        newTabs.push(...userCustomTabs)
        this.talkTabs = newTabs
    }

    updateCircleByTabIndex() {
        const curTab = this.talkTabs.find((item, index) => index === this.currentTabIndex)
        if (curTab.type === TalkTabType.circle_type) {
            socialCircleModule.setCircleName(curTab.name)
        } else {
            socialCircleModule.setCircleName(null)
        }/*
    //不处理，前三个切来切去，不能修改上次使用的
    else {
      this.setCircleName(null)
    }*/
        return curTab
    }

    setCurrentTabIndex(currentTabIndex: number) {
        this.currentTabIndex = currentTabIndex
    }

    setCurTabIndexUpdateCircle(currentTabIndex: number) {
        this.setCurrentTabIndex(currentTabIndex)
        return this.updateCircleByTabIndex()
    }

    //tab选中当前的圈子
    setCircleNameUpdateCurTabIndex(circleName: string) {
        if (circleName) {
            const circleTabIndex = this.talkTabs.findIndex(item => (item.type === TalkTabType.circle_type) && item.name === circleName)
            let circleTab
            if (circleTabIndex > -1) {
                circleTab = this.talkTabs[circleTabIndex]
                //从当前位置删除
                this.talkTabs.splice(circleTabIndex, 1)
            } else {
                circleTab = new TalkTabVO(circleName, TalkTabType.circle_type)
            }
            circleTab.firstLoad = false

            const appTas = this.talkTabs.filter(item => item.appDefaultTab)

            //添加到第四个位置
            this.talkTabs.splice(appTas.length, 0, circleTab)
            //最多保存了9个
            this.talkTabs = this.talkTabs.slice(0, 9)
            return this.setCurTabIndexUpdateCircle(appTas.length)
        }
        return this.setCurTabIndexUpdateCircle(1)
    }

    saveLastTalkTabs(talkTabs: TalkTabVO [], talkTabIndex: number, talkTabType: string) {
        //缓存记录本次推出时的默认值
        // TalkVueUtil.setTalkTabsAll(talkTabs, talkTabIndex, talkTabType)
        if (talkTabs.length) {
            StorageUtil.setObj(TalkVueUtil.TalkTabsKey, talkTabs)
        }
        StorageUtil.setObj(TalkVueUtil.talkTabIndexKey, talkTabIndex)
        StorageUtil.setObj(TalkVueUtil.talkTabTypeKey, talkTabType)
    }

    get curTab() {
        return this.talkTabs[this.currentTabIndex]
    }

    get curTabIsCircle() {
        return this.curTab.type === TalkTabType.circle_type
    }
}
