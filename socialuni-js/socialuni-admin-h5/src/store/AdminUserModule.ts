import type DevAccountRO from "socialuni-admin-api/src/model/base/DevAccountRO";
import {reactive} from "vue";
import AdminUserAPI from "socialuni-admin-api/src/api/AdminUserAPI";

class AdminModule {
    adminUser: DevAccountRO = null

    setAdminUser(adminUser: DevAccountRO) {
        this.adminUser = adminUser
    }

    async getAdminUserAction() {
        const res = await AdminUserAPI.getUserAPI()
        this.setAdminUser(res.data)
    }
}

const adminModule = reactive(new AdminModule())

export default adminModule
