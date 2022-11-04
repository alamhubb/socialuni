export default {
  systemInfo: (state) => state.user.systemInfo,
  userInfo: (state) => state.user.userInfo,
  platform: (state) => state.user.platform,
  operationID: (state) => state.user.operationID,
  userID: (state) => state.user.userID,
  token: (state) => state.user.token,
  loginUserInfo: (state) => state.user.loginUserInfo,
  loginStatus: (state) => state.user.loginStatus,
  connectStatus: (state) => state.user.connectStatus,
  messageList: (state) => state.message.messageList,
  successTimes: (state) => state.message.successTimes,
  newMessageTimes: (state) => state.message.newMessageTimes,
  indexMessageTimes: (state) => state.message.indexMessageTimes,
  infoUpdateTimes: (state) => state.message.infoUpdateTimes,
  operationMessageItem: (state) => state.message.operationMessageItem,
  uploadFileTimes: (state) => state.message.uploadFileTimes,
  uploadFileRes: (state) => state.message.uploadFileRes,
  openFile: (state) => state.message.openFile,
  fileHistory: (state) => state.message.fileHistory,
  sendMessageStatusList: (state) => state.message.sendMessageStatusList,
  newMessageList: (state) => state.message.newMessageList,
  revokeMessageTimes: (state) => state.message.revokeMessageTimes,
  revokeMessageList: (state) => state.message.revokeMessageList,
  groupInfo: (state) => state.group.groupInfo,
  updateGroupInfoTimes: (state) => state.group.updateGroupInfoTimes,
  friendNoticeList: (state) => state.contacts.friendNoticeList,
  selfFriendNoticeList: (state) => state.contacts.selfFriendNoticeList,
  groupNoticeList: (state) => state.contacts.groupNoticeList,
  selfGroupNoticeList: (state) => state.contacts.selfGroupNoticeList,
  friendNoticeItem: (state) => state.contacts.friendNoticeItem,
  groupNoticeItem: (state) => state.contacts.groupNoticeItem,
  joinedGroupDeletedTimes: (state) => state.contacts.joinedGroupDeletedTimes,
  clearHistoryMessageTimes: (state) => state.message.clearHistoryMessageTimes,
  frinendChangeTimes: (state) => state.message.frinendChangeTimes,
  frinendInfoChangeTimes: (state) => state.message.frinendInfoChangeTimes,
  localConversationMessageList: (state) =>
    getLocalConversationMessageList(state),
  apiConfig: (state) => state.user.apiConfig,
};
function getLocalConversationMessageList(state) {
  const localConversationMessageList =
    state.message.localConversationMessageList;
  const userID = state.user.userID;
  return localConversationMessageList.filter((i) => {
    return i.currentUserID === userID;
  });
}
