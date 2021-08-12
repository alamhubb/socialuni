import http from '@/plugins/http'
import { Message } from 'element-ui'

export default class UserGroupManageAPI {
  static queryUserGroups() {
    return http.get('GroupController/list')
  }

  static setStaffGroupRoleAPI(groupId: number, roleId: number) {
    return http.post('GroupController/setWeightRole?groupId=' + groupId +
        '&roleId=' + roleId
    ).then(() => {
      Message.success('设置成功')
    })
  }
}

