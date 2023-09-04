import socialuniAdminRequest from '../socialuniAdminRequest'
import { Message } from 'element-ui'

export default class UserGroupManageAPI {
  static queryUserGroups() {
    return socialuniAdminRequest.get('GroupController/list')
  }

  static setStaffGroupRoleAPI(groupId: number, roleId: number) {
    return socialuniAdminRequest.post('GroupController/setWeightRole?groupId=' + groupId +
        '&roleId=' + roleId
    ).then(() => {
      Message.success('设置成功')
    })
  }
}

