import request from '@/plugins/request'
import { Message } from 'element-ui'

export default class UserGroupManageAPI {
  static queryUserGroups() {
    return request.get('GroupController/list')
  }

  static setStaffGroupRoleAPI(groupId: number, roleId: number) {
    return request.post('GroupController/setWeightRole?groupId=' + groupId +
        '&roleId=' + roleId
    ).then(() => {
      Message.success('设置成功')
    })
  }
}

