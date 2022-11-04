import { faceArr } from "@/pages/conversation/emojiList";
export function formatMessageCardMessage(messageItem) {
  let content = "";
  let latestMsg = messageItem.latestMsg;
  if (!latestMsg) return content;
  latestMsg = JSON.parse(latestMsg);
  content = latestMsg.content;
  let contentType = getMessageCardContentType(messageItem);
  content = getContent(content, contentType, latestMsg);
  return `${getNickname(latestMsg.senderNickname)}${content}`;
}
export function formatConversationCardMessage(messageItem) {
  let content = messageItem.content;
  let contentType = getConversationCardContentType(messageItem);
  content = getContent(content, contentType, messageItem);
  return content;
}
export function formatHistoryCardMessage(item) {
  let content = item.content;
  let contentType = getHistoryCardContentType(item);
  content = getContent(content, contentType);
  return content;
}
function getContent(c, cType, latestMsg) {
  let content = c;
  switch (cType) {
    case 101: //文字
      break;
    case 102:
      content = "[图片]";
      break;
    case 103:
      content = "[语音]";
      break;
    case 104:
      content = "[视频]";
      break;
    case 105:
      content = "[文件]";
      break;
    case 106:
      content = JSON.parse(content).text;
      break;
    case 107: //合并消息
      content = JSON.parse(content).title;
      break;
    case 108:
      content = "[名片]";
      break;
    case 109:
      content = "[位置]";
      break;
    case 110:
      content = "[自定义消息]";
      break;
    case 111:
      content = latestMsg.senderNickname + "撤回了一条消息";
      break;
    case 114:
      content = JSON.parse(content).text;
      break;
    case 115:
      content = "[语音通话]";
      break;
    case 118:
      content = latestMsg.senderNickname + "撤回了一条消息";
      break;
    case 1204:
      content = `你们已经是好友啦,开始聊天吧~`;
      break;
    case 1400:
      content = getNoticeContent(content, 1400);
      break;
    case 1501:
      content = getNoticeContent(content) + "创建了群聊";
      break;
    case 1502:
      content = getNoticeContent(content) + "更新了群信息";
      break;
    case 1503:
      content = getNoticeContent(content) + "同意了加群申请";
      break;
    case 1504:
      content = getNoticeContent(content) + "退出了群聊";
      break;
    case 1507:
      content = getNoticeContent(content, 1507);
      break;
    case 1508:
      content = getNoticeContent(content) + "更新了群信息";
      break;
    case 1509:
      content = getNoticeContent(content, 1509);
      break;
    case 1510:
      content = getNoticeContent(content, 1509) + "加入了群聊";
      break;
    case 1511:
      content = getNoticeContent(content) + "解散了群组";
      break;
    case 1512:
      content = getNoticeContent(content, 1512);
      break;
    case 1513:
      content = getNoticeContent(content, 1513);
      break;
    case 1514:
      content = getNoticeContent(content) + "开启了禁言";
      break;
    case 1515:
      content = getNoticeContent(content) + "关闭了禁言";
      break;
    case 1517:
      content = getNoticeContent(content, 1517);
      break;
    case 1518:
      content = getNoticeContent(content, 1518);
      break;
    default:
      try {
        let c2 = JSON.parse(content);
        console.log(c2, cType);
      } catch (error) {
        console.log(error);
      }
      // let c3 = JSON.parse(c2.jsonDetail);
      // console.log(cType, c2.defaultTips, c3);
      content = "[暂未支持的消息类型]";
      break;
  }
  return content;
}
export function getMessageCardContentType(messageItem) {
  let contentType = 110;
  let latestMsg = messageItem.latestMsg;
  if (!latestMsg) return contentType;
  latestMsg = JSON.parse(latestMsg);
  contentType = latestMsg.contentType;
  return contentType;
}
export function getConversationCardContentType(messageItem) {
  return messageItem.contentType || 110;
}
export function getHistoryCardContentType(item) {
  return item.contentType || 110;
}
function getNickname(nickname = "", conversationType = 1) {
  //1、单聊;2、群聊
  if (conversationType === 1) {
    return "";
  }
  return nickname ? nickname + " :" : "";
}
const noticeTypeList = [
  111, 118, 1204, 1400, 1501, 1502, 1503, 1504, 1505, 1506, 1507, 1508, 1509,
  1510, 1511, 1512, 1513, 1514, 1515, 1516, 1517, 1518, 1701,
];
export function isNoticeMessage(cType, msgFrom = null) {
  //是否会话提示信息
  return noticeTypeList.includes(cType);
  // return noticeTypeList.includes(cType) || (cType === 101 && msgFrom === 100);
}
function getNoticeContent(c, cType) {
  let content = JSON.parse(c);
  let jsonDetail = JSON.parse(content.jsonDetail);
  if (jsonDetail.opUser && jsonDetail.opUser.nickname) {
    content = jsonDetail.opUser.nickname;
  } else if (jsonDetail.quitUser && jsonDetail.quitUser.nickname) {
    content = jsonDetail.quitUser.nickname;
  }
  if (cType === 1400) {
    content = `${jsonDetail.notificationName}:${jsonDetail.text}`;
  } else if (cType === 1507) {
    content = `${jsonDetail.opUser.nickname}将群主身份转让给${jsonDetail.newGroupOwner.nickname}`;
  } else if (cType === 1509) {
    if (jsonDetail.invitedUserList) {
      let invitedUserList = jsonDetail.invitedUserList.map((i) => i.nickname);
      content += "邀请了" + invitedUserList.join("、");
    } else if (jsonDetail.entrantUser) {
      content = jsonDetail.entrantUser.nickname;
    }
  } else if (cType === 1512) {
    let mutedUser = jsonDetail.mutedUser.nickname;
    let mutedSeconds = jsonDetail.mutedSeconds;
    content = `${mutedUser}被${content}禁言了${mutedSeconds}秒`;
  } else if (cType === 1513) {
    let mutedUser = jsonDetail.mutedUser.nickname;
    content = `${mutedUser}被${content}解除了禁言`;
  } else if (cType === 1517) {
    let nickname = jsonDetail.changedUser.nickname;
    let roleLevel = jsonDetail.changedUser.roleLevel;
    content = `${content}修改${nickname}权限等级为${roleLevel}`;
  } else if (cType === 1518) {
    let nickname = jsonDetail.changedUser.nickname;
    let roleLevel = jsonDetail.changedUser.roleLevel;
    content = `${content}修改${nickname}权限等级为${roleLevel}`;
  }
  return content;
}
export function formatSendContent(c) {
  let content = "";
  const atUserList = [];
  if (!c) return { content, atUserList };
  content = c
    .replace(/<p>|<\/p>/g, "")
    .replace(/<br>/, "")
    .replace(/&nbsp;/, " ");
  const reg1 = new RegExp("(i?)(<img)([^>]+>)", "gmi"); //匹配img标签
  const reg2 = /data-custom=".+"/; //匹配img标签data-custom自定义属性
  content = content.replace(reg1, (img) => {
    let emojiText = "";
    img.replace(reg2, (v) => {
      let d = v.slice(12, v.length);
      d = JSON.parse(d);
      const dataObj = {};
      let arr = d.replace(/&amp/g, "").split(";");
      arr.map((ctx) => {
        const ctxArr = ctx.split("=");
        dataObj[ctxArr[0]] = ctxArr[1];
      });
      if (dataObj.emojiText) {
        emojiText = dataObj.emojiText;
      }
      if (dataObj.sendID) {
        emojiText = "@" + dataObj.sendID;
        atUserList.push(dataObj.sendID);
      }
      return v;
    });
    return emojiText || img;
  });
  return { content, atUserList };
}
function checkSystemEmoji(v) {
  const emojiReg =
    /(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff])[\ufe0e\ufe0f]?(?:[\u0300-\u036f\ufe20-\ufe23\u20d0-\u20f0]|\ud83c[\udffb-\udfff])?(?:\u200d(?:[^\ud800-\udfff]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff])[\ufe0e\ufe0f]?(?:[\u0300-\u036f\ufe20-\ufe23\u20d0-\u20f0]|\ud83c[\udffb-\udfff])?)*/;
  let isSystemEmoji = emojiReg.test(v);
  return isSystemEmoji;
}
export function getEmojiContent(atMemberPositionList) {
  let list = [];
  atMemberPositionList.map((item) => {
    if (item.type === "text") {
      let startIndex = item.startIndex;
      let strStartIndex = 0;
      const reg = /\[.+?\]/gi;
      const arr = item.content.match(reg);
      const newArr = [];
      if (arr && arr.length) {
        arr.map((t, arrIndex) => {
          let str = item.content.slice(strStartIndex, item.content.length);
          const strIndex = str.indexOf(t);
          //前面有非emoji字符
          if (strIndex !== 0) {
            const text = str.slice(0, strIndex);
            for (let j = 0; j < text.length; j++) {
              let emojiText = text.slice(j, j + 2);
              const isSystemEmoji = checkSystemEmoji(emojiText);
              if (isSystemEmoji) {
                newArr.push({
                  content: emojiText,
                  startIndex: startIndex + strStartIndex + 1,
                  endIndex: startIndex + strStartIndex + 1,
                  type: "text",
                });
                strStartIndex += 1;
                j++;
              } else {
                newArr.push({
                  content: text.slice(j, j + 1),
                  startIndex: startIndex + strStartIndex,
                  endIndex: startIndex + strStartIndex,
                  type: "text",
                });
              }
              strStartIndex += 1;
            }
          }
          //截取emoji字符
          const emojiSrc = getEmojiSrc(t);
          if (emojiSrc) {
            newArr.push({
              content: t,
              endIndex: startIndex + strStartIndex + t.length - 1,
              startIndex: startIndex + strStartIndex,
              type: "emoji",
              src: getEmojiSrc(t),
            });
            strStartIndex += t.length;
          } else {
            for (let j = 0; j < t.length; j++) {
              newArr.push({
                content: t.slice(j, j + 1),
                startIndex: startIndex + strStartIndex,
                endIndex: startIndex + strStartIndex,
                type: "text",
              });
              strStartIndex += 1;
            }
          }
          //截取尾部非emoji字符
          const text = str.slice(strIndex + t.length, str.length);
          if (arrIndex === arr.length - 1 && text) {
            for (let j = 0; j < text.length; j++) {
              let emojiText = text.slice(j, j + 2);
              const isSystemEmoji = checkSystemEmoji(emojiText);
              if (isSystemEmoji) {
                newArr.push({
                  content: emojiText,
                  startIndex: startIndex + strStartIndex + 1,
                  endIndex: startIndex + strStartIndex + 1,
                  type: "text",
                });
                strStartIndex += 1;
                j++;
              } else {
                newArr.push({
                  content: text.slice(j, j + 1),
                  startIndex: startIndex + strStartIndex,
                  endIndex: startIndex + strStartIndex,
                  type: "text",
                });
              }
              strStartIndex += 1;
            }
          }
        });
      } else {
        const text = item.content;
        for (let j = 0; j < text.length; j++) {
          let emojiText = text.slice(j, j + 2);
          const isSystemEmoji = checkSystemEmoji(emojiText);
          if (isSystemEmoji) {
            newArr.push({
              content: emojiText,
              startIndex: startIndex + strStartIndex + 1,
              endIndex: startIndex + strStartIndex + 1,
              type: "text",
            });
            strStartIndex += 1;
            j++;
          } else {
            newArr.push({
              content: text.slice(j, j + 1),
              startIndex: startIndex + strStartIndex,
              endIndex: startIndex + strStartIndex,
              type: "text",
            });
          }
          strStartIndex += 1;
        }
      }
      list.push(...newArr);
    } else {
      list.push(item);
    }
  });
  list = list.sort((a, b) => {
    return a.startIndex - b.startIndex;
  });
  return list;
}

function getEmojiSrc(context) {
  const item = faceArr.filter((i) => i.context === context)[0] || {};
  return item.src || "";
}
