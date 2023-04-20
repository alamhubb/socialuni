import UserPayResultVO from "socialuni/src/model/user/UserPayResultVO";
import SocialuniPayCoinQO from "socialuni/src/model/user/SocialuniPayCoinQO";

export default class SocialuniCoinAPI {
  static payCoinAPI(provider: string, amount: number) {
    const userPayVO = new SocialuniPayCoinQO(provider, amount)
    return request.post<UserPayResultVO>('socialuni/coin/payCoin', userPayVO)
  }
}
