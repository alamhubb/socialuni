import { set_messageToLocal } from "@/utils/setLocalMessage";
const state = {
  messageList: [
    {
      conversationID: "group_200192428",
      conversationType: 2,
      userID: "",
      groupID: "200192428",
      showName: "设计中心",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"365e64f99c813230af1bf2e0116c09b2","serverMsgID":"82780120209a29cfdb9dce43c127424b","createTime":1657521244313,"sendTime":1657521244317,"sessionType":2,"sendID":"863692672","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":0,"senderNickname":"todo","senderFaceUrl":"ic_avatar_05","groupID":"200192428","content":"欢迎todo加入部门","seq":8875,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657521244317,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_2091143286",
      conversationType: 2,
      userID: "",
      groupID: "2091143286",
      showName: "tt",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"7cfaff4d64fa461ba39910130cfc2fd9","serverMsgID":"6741bd11212ff3067dc48bd512480d78","createTime":1657507771862,"sendTime":1657507771933,"sessionType":2,"sendID":"2911830477","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":5,"senderNickname":"hihihi","senderFaceUrl":"ic_avatar_05","groupID":"2091143286","content":"5","seq":8853,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657507771933,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1549274016",
      conversationType: 2,
      userID: "",
      groupID: "1549274016",
      showName: "..",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 2,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"55c51d1ec4fa7927014cb66b3622e8e1","serverMsgID":"b0567386d292ea7622017684ad9975ec","createTime":1657505248539,"sendTime":1657505248540,"sessionType":2,"sendID":"3798435312","recvID":"870517699","msgFrom":0,"contentType":1509,"platformID":0,"groupID":"1549274016","content":"{\\"detail\\":\\"Ci4KCjE1NDkyNzQwMTYSAi4uMgk4NzA1MTc2OTk4p8+klgZABloJODcwNTE3Njk5EkwKCjE1NDkyNzQwMTYSCjM3OTg0MzUzMTIYASDsz6SWBioPT3BlbklNLUJsb29taW5nMgxpY19hdmF0YXJfMDY4AUoJODcwNTE3Njk5GkQKCjE1NDkyNzQwMTYSCTU2NTIxNDU3ORgBIOGLrpYGKgnlvJTlhL/lrZAyDGljX2F2YXRhcl8wNkoKMzc5ODQzNTMxMg==\\",\\"defaultTips\\":\\" was invited into the group\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1549274016\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"..\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"createTime\\\\\\":1657350055,\\\\\\"memberCount\\\\\\":6,\\\\\\"creatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1549274016\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3798435312\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657350124,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM-Blooming\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_06\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"invitedUserList\\\\\\":[{\\\\\\"groupID\\\\\\":\\\\\\"1549274016\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"565214579\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657505249,\\\\\\"nickname\\\\\\":\\\\\\"弔儿子\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_06\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3798435312\\\\\\"}]}\\"}","seq":8834,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"1549274016\\",\\"groupName\\":\\"..\\",\\"ownerUserID\\":\\"870517699\\",\\"createTime\\":1657350055,\\"memberCount\\":6,\\"creatorUserID\\":\\"870517699\\"},\\"opUser\\":{\\"groupID\\":\\"1549274016\\",\\"userID\\":\\"3798435312\\",\\"roleLevel\\":1,\\"joinTime\\":1657350124,\\"nickname\\":\\"OpenIM-Blooming\\",\\"faceURL\\":\\"ic_avatar_06\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"870517699\\"},\\"invitedUserList\\":[{\\"groupID\\":\\"1549274016\\",\\"userID\\":\\"565214579\\",\\"roleLevel\\":1,\\"joinTime\\":1657505249,\\"nickname\\":\\"弔儿子\\",\\"faceURL\\":\\"ic_avatar_06\\",\\"operatorUserID\\":\\"3798435312\\"}]}","defaultTips":" was invited into the group"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657505248540,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_2416895552",
      conversationType: 2,
      userID: "",
      groupID: "2416895552",
      showName: ".",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 3,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"4ac35acb7c097caa7a5916ac0a329153","serverMsgID":"5c94e4c7d0a22004ade757080296d834","createTime":1657505092758,"sendTime":1657505092758,"sessionType":2,"sendID":"3798435312","recvID":"870517699","msgFrom":0,"contentType":1509,"platformID":0,"groupID":"2416895552","content":"{\\"detail\\":\\"Ci0KCjI0MTY4OTU1NTISAS4yCTg3MDUxNzY5OTiz1KSWBkAHWgk4NzA1MTc2OTkSTAoKMjQxNjg5NTU1MhIKMzc5ODQzNTMxMhgBILPUpJYGKg9PcGVuSU0tQmxvb21pbmcyDGljX2F2YXRhcl8wNjgBSgk4NzA1MTc2OTkaQgoKMjQxNjg5NTU1MhIKMjUwNzA1NzI5MBgBIMWKrpYGKgboooHluIUyDGljX2F2YXRhcl8wMkoKMzc5ODQzNTMxMhpFCgoyNDE2ODk1NTUyEgoyNTM5NDY2MzQ1GAEgxYqulgYqCeael+S8r+WuhzIMaWNfYXZhdGFyXzAySgozNzk4NDM1MzEyGkQKCjI0MTY4OTU1NTISCTU2NTIxNDU3ORgBIMWKrpYGKgnlvJTlhL/lrZAyDGljX2F2YXRhcl8wNkoKMzc5ODQzNTMxMg==\\",\\"defaultTips\\":\\" was invited into the group\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"2416895552\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\".\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"createTime\\\\\\":1657350707,\\\\\\"memberCount\\\\\\":7,\\\\\\"creatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"2416895552\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3798435312\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657350707,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM-Blooming\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_06\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"invitedUserList\\\\\\":[{\\\\\\"groupID\\\\\\":\\\\\\"2416895552\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"2507057290\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657505093,\\\\\\"nickname\\\\\\":\\\\\\"袁帅\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_02\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3798435312\\\\\\"},{\\\\\\"groupID\\\\\\":\\\\\\"2416895552\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"2539466345\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657505093,\\\\\\"nickname\\\\\\":\\\\\\"林伯宇\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_02\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3798435312\\\\\\"},{\\\\\\"groupID\\\\\\":\\\\\\"2416895552\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"565214579\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657505093,\\\\\\"nickname\\\\\\":\\\\\\"弔儿子\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_06\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3798435312\\\\\\"}]}\\"}","seq":8832,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"2416895552\\",\\"groupName\\":\\".\\",\\"ownerUserID\\":\\"870517699\\",\\"createTime\\":1657350707,\\"memberCount\\":7,\\"creatorUserID\\":\\"870517699\\"},\\"opUser\\":{\\"groupID\\":\\"2416895552\\",\\"userID\\":\\"3798435312\\",\\"roleLevel\\":1,\\"joinTime\\":1657350707,\\"nickname\\":\\"OpenIM-Blooming\\",\\"faceURL\\":\\"ic_avatar_06\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"870517699\\"},\\"invitedUserList\\":[{\\"groupID\\":\\"2416895552\\",\\"userID\\":\\"2507057290\\",\\"roleLevel\\":1,\\"joinTime\\":1657505093,\\"nickname\\":\\"袁帅\\",\\"faceURL\\":\\"ic_avatar_02\\",\\"operatorUserID\\":\\"3798435312\\"},{\\"groupID\\":\\"2416895552\\",\\"userID\\":\\"2539466345\\",\\"roleLevel\\":1,\\"joinTime\\":1657505093,\\"nickname\\":\\"林伯宇\\",\\"faceURL\\":\\"ic_avatar_02\\",\\"operatorUserID\\":\\"3798435312\\"},{\\"groupID\\":\\"2416895552\\",\\"userID\\":\\"565214579\\",\\"roleLevel\\":1,\\"joinTime\\":1657505093,\\"nickname\\":\\"弔儿子\\",\\"faceURL\\":\\"ic_avatar_06\\",\\"operatorUserID\\":\\"3798435312\\"}]}","defaultTips":" was invited into the group"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657505092758,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "single_3798435312",
      conversationType: 1,
      userID: "3798435312",
      groupID: "",
      showName: "OpenIM-Blooming",
      faceURL: "ic_avatar_06",
      recvMsgOpt: 0,
      unreadCount: 43,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"1347e1694a68d1ff54e67c731eaee796","serverMsgID":"4c23fb17575817affa6471a32ca767a2","createTime":1657502944258,"sendTime":1657502840270,"sessionType":1,"sendID":"3798435312","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"OpenIM-Blooming","senderFaceUrl":"ic_avatar_06","content":"really","seq":8822,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657502840270,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1643844429",
      conversationType: 2,
      userID: "",
      groupID: "1643844429",
      showName: "。",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 1,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"432ae999dca93e9931f9d987e64fbc8a","serverMsgID":"eebe4e79e5ff8c3b474f282fdf25d967","createTime":1657439244743,"sendTime":1657439244743,"sessionType":2,"sendID":"3935764022","recvID":"870517699","msgFrom":0,"contentType":1501,"platformID":0,"groupID":"1643844429","content":"{\\"detail\\":\\"CjEKCjE2NDM4NDQ0MjkSA+OAgjIKMzkzNTc2NDAyMjiNiKqWBkACWgozOTM1NzY0MDIyEqgBCgoxNjQzODQ0NDI5EgozOTM1NzY0MDIyGAIgjYiqlgYqBk9wZW5JTTJwaHR0cHM6Ly9zdG9yYWdlLW9ubGluZS5yZW50c29mdC5jbi9vcGVuaW0vMTY1NzI2NDkzMjI3NjkzNzY2MS00OTgwNTk5NTg4NzgxMzY4NDk0aW1hZ2VfY3JvcHBlcl8xNjU3MjY0OTI5MDMyLmpwZzgBSgozOTM1NzY0MDIyGqUBCgoxNjQzODQ0NDI5Egk4NzA1MTc2OTkYASCNiKqWBioG5bCP57qiMnBodHRwczovL3N0b3JhZ2Utb25saW5lLnJlbnRzb2Z0LmNuL29wZW5pbS8xNjU1NTMyNzEyNTM2MjQ3OTQ4LTgyNzMyOTA1Mzg2NTk4MDIyNjlpbWFnZV9jcm9wcGVyXzE2NTU1MzI3MTE0MjYuanBnSgozOTM1NzY0MDIyGqYBCgoxNjQzODQ0NDI5EgozOTM1NzY0MDIyGAIgjYiqlgYqBk9wZW5JTTJwaHR0cHM6Ly9zdG9yYWdlLW9ubGluZS5yZW50c29mdC5jbi9vcGVuaW0vMTY1NzI2NDkzMjI3NjkzNzY2MS00OTgwNTk5NTg4NzgxMzY4NDk0aW1hZ2VfY3JvcHBlcl8xNjU3MjY0OTI5MDMyLmpwZ0oKMzkzNTc2NDAyMiqmAQoKMTY0Mzg0NDQyORIKMzkzNTc2NDAyMhgCII2IqpYGKgZPcGVuSU0ycGh0dHBzOi8vc3RvcmFnZS1vbmxpbmUucmVudHNvZnQuY24vb3BlbmltLzE2NTcyNjQ5MzIyNzY5Mzc2NjEtNDk4MDU5OTU4ODc4MTM2ODQ5NGltYWdlX2Nyb3BwZXJfMTY1NzI2NDkyOTAzMi5qcGdKCjM5MzU3NjQwMjI=\\",\\"defaultTips\\":\\"OpenIM create the group\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1643844429\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"。\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"3935764022\\\\\\",\\\\\\"createTime\\\\\\":1657439245,\\\\\\"memberCount\\\\\\":2,\\\\\\"creatorUserID\\\\\\":\\\\\\"3935764022\\\\\\"},\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1643844429\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3935764022\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1657439245,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"3935764022\\\\\\"},\\\\\\"memberList\\\\\\":[{\\\\\\"groupID\\\\\\":\\\\\\"1643844429\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657439245,\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3935764022\\\\\\"},{\\\\\\"groupID\\\\\\":\\\\\\"1643844429\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3935764022\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1657439245,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3935764022\\\\\\"}],\\\\\\"groupOwnerUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1643844429\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3935764022\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1657439245,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"3935764022\\\\\\"}}\\"}","seq":8792,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"1643844429\\",\\"groupName\\":\\"。\\",\\"ownerUserID\\":\\"3935764022\\",\\"createTime\\":1657439245,\\"memberCount\\":2,\\"creatorUserID\\":\\"3935764022\\"},\\"opUser\\":{\\"groupID\\":\\"1643844429\\",\\"userID\\":\\"3935764022\\",\\"roleLevel\\":2,\\"joinTime\\":1657439245,\\"nickname\\":\\"OpenIM\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"3935764022\\"},\\"memberList\\":[{\\"groupID\\":\\"1643844429\\",\\"userID\\":\\"870517699\\",\\"roleLevel\\":1,\\"joinTime\\":1657439245,\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"operatorUserID\\":\\"3935764022\\"},{\\"groupID\\":\\"1643844429\\",\\"userID\\":\\"3935764022\\",\\"roleLevel\\":2,\\"joinTime\\":1657439245,\\"nickname\\":\\"OpenIM\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\",\\"operatorUserID\\":\\"3935764022\\"}],\\"groupOwnerUser\\":{\\"groupID\\":\\"1643844429\\",\\"userID\\":\\"3935764022\\",\\"roleLevel\\":2,\\"joinTime\\":1657439245,\\"nickname\\":\\"OpenIM\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\",\\"operatorUserID\\":\\"3935764022\\"}}","defaultTips":"OpenIM create the group"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657439244743,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "single_3493949860",
      conversationType: 1,
      userID: "3493949860",
      groupID: "",
      showName: "OpenIM-sk",
      faceURL:
        "https://storage-online.rentsoft.cn/openim/1655861026796104324-1488665021329751810image_cropper_1655861022512.jpg",
      recvMsgOpt: 2,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"b3faa9f32eeca7519dd77f2ea03c6a5b","serverMsgID":"17058cdeb95afb47692609a04f2cd5f1","createTime":1657350748507,"sendTime":1657350751218,"sessionType":1,"sendID":"870517699","recvID":"3493949860","msgFrom":100,"contentType":107,"platformID":2,"senderNickname":"小红","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","content":"{\\"title\\":\\"聊天记录\\",\\"abstractList\\":[\\"mjiee:欢迎mjiee加入部门\\",\\"我想问一下:欢迎我想问一下加入部门\\",\\"小红hh:欢迎我想问一下加入部门\\"],\\"multiMessage\\":[{\\"clientMsgID\\":\\"02b88e001819fbc0eaa53a87355c18db\\",\\"serverMsgID\\":\\"1e4a1fae5ad16d1705f28637bc845052\\",\\"createTime\\":1657293958090,\\"sendTime\\":1657293958091,\\"sessionType\\":2,\\"sendID\\":\\"791444412\\",\\"recvID\\":\\"870517699\\",\\"msgFrom\\":100,\\"contentType\\":101,\\"platformID\\":0,\\"senderNickname\\":\\"mjiee\\",\\"senderFaceUrl\\":\\"ic_avatar_01\\",\\"groupID\\":\\"200192428\\",\\"content\\":\\"欢迎mjiee加入部门\\",\\"seq\\":8655,\\"isRead\\":false,\\"status\\":2,\\"offlinePush\\":{},\\"pictureElem\\":{\\"sourcePicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"bigPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"snapshotPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0}},\\"soundElem\\":{\\"dataSize\\":0,\\"duration\\":0},\\"videoElem\\":{\\"videoSize\\":0,\\"duration\\":0,\\"snapshotSize\\":0,\\"snapshotWidth\\":0,\\"snapshotHeight\\":0},\\"fileElem\\":{\\"fileSize\\":0},\\"mergeElem\\":{},\\"atElem\\":{\\"isAtSelf\\":false},\\"faceElem\\":{\\"index\\":0},\\"locationElem\\":{\\"longitude\\":0,\\"latitude\\":0},\\"customElem\\":{},\\"quoteElem\\":{},\\"notificationElem\\":{},\\"messageEntityElem\\":{},\\"attachedInfoElem\\":{\\"groupHasReadInfo\\":{\\"hasReadCount\\":0,\\"groupMemberCount\\":0},\\"isPrivateChat\\":false,\\"hasReadTime\\":0,\\"notSenderNotificationPush\\":false}},{\\"clientMsgID\\":\\"099d92d7c70da7a9cfc707855573a9ac\\",\\"serverMsgID\\":\\"a3f78645060ddff1efb48a84da228cf0\\",\\"createTime\\":1657348053906,\\"sendTime\\":1657348053907,\\"sessionType\\":2,\\"sendID\\":\\"4118219741\\",\\"recvID\\":\\"870517699\\",\\"msgFrom\\":100,\\"contentType\\":101,\\"platformID\\":0,\\"senderNickname\\":\\"我想问一下\\",\\"senderFaceUrl\\":\\"ic_avatar_02\\",\\"groupID\\":\\"200192428\\",\\"content\\":\\"欢迎我想问一下加入部门\\",\\"seq\\":8670,\\"isRead\\":false,\\"status\\":2,\\"offlinePush\\":{},\\"pictureElem\\":{\\"sourcePicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"bigPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"snapshotPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0}},\\"soundElem\\":{\\"dataSize\\":0,\\"duration\\":0},\\"videoElem\\":{\\"videoSize\\":0,\\"duration\\":0,\\"snapshotSize\\":0,\\"snapshotWidth\\":0,\\"snapshotHeight\\":0},\\"fileElem\\":{\\"fileSize\\":0},\\"mergeElem\\":{},\\"atElem\\":{\\"isAtSelf\\":false},\\"faceElem\\":{\\"index\\":0},\\"locationElem\\":{\\"longitude\\":0,\\"latitude\\":0},\\"customElem\\":{},\\"quoteElem\\":{},\\"notificationElem\\":{},\\"messageEntityElem\\":{},\\"attachedInfoElem\\":{\\"groupHasReadInfo\\":{\\"hasReadCount\\":0,\\"groupMemberCount\\":0},\\"isPrivateChat\\":false,\\"hasReadTime\\":0,\\"notSenderNotificationPush\\":false}},{\\"clientMsgID\\":\\"257170b1ac8db4046f3c7abefe42aea1\\",\\"serverMsgID\\":\\"35530d19daec11a568f02f120d4e23a3\\",\\"createTime\\":1657350741678,\\"sendTime\\":1657350741878,\\"sessionType\\":2,\\"sendID\\":\\"870517699\\",\\"recvID\\":\\"870517699\\",\\"msgFrom\\":100,\\"contentType\\":101,\\"platformID\\":2,\\"senderNickname\\":\\"小红hh\\",\\"senderFaceUrl\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"groupID\\":\\"200192428\\",\\"content\\":\\"欢迎我想问一下加入部门\\",\\"seq\\":8702,\\"isRead\\":false,\\"status\\":2,\\"offlinePush\\":{},\\"attachedInfo\\":\\"{\\\\\\"groupHasReadInfo\\\\\\":{\\\\\\"hasReadCount\\\\\\":0,\\\\\\"groupMemberCount\\\\\\":314},\\\\\\"isPrivateChat\\\\\\":false,\\\\\\"hasReadTime\\\\\\":0,\\\\\\"notSenderNotificationPush\\\\\\":false}\\",\\"pictureElem\\":{\\"sourcePicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"bigPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"snapshotPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0}},\\"soundElem\\":{\\"dataSize\\":0,\\"duration\\":0},\\"videoElem\\":{\\"videoSize\\":0,\\"duration\\":0,\\"snapshotSize\\":0,\\"snapshotWidth\\":0,\\"snapshotHeight\\":0},\\"fileElem\\":{\\"fileSize\\":0},\\"mergeElem\\":{},\\"atElem\\":{\\"isAtSelf\\":false},\\"faceElem\\":{\\"index\\":0},\\"locationElem\\":{\\"longitude\\":0,\\"latitude\\":0},\\"customElem\\":{},\\"quoteElem\\":{},\\"notificationElem\\":{},\\"messageEntityElem\\":{},\\"attachedInfoElem\\":{\\"groupHasReadInfo\\":{\\"hasReadCount\\":0,\\"groupMemberCount\\":314},\\"isPrivateChat\\":false,\\"hasReadTime\\":0,\\"notSenderNotificationPush\\":false}}]}","seq":8703,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{"title":"聊天记录","abstractList":["mjiee:欢迎mjiee加入部门","我想问一下:欢迎我想问一下加入部门","小红hh:欢迎我想问一下加入部门"],"multiMessage":[{"clientMsgID":"02b88e001819fbc0eaa53a87355c18db","serverMsgID":"1e4a1fae5ad16d1705f28637bc845052","createTime":1657293958090,"sendTime":1657293958091,"sessionType":2,"sendID":"791444412","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":0,"senderNickname":"mjiee","senderFaceUrl":"ic_avatar_01","groupID":"200192428","content":"欢迎mjiee加入部门","seq":8655,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}},{"clientMsgID":"099d92d7c70da7a9cfc707855573a9ac","serverMsgID":"a3f78645060ddff1efb48a84da228cf0","createTime":1657348053906,"sendTime":1657348053907,"sessionType":2,"sendID":"4118219741","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":0,"senderNickname":"我想问一下","senderFaceUrl":"ic_avatar_02","groupID":"200192428","content":"欢迎我想问一下加入部门","seq":8670,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}},{"clientMsgID":"257170b1ac8db4046f3c7abefe42aea1","serverMsgID":"35530d19daec11a568f02f120d4e23a3","createTime":1657350741678,"sendTime":1657350741878,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"小红hh","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","groupID":"200192428","content":"欢迎我想问一下加入部门","seq":8702,"isRead":false,"status":2,"offlinePush":{},"attachedInfo":"{\\"groupHasReadInfo\\":{\\"hasReadCount\\":0,\\"groupMemberCount\\":314},\\"isPrivateChat\\":false,\\"hasReadTime\\":0,\\"notSenderNotificationPush\\":false}","pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":314},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}]},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657350751218,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "single_3935764022",
      conversationType: 1,
      userID: "3935764022",
      groupID: "",
      showName: "OpenIM",
      faceURL:
        "https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"ff0b3775e37a282d0eaf65061da18cd6","serverMsgID":"27e9a282418e49b2ff9c68d42a090c2d","createTime":1657350587747,"sendTime":1657350587965,"sessionType":1,"sendID":"870517699","recvID":"3935764022","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"小红","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","content":"hello","seq":8700,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657350587965,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_431366583",
      conversationType: 2,
      userID: "",
      groupID: "431366583",
      showName: "hello",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"6f24e093095e56d83642531edeb9ae87","serverMsgID":"64d51db3a251812d5890bd779f936574","createTime":1657350020652,"sendTime":1657350021374,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":100,"contentType":102,"platformID":2,"senderNickname":"小红","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","groupID":"431366583","content":"{\\"sourcePath\\":\\"/storage/emulated/0/Android/data/com.openIm.app/apps/__UNI__EC966F0/doc/uniapp_temp/compressed/1657350020469_Screenshot_20220709_144351_com.openIm.app.jpg\\",\\"sourcePicture\\":{\\"uuid\\":\\"1657350020804722407-2477346412368114538.jpg\\",\\"type\\":\\"image/jpeg\\",\\"size\\":53932,\\"width\\":1080,\\"height\\":2340,\\"url\\":\\"https://storage-online.rentsoft.cn/openim/1657350020804722407-2477346412368114538.jpg\\"},\\"bigPicture\\":{\\"size\\":0,\\"width\\":0,\\"height\\":0},\\"snapshotPicture\\":{\\"size\\":0,\\"width\\":200,\\"height\\":200,\\"url\\":\\"https://storage-online.rentsoft.cn/openim/1657350020804722407-2477346412368114538.jpg?imageView2/2/w/200/h/200\\"}}","seq":8687,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePath":"/storage/emulated/0/Android/data/com.openIm.app/apps/__UNI__EC966F0/doc/uniapp_temp/compressed/1657350020469_Screenshot_20220709_144351_com.openIm.app.jpg","sourcePicture":{"uuid":"1657350020804722407-2477346412368114538.jpg","type":"image/jpeg","size":53932,"width":1080,"height":2340,"url":"https://storage-online.rentsoft.cn/openim/1657350020804722407-2477346412368114538.jpg"},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":200,"height":200,"url":"https://storage-online.rentsoft.cn/openim/1657350020804722407-2477346412368114538.jpg?imageView2/2/w/200/h/200"}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657350021374,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_857447475",
      conversationType: 2,
      userID: "",
      groupID: "857447475",
      showName: "测试测试",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"1b947768a48acb549a2d5297514546ba","serverMsgID":"d7cf6fb53825c440a11ce86f15bedf3f","createTime":1657349646637,"sendTime":1657349646637,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":0,"contentType":1504,"platformID":0,"groupID":"857447475","content":"{\\"detail\\":\\"CkkKCTg1NzQ0NzQ3NRIM5rWL6K+V5rWL6K+VGg5jY2MzNTXkuozml6UxMTIKMzc5ODQzNTMxMjiQstCVBkACWgozNzk4NDM1MzEyEpIBCgk4NTc0NDc0NzUSCTg3MDUxNzY5OSoG5bCP57qiMnBodHRwczovL3N0b3JhZ2Utb25saW5lLnJlbnRzb2Z0LmNuL29wZW5pbS8xNjU1NTMyNzEyNTM2MjQ3OTQ4LTgyNzMyOTA1Mzg2NTk4MDIyNjlpbWFnZV9jcm9wcGVyXzE2NTU1MzI3MTE0MjYuanBnOAE=\\",\\"defaultTips\\":\\"小红 quit group chat\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"857447475\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"测试测试\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"ccc355二日11\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"3798435312\\\\\\",\\\\\\"createTime\\\\\\":1655970064,\\\\\\"memberCount\\\\\\":2,\\\\\\"creatorUserID\\\\\\":\\\\\\"3798435312\\\\\\"},\\\\\\"quitUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"857447475\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1}}\\"}","seq":8682,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"857447475\\",\\"groupName\\":\\"测试测试\\",\\"notification\\":\\"ccc355二日11\\",\\"ownerUserID\\":\\"3798435312\\",\\"createTime\\":1655970064,\\"memberCount\\":2,\\"creatorUserID\\":\\"3798435312\\"},\\"quitUser\\":{\\"groupID\\":\\"857447475\\",\\"userID\\":\\"870517699\\",\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"appMangerLevel\\":1}}","defaultTips":"小红 quit group chat"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657349646637,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_2360404045",
      conversationType: 2,
      userID: "",
      groupID: "2360404045",
      showName: "。。",
      faceURL:
        "https://storage-online.rentsoft.cn/openim/1657185293206081695-3423389732713730079image_cropper_1657185290216.jpg",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"9268e6c39e70efc7206aaf35ea0746dd","serverMsgID":"a31d0c01574f834467f5e82e81efd810","createTime":1657348842347,"sendTime":1657348842348,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":0,"contentType":1502,"platformID":0,"groupID":"2360404045","content":"{\\"detail\\":\\"CqYBCgoyMzYwNDA0MDQ1Egk4NzA1MTc2OTkYAiCOyJqWBioG5bCP57qiMnBodHRwczovL3N0b3JhZ2Utb25saW5lLnJlbnRzb2Z0LmNuL29wZW5pbS8xNjU1NTMyNzEyNTM2MjQ3OTQ4LTgyNzMyOTA1Mzg2NTk4MDIyNjlpbWFnZV9jcm9wcGVyXzE2NTU1MzI3MTE0MjYuanBnOAFKCTg3MDUxNzY5ORo0CgoyMzYwNDA0MDQ1Ggblk4jlk4gyCTg3MDUxNzY5OTiOyJqWBkACWgk4NzA1MTc2OTlgAg==\\",\\"defaultTips\\":\\"小红 modified the group profile\\",\\"jsonDetail\\":\\"{\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"2360404045\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1657185294,\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"2360404045\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"哈哈\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"createTime\\\\\\":1657185294,\\\\\\"memberCount\\\\\\":2,\\\\\\"creatorUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"groupType\\\\\\":2}}\\"}","seq":8677,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"opUser\\":{\\"groupID\\":\\"2360404045\\",\\"userID\\":\\"870517699\\",\\"roleLevel\\":2,\\"joinTime\\":1657185294,\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"870517699\\"},\\"group\\":{\\"groupID\\":\\"2360404045\\",\\"notification\\":\\"哈哈\\",\\"ownerUserID\\":\\"870517699\\",\\"createTime\\":1657185294,\\"memberCount\\":2,\\"creatorUserID\\":\\"870517699\\",\\"groupType\\":2}}","defaultTips":"小红 modified the group profile"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657348842348,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_4277507423",
      conversationType: 2,
      userID: "",
      groupID: "4277507423",
      showName: "热的",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 3,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"ad421b58d895cad7164b784b8db049d0","serverMsgID":"030a72f908bae4b49fb115bc1713b300","createTime":1657273040043,"sendTime":1657272936027,"sessionType":2,"sendID":"3798435312","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"OpenIM-Blooming","senderFaceUrl":"ic_avatar_06","groupID":"4277507423","content":"4","seq":8620,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657272936027,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1196958345",
      conversationType: 2,
      userID: "",
      groupID: "1196958345",
      showName: "通天塔",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 5,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"799122fea3528b3007aab979577b1b64","serverMsgID":"03ed7e2ce865b8d6443bc1cada78b4e1","createTime":1657272511904,"sendTime":1657272511904,"sessionType":2,"sendID":"2911830477","recvID":"870517699","msgFrom":0,"contentType":1508,"platformID":0,"groupID":"1196958345","content":"{\\"detail\\":\\"CjcKCjExOTY5NTgzNDUSCemAmuWkqeWhlDIKMjkxMTgzMDQ3NzjR5p+WBkACWgoyOTExODMwNDc3EkQKCjExOTY5NTgzNDUSCjI5MTE4MzA0NzcYAiDR5p+WBioGaGloaWhpMgxpY19hdmF0YXJfMDU4AUoKMjkxMTgzMDQ3NxowCgoxMTk2OTU4MzQ1EgoyNTA3MDU3MjkwKgboooHluIUyDGljX2F2YXRhcl8wMjgBGpMBCgoxMTk2OTU4MzQ1Egk4NzA1MTc2OTkqBuWwj+e6ojJwaHR0cHM6Ly9zdG9yYWdlLW9ubGluZS5yZW50c29mdC5jbi9vcGVuaW0vMTY1NTUzMjcxMjUzNjI0Nzk0OC04MjczMjkwNTM4NjU5ODAyMjY5aW1hZ2VfY3JvcHBlcl8xNjU1NTMyNzExNDI2LmpwZzgBGjMKCjExOTY5NTgzNDUSCjI1Mzk0NjYzNDUqCeael+S8r+WuhzIMaWNfYXZhdGFyXzAyOAEaMgoKMTE5Njk1ODM0NRIJNTY1MjE0NTc5KgnlvJTlhL/lrZAyDGljX2F2YXRhcl8wNjgB\\",\\"defaultTips\\":\\" was kicked out of the group\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"通天塔\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"2911830477\\\\\\",\\\\\\"createTime\\\\\\":1657271121,\\\\\\"memberCount\\\\\\":2,\\\\\\"creatorUserID\\\\\\":\\\\\\"2911830477\\\\\\"},\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"2911830477\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1657271121,\\\\\\"nickname\\\\\\":\\\\\\"hihihi\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_05\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"2911830477\\\\\\"},\\\\\\"kickedUserList\\\\\\":[{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"2507057290\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"袁帅\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_02\\\\\\",\\\\\\"appMangerLevel\\\\\\":1},{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1},{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"2539466345\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"林伯宇\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_02\\\\\\",\\\\\\"appMangerLevel\\\\\\":1},{\\\\\\"groupID\\\\\\":\\\\\\"1196958345\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"565214579\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"弔儿子\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"ic_avatar_06\\\\\\",\\\\\\"appMangerLevel\\\\\\":1}]}\\"}","seq":8613,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"1196958345\\",\\"groupName\\":\\"通天塔\\",\\"ownerUserID\\":\\"2911830477\\",\\"createTime\\":1657271121,\\"memberCount\\":2,\\"creatorUserID\\":\\"2911830477\\"},\\"opUser\\":{\\"groupID\\":\\"1196958345\\",\\"userID\\":\\"2911830477\\",\\"roleLevel\\":2,\\"joinTime\\":1657271121,\\"nickname\\":\\"hihihi\\",\\"faceURL\\":\\"ic_avatar_05\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"2911830477\\"},\\"kickedUserList\\":[{\\"groupID\\":\\"1196958345\\",\\"userID\\":\\"2507057290\\",\\"nickname\\":\\"袁帅\\",\\"faceURL\\":\\"ic_avatar_02\\",\\"appMangerLevel\\":1},{\\"groupID\\":\\"1196958345\\",\\"userID\\":\\"870517699\\",\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"appMangerLevel\\":1},{\\"groupID\\":\\"1196958345\\",\\"userID\\":\\"2539466345\\",\\"nickname\\":\\"林伯宇\\",\\"faceURL\\":\\"ic_avatar_02\\",\\"appMangerLevel\\":1},{\\"groupID\\":\\"1196958345\\",\\"userID\\":\\"565214579\\",\\"nickname\\":\\"弔儿子\\",\\"faceURL\\":\\"ic_avatar_06\\",\\"appMangerLevel\\":1}]}","defaultTips":" was kicked out of the group"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657272511904,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_51724656",
      conversationType: 2,
      userID: "",
      groupID: "51724656",
      showName: "group name",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"ba8559449571ba952800a96bb81c61f1","serverMsgID":"3a6eaff4729f602c956c525c68c3d6fd","createTime":1657265169167,"sendTime":1657265169167,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":0,"contentType":1510,"platformID":0,"groupID":"51724656","content":"{\\"detail\\":\\"CjQKCDUxNzI0NjU2Egpncm91cCBuYW1lMgk4NzA1MTc2OTk40eCPlgZAA1oJODcwNTE3Njk5EqMBCgg1MTcyNDY1NhIKMzkzNTc2NDAyMhgBIJG4n5YGKgZPcGVuSU0ycGh0dHBzOi8vc3RvcmFnZS1vbmxpbmUucmVudHNvZnQuY24vb3BlbmltLzE2NTcyNjQ5MzIyNzY5Mzc2NjEtNDk4MDU5OTU4ODc4MTM2ODQ5NGltYWdlX2Nyb3BwZXJfMTY1NzI2NDkyOTAzMi5qcGdKCTg3MDUxNzY5OQ==\\",\\"defaultTips\\":\\" entered the group\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"51724656\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"group name\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"createTime\\\\\\":1657008209,\\\\\\"memberCount\\\\\\":3,\\\\\\"creatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"entrantUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"51724656\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"3935764022\\\\\\",\\\\\\"roleLevel\\\\\\":1,\\\\\\"joinTime\\\\\\":1657265169,\\\\\\"nickname\\\\\\":\\\\\\"OpenIM\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\\\\\",\\\\\\"operatorUserID\\\\\\":\\\\\\"870517699\\\\\\"}}\\"}","seq":8512,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"51724656\\",\\"groupName\\":\\"group name\\",\\"ownerUserID\\":\\"870517699\\",\\"createTime\\":1657008209,\\"memberCount\\":3,\\"creatorUserID\\":\\"870517699\\"},\\"entrantUser\\":{\\"groupID\\":\\"51724656\\",\\"userID\\":\\"3935764022\\",\\"roleLevel\\":1,\\"joinTime\\":1657265169,\\"nickname\\":\\"OpenIM\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1657264932276937661-4980599588781368494image_cropper_1657264929032.jpg\\",\\"operatorUserID\\":\\"870517699\\"}}","defaultTips":" entered the group"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657265169167,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_555349184",
      conversationType: 2,
      userID: "",
      groupID: "555349184",
      showName: "is黑恶",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 2,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"36a19cb1a88f312ed4a0737dcba2ac40","serverMsgID":"42c5a7f173697d97b447c3a2a84ca21e","createTime":1657264044632,"sendTime":1657263940595,"sessionType":2,"sendID":"3798435312","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"OpenIM-Blooming","senderFaceUrl":"ic_avatar_06","groupID":"555349184","content":"[晕][暴怒][眨眼][暴怒][无语]","seq":8471,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657263940595,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1460445720",
      conversationType: 2,
      userID: "",
      groupID: "1460445720",
      showName: "46有",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 3,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"3ca498ab1c602368ba2e288db719ad24","serverMsgID":"9475ce77ebf408bec6a50762880ef108","createTime":1657263151410,"sendTime":1657263047377,"sessionType":2,"sendID":"3798435312","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"OpenIM-Blooming","senderFaceUrl":"ic_avatar_06","groupID":"1460445720","content":"金宝贝","seq":8464,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657263047377,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_4293847457",
      conversationType: 2,
      userID: "",
      groupID: "4293847457",
      showName: "openIM体验群",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 2490,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"6cd0a4d438ad97de39c7c703ec8065f8","serverMsgID":"8d84b0c9f8b424d4e62b020b19401626","createTime":1657250328725,"sendTime":1657250328726,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":0,"contentType":1504,"platformID":0,"groupID":"4293847457","content":"{\\"detail\\":\\"ClgKCjQyOTM4NDc0NTcSD29wZW5JTeS9k+mqjOe+pBoP5rWL6K+V576k5YWs5ZGKIgZvcGVuSU0yCjM0OTM5NDk4NjA4+JGxlQZAsAdaC29wZW5JTUFkbWluEpMBCgo0MjkzODQ3NDU3Egk4NzA1MTc2OTkqBuWwj+e6ojJwaHR0cHM6Ly9zdG9yYWdlLW9ubGluZS5yZW50c29mdC5jbi9vcGVuaW0vMTY1NTUzMjcxMjUzNjI0Nzk0OC04MjczMjkwNTM4NjU5ODAyMjY5aW1hZ2VfY3JvcHBlcl8xNjU1NTMyNzExNDI2LmpwZzgB\\",\\"defaultTips\\":\\"小红 quit group chat\\",\\"jsonDetail\\":\\"{\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"4293847457\\\\\\",\\\\\\"groupName\\\\\\":\\\\\\"openIM体验群\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"测试群公告\\\\\\",\\\\\\"introduction\\\\\\":\\\\\\"openIM\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"3493949860\\\\\\",\\\\\\"createTime\\\\\\":1655458040,\\\\\\"memberCount\\\\\\":944,\\\\\\"creatorUserID\\\\\\":\\\\\\"openIMAdmin\\\\\\"},\\\\\\"quitUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"4293847457\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1}}\\"}","seq":8402,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"group\\":{\\"groupID\\":\\"4293847457\\",\\"groupName\\":\\"openIM体验群\\",\\"notification\\":\\"测试群公告\\",\\"introduction\\":\\"openIM\\",\\"ownerUserID\\":\\"3493949860\\",\\"createTime\\":1655458040,\\"memberCount\\":944,\\"creatorUserID\\":\\"openIMAdmin\\"},\\"quitUser\\":{\\"groupID\\":\\"4293847457\\",\\"userID\\":\\"870517699\\",\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"appMangerLevel\\":1}}","defaultTips":"小红 quit group chat"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657250328726,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1386476439",
      conversationType: 2,
      userID: "",
      groupID: "1386476439",
      showName: "修改",
      faceURL:
        "https://storage-online.rentsoft.cn/openim/1656407746264481260-1793870773209338684image_cropper_1656407744810.jpg",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"f92361703a1d80afb62cc716e0c8d3ef","serverMsgID":"8bd421518fc383024ef3c6b92f921dda","createTime":1657250086261,"sendTime":1657250086261,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":0,"contentType":1502,"platformID":0,"groupID":"1386476439","content":"{\\"detail\\":\\"CqYBCgoxMzg2NDc2NDM5Egk4NzA1MTc2OTkYAiCvieuVBioG5bCP57qiMnBodHRwczovL3N0b3JhZ2Utb25saW5lLnJlbnRzb2Z0LmNuL29wZW5pbS8xNjU1NTMyNzEyNTM2MjQ3OTQ4LTgyNzMyOTA1Mzg2NTk4MDIyNjlpbWFnZV9jcm9wcGVyXzE2NTU1MzI3MTE0MjYuanBnOAFKCTg3MDUxNzY5ORpKCgoxMzg2NDc2NDM5Gh7llpTnv6Hnv6Dlj7Dlj43mmKDlh7rkuIDluYXluYUyCTg3MDUxNzY5OTivieuVBkADWgk4NzA1MTc2OTk=\\",\\"defaultTips\\":\\"小红 modified the group profile\\",\\"jsonDetail\\":\\"{\\\\\\"opUser\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1386476439\\\\\\",\\\\\\"userID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"roleLevel\\\\\\":2,\\\\\\"joinTime\\\\\\":1656407215,\\\\\\"nickname\\\\\\":\\\\\\"小红\\\\\\",\\\\\\"faceURL\\\\\\":\\\\\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\\\\\",\\\\\\"appMangerLevel\\\\\\":1,\\\\\\"operatorUserID\\\\\\":\\\\\\"870517699\\\\\\"},\\\\\\"group\\\\\\":{\\\\\\"groupID\\\\\\":\\\\\\"1386476439\\\\\\",\\\\\\"notification\\\\\\":\\\\\\"喔翡翠台反映出一幅幅\\\\\\",\\\\\\"ownerUserID\\\\\\":\\\\\\"870517699\\\\\\",\\\\\\"createTime\\\\\\":1656407215,\\\\\\"memberCount\\\\\\":3,\\\\\\"creatorUserID\\\\\\":\\\\\\"870517699\\\\\\"}}\\"}","seq":8391,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"opUser\\":{\\"groupID\\":\\"1386476439\\",\\"userID\\":\\"870517699\\",\\"roleLevel\\":2,\\"joinTime\\":1656407215,\\"nickname\\":\\"小红\\",\\"faceURL\\":\\"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg\\",\\"appMangerLevel\\":1,\\"operatorUserID\\":\\"870517699\\"},\\"group\\":{\\"groupID\\":\\"1386476439\\",\\"notification\\":\\"喔翡翠台反映出一幅幅\\",\\"ownerUserID\\":\\"870517699\\",\\"createTime\\":1656407215,\\"memberCount\\":3,\\"creatorUserID\\":\\"870517699\\"}}","defaultTips":"小红 modified the group profile"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657250086261,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "single_383921248",
      conversationType: 1,
      userID: "383921248",
      groupID: "",
      showName: "A",
      faceURL: "ic_avatar_03",
      recvMsgOpt: 2,
      unreadCount: 1,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"5e92282490b39eeeae11a7b59984819a","serverMsgID":"58bfac9e94771886f6953fbfc5612e6e","createTime":1657246562719,"sendTime":1657246562594,"sessionType":1,"sendID":"870517699","recvID":"383921248","msgFrom":100,"contentType":111,"platformID":2,"senderNickname":"小红","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","content":"1a497552ecd3f816f68a3c635fdf9146","seq":8262,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657246562594,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "group_1236180320",
      conversationType: 2,
      userID: "",
      groupID: "1236180320",
      showName: "？",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"fe546e0266db4ec695ae31021f389392","serverMsgID":"c25e559631e16d8e36d89451450020dc","createTime":1657007321469,"sendTime":1657007321446,"sessionType":2,"sendID":"870517699","recvID":"870517699","msgFrom":100,"contentType":101,"platformID":2,"senderNickname":"小红啊","senderFaceUrl":"https://storage-online.rentsoft.cn/openim/1655532712536247948-8273290538659802269image_cropper_1655532711426.jpg","groupID":"1236180320","content":"搜索不到","seq":6661,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1657007321446,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: true,
      attachedInfo: "",
      ex: "",
    },
    {
      conversationID: "notification_openIM123456",
      conversationType: 4,
      userID: "openIM123456",
      groupID: "",
      showName: "系统通知",
      faceURL: "",
      recvMsgOpt: 0,
      unreadCount: 0,
      groupAtType: 0,
      latestMsg:
        '{"clientMsgID":"252556531c9fd8803aef27377114d7bf","serverMsgID":"0d30976a5b370d147f9ba881359d2965","createTime":1655532611680,"sendTime":1655532611680,"sessionType":4,"sendID":"openIM123456","recvID":"870517699","msgFrom":200,"contentType":1400,"platformID":0,"content":"{\\"jsonDetail\\":\\"{\\\\\\"notificationName\\\\\\":\\\\\\"入职通知\\\\\\",\\\\\\"notificationFaceURL\\\\\\":\\\\\\"\\\\\\",\\\\\\"notificationType\\\\\\":1,\\\\\\"text\\\\\\":\\\\\\"欢迎你入职公司\\\\\\",\\\\\\"url\\\\\\":\\\\\\"\\\\\\",\\\\\\"mixType\\\\\\":0,\\\\\\"pictureElem\\\\\\":{\\\\\\"SourcePath\\\\\\":\\\\\\"\\\\\\",\\\\\\"SourcePicture\\\\\\":{\\\\\\"UUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"Type\\\\\\":\\\\\\"\\\\\\",\\\\\\"Size\\\\\\":0,\\\\\\"Width\\\\\\":0,\\\\\\"Height\\\\\\":0,\\\\\\"Url\\\\\\":\\\\\\"\\\\\\"},\\\\\\"BigPicture\\\\\\":{\\\\\\"UUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"Type\\\\\\":\\\\\\"\\\\\\",\\\\\\"Size\\\\\\":0,\\\\\\"Width\\\\\\":0,\\\\\\"Height\\\\\\":0,\\\\\\"Url\\\\\\":\\\\\\"\\\\\\"},\\\\\\"SnapshotPicture\\\\\\":{\\\\\\"UUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"Type\\\\\\":\\\\\\"\\\\\\",\\\\\\"Size\\\\\\":0,\\\\\\"Width\\\\\\":0,\\\\\\"Height\\\\\\":0,\\\\\\"Url\\\\\\":\\\\\\"\\\\\\"}},\\\\\\"soundElem\\\\\\":{\\\\\\"UUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"SoundPath\\\\\\":\\\\\\"\\\\\\",\\\\\\"SourceURL\\\\\\":\\\\\\"\\\\\\",\\\\\\"DataSize\\\\\\":0,\\\\\\"Duration\\\\\\":0},\\\\\\"videoElem\\\\\\":{\\\\\\"VideoPath\\\\\\":\\\\\\"\\\\\\",\\\\\\"VideoUUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"VideoURL\\\\\\":\\\\\\"\\\\\\",\\\\\\"VideoType\\\\\\":\\\\\\"\\\\\\",\\\\\\"VideoSize\\\\\\":0,\\\\\\"Duration\\\\\\":0,\\\\\\"SnapshotPath\\\\\\":\\\\\\"\\\\\\",\\\\\\"SnapshotUUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"SnapshotSize\\\\\\":0,\\\\\\"SnapshotURL\\\\\\":\\\\\\"\\\\\\",\\\\\\"SnapshotWidth\\\\\\":0,\\\\\\"SnapshotHeight\\\\\\":0},\\\\\\"fileElem\\\\\\":{\\\\\\"FilePath\\\\\\":\\\\\\"\\\\\\",\\\\\\"UUID\\\\\\":\\\\\\"\\\\\\",\\\\\\"SourceURL\\\\\\":\\\\\\"\\\\\\",\\\\\\"FileName\\\\\\":\\\\\\"\\\\\\",\\\\\\"FileSize\\\\\\":0},\\\\\\"ex\\\\\\":\\\\\\"\\\\\\"}\\"}","seq":9,"isRead":false,"status":2,"offlinePush":{},"pictureElem":{"sourcePicture":{"size":0,"width":0,"height":0},"bigPicture":{"size":0,"width":0,"height":0},"snapshotPicture":{"size":0,"width":0,"height":0}},"soundElem":{"dataSize":0,"duration":0},"videoElem":{"videoSize":0,"duration":0,"snapshotSize":0,"snapshotWidth":0,"snapshotHeight":0},"fileElem":{"fileSize":0},"mergeElem":{},"atElem":{"isAtSelf":false},"faceElem":{"index":0},"locationElem":{"longitude":0,"latitude":0},"customElem":{},"quoteElem":{},"notificationElem":{"detail":"{\\"notificationName\\":\\"入职通知\\",\\"notificationFaceURL\\":\\"\\",\\"notificationType\\":1,\\"text\\":\\"欢迎你入职公司\\",\\"url\\":\\"\\",\\"mixType\\":0,\\"pictureElem\\":{\\"SourcePath\\":\\"\\",\\"SourcePicture\\":{\\"UUID\\":\\"\\",\\"Type\\":\\"\\",\\"Size\\":0,\\"Width\\":0,\\"Height\\":0,\\"Url\\":\\"\\"},\\"BigPicture\\":{\\"UUID\\":\\"\\",\\"Type\\":\\"\\",\\"Size\\":0,\\"Width\\":0,\\"Height\\":0,\\"Url\\":\\"\\"},\\"SnapshotPicture\\":{\\"UUID\\":\\"\\",\\"Type\\":\\"\\",\\"Size\\":0,\\"Width\\":0,\\"Height\\":0,\\"Url\\":\\"\\"}},\\"soundElem\\":{\\"UUID\\":\\"\\",\\"SoundPath\\":\\"\\",\\"SourceURL\\":\\"\\",\\"DataSize\\":0,\\"Duration\\":0},\\"videoElem\\":{\\"VideoPath\\":\\"\\",\\"VideoUUID\\":\\"\\",\\"VideoURL\\":\\"\\",\\"VideoType\\":\\"\\",\\"VideoSize\\":0,\\"Duration\\":0,\\"SnapshotPath\\":\\"\\",\\"SnapshotUUID\\":\\"\\",\\"SnapshotSize\\":0,\\"SnapshotURL\\":\\"\\",\\"SnapshotWidth\\":0,\\"SnapshotHeight\\":0},\\"fileElem\\":{\\"FilePath\\":\\"\\",\\"UUID\\":\\"\\",\\"SourceURL\\":\\"\\",\\"FileName\\":\\"\\",\\"FileSize\\":0},\\"ex\\":\\"\\"}"},"messageEntityElem":{},"attachedInfoElem":{"groupHasReadInfo":{"hasReadCount":0,"groupMemberCount":0},"isPrivateChat":false,"hasReadTime":0,"notSenderNotificationPush":false}}',
      latestMsgSendTime: 1655532611680,
      draftText: "",
      draftTextTime: 0,
      isPinned: false,
      isPrivateChat: false,
      isNotInGroup: false,
      attachedInfo: "",
      ex: "",
    },
  ],
  messageType: {
    TEXTMESSAGE: 101,
    PICTUREMESSAGE: 102,
    VOICEMESSAGE: 103,
    VIDEOMESSAGE: 104,
    FILEMESSAGE: 105,
    ATTEXTMESSAGE: 106,
    MERGERMESSAGE: 107,
    CARDMESSAGE: 108,
    LOCATIONMESSAGE: 109,
    CUSTOMMESSAGE: 110,
    REVOKEMESSAGE: 111,
    HASREADRECEIPTMESSAGE: 112,
    TYPINGMESSAGE: 113,
    QUOTEMESSAGE: 114,
    FACEMESSAGE: 115,
    FRIENDAPPLICATIONAPPROVED: 1201,
    FRIENDAPPLICATIONREJECTED: 1202,
    FRIENDAPPLICATIONADDED: 1203,
    FRIENDADDED: 1204,
    FRIENDDELETED: 1205,
    FRIENDREMARKSET: 1206,
    BLACKADDED: 1207,
    BLACKDELETED: 1208,
    SELFINFOUPDATED: 1303,
    NOTIFICATION: 1400,
    GROUPCREATED: 1501,
    GROUPINFOUPDATED: 1502,
    JOINGROUPAPPLICATIONADDED: 1503,
    GROUPMEMBERQUIT: 1504,
    GROUPAPPLICATIONACCEPTED: 1505,
    GROUPAPPLICATIONREJECTED: 1506,
    GROUPOWNERTRANSFERRED: 1507,
    MEMBERKICKED: 1508,
    MEMBERINVITED: 1509,
    MEMBERENTER: 1510,
    GROUPDISMISSED: 1511,
    GROUPMEMBERMUTED: 1512,
    GROUPMEMBERCANCELMUTED: 1513,
    GROUPMUTED: 1514,
    GROUPCANCELMUTED: 1515,
    GROUPMEMBERINFOUPDATED: 1516,
    BURNMESSAGECHANGE: 1701,
  },
  successTimes: 0, //发送成功次数
  newMessageTimes: 0, //接收到新消息
  indexMessageTimes: 0, //首页刷新消息列表
  infoUpdateTimes: 0, //更新好友、群组成功次数
  operationMessageItem: null, //操作的某条消息
  uploadFileTimes: 0, //上传文件操作次数
  uploadFileRes: "",
  openFile: {
    url: "https://www.zhenaisg.com/zagj_public/DOCUMENT/REAL/pdf/Q&A/LimianEn.pdf",
    fileName: "员工个人信息.doc",
    fileSize: 99,
  },
  fileHistory: [],
  sendMessageStatusList: [],
  conversationData: {
    userID: "",
    groupID: "",
  },
  localConversationMessageList: [
    {
      groupID: "",
      userID: "",
      currentUserID: "",
      messageList: [],
      draftText: "", //草稿
    },
  ],
  newMessageList: [],
  revokeMessageTimes: 0, //撤回消息次数
  revokeMessageList: [],
  clearHistoryMessageTimes: 0, //清空消息次数
  frinendChangeTimes: 0, //好友列表发生变化次数
  frinendInfoChangeTimes: 0, //好友信息发生变化次数
};
const mutations = {
  set_messageList(state, messageList) {
    state.messageList = messageList;
  },
  set_successTimes(state) {
    state.successTimes++;
  },
  set_newMessageTimes(state) {
    state.newMessageTimes++;
  },
  set_indexMessageTimes(state) {
    state.indexMessageTimes++;
  },
  set_infoUpdateTimes(state) {
    state.infoUpdateTimes++;
  },
  set_operationMessageItem(state, operationMessageItem = null) {
    state.operationMessageItem = operationMessageItem;
  },
  set_uploadFileRes(state, uploadFileRes = "") {
    state.uploadFileTimes++;
    state.uploadFileRes = uploadFileRes;
  },
  set_openFile(state, openFile = {}) {
    state.openFile = openFile;
  },
  clear_fileHistory(state) {
    state.fileHistory = [];
  },
  push_fileHistory(state, file = {}) {
    state.fileHistory.push(file);
  },
  remove_fileHistory(state, filePath) {
    const index = state.fileHistory.findIndex((i) => i.filePath === filePath);
    if (index > -1) {
      state.fileHistory.splice(index, 1);
    }
  },
  clear_sendMessageStatusList(state) {
    state.sendMessageStatusList = [];
  },
  set_sendMessageStatusList(state, messageItem, status, type = "normal") {
    state.sendMessageStatusList.push({
      messageItem,
      status,
      type,
    });
  },
  set_singleMessageStatusList(state, data) {
    const { message, status, errCode } = data;
    console.log("sendMessageStatus", status, message);
    //status:   0：发送中,1：发送成功,-1:发送失败
    const clientMsgID = message.clientMsgID;
    const index = state.sendMessageStatusList.findIndex(
      (i) => i.messageItem.messageContent.clientMsgID === clientMsgID
    );
    if (index >= 0 && status === -1) {
      const item = state.sendMessageStatusList[index];
      item.status = status;
      item.errCode = errCode;
      state.sendMessageStatusList.splice(index, 1, item);
    } else if (index >= 0 && status === 1) {
      if (message.videoElem && message.videoElem.snapshotPath) {
        uni.removeSavedFile({ filePath: message.videoElem.snapshotPath });
      } else if (message.soundPath) {
        uni.removeSavedFile({ filePath: message.soundPath });
      }
      state.sendMessageStatusList.splice(index, 1);
    }
  },
  set_conversationData(state, d = null) {
    state.conversationData = d;
  },
  set_newMessageList(state, item) {
    if (
      (item.sessionType === 1 &&
        state.conversationData &&
        item.sendID === state.conversationData.userID) ||
      ((item.sessionType === 2 || item.sessionType === 3) &&
        state.conversationData &&
        item.groupID === state.conversationData.groupID)
    ) {
      state.newMessageList.push(item);
    }
  },
  clear_newMessageList(state) {
    state.newMessageList = [];
  },
  set_revokeMessageTimes(state) {
    state.revokeMessageTimes++;
  },
  set_revokeMessageList(state, msgId) {
    state.revokeMessageList.push(msgId);
  },
  del_revokeMessageList(state, clientMsgID) {
    const index = state.revokeMessageList.findIndex(
      (cID) => cID === clientMsgID
    );
    if (index >= 0) {
      state.revokeMessageList.splice(index, 1);
    }
  },
  clear_revokeMessageList(state) {
    state.revokeMessageList = [];
  },
  set_clearHistoryMessageTimes(state) {
    state.clearHistoryMessageTimes++;
    state.indexMessageTimes++;
  },
  set_frinendChangeTimes(state) {
    state.frinendChangeTimes++;
  },
  set_frinendInfoChangeTimes(state) {
    state.frinendInfoChangeTimes++;
    state.indexMessageTimes++;
  },
  push_localConversationMessage(
    state,
    { groupID, userID, messageItem, currentUserID, im }
  ) {
    if (messageItem) {
      const index = state.localConversationMessageList.findIndex(
        (i) =>
          (groupID &&
            i.groupID === groupID &&
            i.currentUserID === currentUserID) ||
          (userID && i.userID === userID && i.currentUserID === currentUserID)
      );
      set_messageToLocal(messageItem, im);
      if (index >= 0) {
        state.localConversationMessageList[index].messageList.push(messageItem);
      } else {
        state.localConversationMessageList.push({
          groupID,
          userID,
          messageList: [messageItem],
          currentUserID,
          draftText: "",
        });
      }
    }
  },
  unshift_localConversationMessage(
    state,
    { groupID, userID, messageItem, currentUserID, im }
  ) {
    if (messageItem) {
      const index = state.localConversationMessageList.findIndex(
        (i) =>
          (groupID &&
            i.groupID === groupID &&
            i.currentUserID === currentUserID) ||
          (userID && i.userID === userID && i.currentUserID === currentUserID)
      );
      set_messageToLocal(messageItem, im);
      if (index >= 0) {
        state.localConversationMessageList[index].messageList.unshift(
          messageItem
        );
      } else {
        state.localConversationMessageList.push({
          groupID,
          userID,
          messageList: [messageItem],
          currentUserID,
          draftText: "",
        });
      }
    }
  },
  update_localConversationMessage(
    state,
    { groupID, userID, messageItem, currentUserID }
  ) {
    const index = state.localConversationMessageList.findIndex(
      (i) =>
        (groupID &&
          i.groupID === groupID &&
          i.currentUserID === currentUserID) ||
        (userID && i.userID === userID && i.currentUserID === currentUserID)
    );
    if (index >= 0) {
      const messageIndex = state.localConversationMessageList[
        index
      ].messageList.findIndex((i) => i.clientMsgID === messageItem.clientMsgID);
      if (messageIndex >= 0) {
        state.localConversationMessageList[index].messageList.splice(
          messageIndex,
          1,
          messageItem
        );
      } else {
        state.localConversationMessageList[index].messageList.push(messageItem);
      }
    }
  },
  del_localConversationMessage(
    state,
    { groupID, userID, messageItem, currentUserID }
  ) {
    const index = state.localConversationMessageList.findIndex(
      (i) =>
        (groupID &&
          i.groupID === groupID &&
          i.currentUserID === currentUserID) ||
        (userID && i.userID === userID && i.currentUserID === currentUserID)
    );
    if (index >= 0) {
      const messageIndex = state.localConversationMessageList[
        index
      ].messageList.findIndex((i) => i.clientMsgID === messageItem.clientMsgID);
      if (messageIndex >= 0) {
        state.localConversationMessageList[index].messageList.splice(
          messageIndex,
          1
        );
      }
    }
  },
  clear_localConversationMessage(state, { groupID, userID, currentUserID }) {
    const index = state.localConversationMessageList.findIndex(
      (i) =>
        (groupID &&
          i.groupID === groupID &&
          i.currentUserID === currentUserID) ||
        (userID && i.userID === userID && i.currentUserID === currentUserID)
    );
    if (index >= 0) {
      state.localConversationMessageList[index].messageList = [];
    }
  },
  clear_allLocalConversationMessage(state, { currentUserID }) {
    state.localConversationMessageList.map((i) => {
      if (i.currentUserID === currentUserID) {
        i.messageList = [];
      }
    });
  },
  set_localMessageAsRead(state, { list, currentUserID }) {
    // console.log(list);
    list.map((item) => {
      const { groupID, userID, msgIDList, sessionType } = item;
      let index = -1;
      if (sessionType === 1) {
        index = state.localConversationMessageList.findIndex(
          (i) =>
            userID && i.userID === userID && i.currentUserID === currentUserID
        );
      } else if (sessionType === 2 || sessionType === 3) {
        index = state.localConversationMessageList.findIndex(
          (i) =>
            groupID &&
            i.groupID === groupID &&
            i.currentUserID === currentUserID
        );
      }
      if (index >= 0) {
        msgIDList.map((clientMsgID) => {
          const messageIndex = state.localConversationMessageList[
            index
          ].messageList.findIndex((i) => i.clientMsgID === clientMsgID);
          if (messageIndex >= 0) {
            state.localConversationMessageList[index].messageList[
              messageIndex
            ].isRead = true;
          }
        });
      }
    });
  },
  set_draftText(state, { groupID, userID, currentUserID, draftText }) {
    const index = state.localConversationMessageList.findIndex(
      (i) =>
        (groupID &&
          i.groupID === groupID &&
          i.currentUserID === currentUserID) ||
        (userID && i.userID === userID && i.currentUserID === currentUserID)
    );
    if (index >= 0) {
      state.localConversationMessageList[index].draftText = draftText;
    }
  },
};
const actions = {
  do_singleMessageStatusList({ commit, state }, m) {
    commit("set_singleMessageStatusList", m);
    const message = m.message;
    const groupID = message.groupID || state.conversationData.groupID;
    const userID = message.recvID || state.conversationData.userID;
    commit("update_localConversationMessage", {
      groupID,
      userID,
      messageItem: m.message,
      currentUserID: m.currentUserID,
    });
  },
  push_localConversationMessage({ commit }, d) {
    return new Promise((resolve) => {
      commit("push_localConversationMessage", d);
      resolve();
    });
  },
  unshift_localConversationMessage({ commit }, d) {
    return new Promise((resolve) => {
      commit("unshift_localConversationMessage", d);
      resolve();
    });
  },
  set_newMessageTimes({ commit }) {
    return new Promise((resolve) => {
      commit("set_newMessageTimes");
      resolve();
    });
  },
  set_newMessageList({ commit }, d) {
    return new Promise((resolve) => {
      commit("set_newMessageList", d);
      resolve();
    });
  },
  set_revokeMessageTimes({ commit }) {
    return new Promise((resolve) => {
      commit("set_revokeMessageTimes");
      resolve();
    });
  },
  set_revokeMessageList({ commit }, d) {
    return new Promise((resolve) => {
      commit("set_revokeMessageList", d);
      resolve();
    });
  },
  set_draftText({ commit }, d) {
    return new Promise((resolve) => {
      commit("set_draftText", d);
      resolve();
    });
  },
};
export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
