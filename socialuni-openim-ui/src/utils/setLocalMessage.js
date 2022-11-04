import store from "@/store";
export function set_messageToLocal(messageItem, im) {
  // console.log(messageItem);
  // const sessionType = messageItem.sessionType;
  // if (sessionType === 1) {
  //   insertSingleMessageToLocalStorage(messageItem, messageItem.recvID, messageItem.sendID, im);
  // } else if (sessionType === 2) {
  //   insertGroupMessageToLocalStorage(messageItem, messageItem.groupID, messageItem.sendID, im);
  // }
}
function insertSingleMessageToLocalStorage(message, recvID, sendID, im) {
  im.insertSingleMessageToLocalStorage(
    store.getters.operationID,
    message,
    recvID,
    sendID,
    (res) => {
      // console.log("single", res);
    }
  );
}
function insertGroupMessageToLocalStorage(message, groupID, sendID, im) {
  im.insertGroupMessageToLocalStorage(
    store.getters.operationID,
    message,
    groupID,
    sendID,
    (res) => {
      // console.log("group", res);
    }
  );
}
