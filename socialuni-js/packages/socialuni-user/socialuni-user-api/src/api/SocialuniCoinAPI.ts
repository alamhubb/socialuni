import UserPayResultVO from "socialuni-api-base/src/model/user/UserPayResultVO";
import SocialuniPayCoinQO from "socialuni-api-base/src/model/user/SocialuniPayCoinQO";
import socialuniUserRequest from "../request/socialuniUserRequest";

export default class SocialuniCoinAPI {
    static payCoinAPI(provider: string, amount: number) {
        const userPayVO = new SocialuniPayCoinQO(provider, amount)
        return socialuniUserRequest.post<UserPayResultVO>('socialuni/coin/payCoin', userPayVO)
    }

    static getUserCoinInfoAPI() {
        return socialuniUserRequest.get<{ coinNum: number }>('socialuni/coin/getUserCoinInfo')
    }
}
