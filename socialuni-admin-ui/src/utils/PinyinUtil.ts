import Pinyin from 'pinyin-match'

export default class PinyinUtil {
  static match(searchText: string, ...contents: string[]): boolean {
    return contents.some(content => Pinyin.match(content, searchText)) as boolean
  }
}
