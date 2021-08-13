/*
package controller;

import com.socialuni.api.model.RO.ResultRO;
import com.socialuni.center.web.controller.UserDetailController;
import com.socialuni.center.web.controller.login.CenterLoginController;
import com.socialuni.center.web.controller.login.PhoneController;
import com.socialuni.api.model.RO.user.SocialLoginRO;
import com.socialuni.center.web.model.RO.user.CenterMineUserDetailRO;

import javax.annotation.Resource;

public class UserDetailControllerTest {

    @Resource
    UserDetailController userDetailController;
    @Resource
    CenterLoginController centerLoginController;
    @Resource
    PhoneController phoneController;

    public void test() {
        //校验返回结果都应该是 CenterMineUserDetailRO
        ResultRO<CenterMineUserDetailRO> roResultRO = userDetailController.getUserMine();
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> roResultRO1 = centerLoginController.phoneLogin(null);
        ResultRO<SocialLoginRO<CenterMineUserDetailRO>> roResultRO2 =  centerLoginController.providerLogin(null);
        ResultRO<CenterMineUserDetailRO> roResultRO3 =  phoneController.bindPhoneNum(null);
        ResultRO<CenterMineUserDetailRO> roResultRO4 =  phoneController.bindPhoneNumByWx(null);
    }
}
*/
