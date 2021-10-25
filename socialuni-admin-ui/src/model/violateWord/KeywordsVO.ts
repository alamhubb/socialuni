import Message from "@/model/Message"
import keywordsTriggerDetailVO from "@/model/violateWord/KeywordsTriggerDetailVO";

export default class keywordsVO {
  id: number
  text: string
  textShow: string
  pinyinText: string
  pinyinTextShow: string
  cause: string
  status: string
  deleteCause: string
  openPinyin: boolean
  usePinyin: boolean
  openText: string

  triggers: keywordsTriggerDetailVO[]

  violateNum: number
  violateRatio: number
  normalNum: number
  normalRatio: number
  totalNum: number

  textViolateNum: number
  textViolateRatio: number
  textNormalNum: number
  textNormalRatio: number
  textTotalNum: number

  pinyinViolateNum: number
  pinyinViolateRatio: number
  pinyinNormalNum: number
  pinyinNormalRatio: number
  pinyinTotalNum: number
}
