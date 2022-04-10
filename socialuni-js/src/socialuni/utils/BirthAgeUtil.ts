export default class BirthAgeUtil {
  //根据出生日期，获取年龄
  static getAgeByBirth (birthDayStr: string): number {
    const nowDay = new Date()
    //采用虚岁+1岁
    const yearNow = nowDay.getFullYear() + 1
    const monthNow = nowDay.getMonth() + 1
    const dayOfMonthNow = nowDay.getDate()

    const birthDay = new Date(birthDayStr)
    const yearBirth = birthDay.getFullYear()
    const monthBirth = birthDay.getMonth() + 1
    const dayOfMonthBirth = birthDay.getDate()
    //计算整岁数
    let age = yearNow - yearBirth
    if (monthNow <= monthBirth) {
      if (monthNow === monthBirth) {
        //当前日期在生日之前，年龄减一
        if (dayOfMonthNow < dayOfMonthBirth) age--
      } else {
        age--//当前月份在生日之前，年龄减一
      }
    }
    return age
  }
}
