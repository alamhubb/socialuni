export default class keywordsTriggerDetailVO {
  id: number
  reportId: number

  // 要显示具体的各种比例所以不是懒加载
  keywordsId: number
  contentType: string
  contentId: number

  content: string
  keywordsText: string
  // 主要违规部分，内容未处理前，违规的部分
  matchText: string

  keywordsPinyin: string
  // 主要违规变种部分，内容处理转变为拼音之后，违规的部分
  matchPinyin: string

  auditResult: string
  // 有变种
  usePinyin: boolean
}
