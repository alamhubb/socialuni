import Message from '@/model/Message'
import keywordsTriggerDetailVO from '@/model/violateWord/KeywordsTriggerDetailVO'
import keywordsVO from '@/model/violateWord/KeywordsVO'

export default class KeywordsDetailVO {
  // db的keywords
  tempKeywords: keywordsVO
  // 实时计算的keywords
  keywords: keywordsVO

  triggerDetails: keywordsTriggerDetailVO[]
  vioTriggerDetails: keywordsTriggerDetailVO[]
}
