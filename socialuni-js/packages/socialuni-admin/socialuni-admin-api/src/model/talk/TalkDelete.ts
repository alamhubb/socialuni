export default class TalkDelete {
  public talkId: number
  public deleteReason: string
  public violation: boolean

  constructor(talkId: number, deleteReason: string, violation: boolean) {
    this.talkId = talkId
    this.deleteReason = deleteReason
    this.violation = violation
  }
}
