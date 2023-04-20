export default class CircleCreateChatQO {
  circleName: string = null
  groupChatId: string = null


  constructor(circleName: string, groupChatId: string) {
    this.circleName = circleName;
    this.groupChatId = groupChatId;
  }
}
