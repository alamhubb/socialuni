import Pinyin from 'pinyin-match'

// 可以根据输入内容的拼音和首字母搜索
function filterMappings() {
  const fasdf: [] = this.mappings.filter(item => {
    return !this.filterInput || Pinyin.match(item.mappingName, this.filterInput)
  })
  return fasdf
}
