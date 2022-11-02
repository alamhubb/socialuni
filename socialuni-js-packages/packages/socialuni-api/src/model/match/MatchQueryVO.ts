export default class MatchQueryVO {
  public userIds: string[]
  public lon: number
  public lat: number
  public matchType: string

  constructor (talkIds: string[], matchType: string) {
    this.userIds = talkIds
    this.matchType = matchType
  }
}
