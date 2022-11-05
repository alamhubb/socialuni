const state = {
  groupInfo: {
    groupName: "", //群名称
    groupType: 0, //群类型
    notification: "", //群公告
    introduction: "", //群介绍
    faceURL: "", //群头像
    ex: "", //扩展字段
  },
  updateGroupInfoTimes: 0,
};
const mutations = {
  set_groupInfo(state, groupInfo) {
    state.groupInfo = groupInfo;
  },
  set_updateGroupInfoTimes(state) {
    state.updateGroupInfoTimes++;
  },
};
const actions = {};
export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
