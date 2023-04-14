export default class SocialuniPayCoinQO {
  provider: string
  amount: number

  constructor (provider: string, amount?: number) {
    this.provider = provider
    this.amount = amount
  }
}
