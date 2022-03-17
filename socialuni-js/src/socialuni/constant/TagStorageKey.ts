export default class TagStorageKey {
  static getMineHistoryTagNamesKey (userId: string) {
    return userId + '-' + 'mineTagsKey'
  }
}
