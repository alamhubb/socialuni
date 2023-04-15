import UserPayResultVO from "socialuni-base-api/src/model/user/UserPayResultVO";
import SocialuniPayCoinQO from "socialuni-base-api/src/model/user/SocialuniPayCoinQO";

export default class SocialuniCoinAPI {
  static payCoinAPI(provider: string, amount: number) {
    const userPayVO = new SocialuniPayCoinQO(provider, amount)
    return request.post<UserPayResultVO>('socialuni/coin/payCoin', userPayVO)
  }
}
