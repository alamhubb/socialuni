import Pinyin from 'pinyin-match'

export default class PinyinUtil {
  static match(searchText: string, ...contents: string[]): boolean {
    if (!searchText) {
      return true
    }
    searchText = searchText.replaceAll('\'', '').replaceAll('/\s/g', '')
    return contents.some(content => {
      content = String(content)
      const chineseMatch = Pinyin.match(content, searchText)
      if (chineseMatch) {
        return true
      }
      // 支持模糊匹配，hulu可匹配hl
      const matchRegStr = searchText.split('').join('.*')
      return content.match(new RegExp(matchRegStr, 'i'))
    }) as boolean
  }
}
