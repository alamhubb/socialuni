import GenderType from '@/const/GenderType'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import FollowStatus from '@/const/FollowStatus'

export default class UserUtil {
  static getGenderIcon (user: CenterUserDetailRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlIcon
    } else {
      return GenderType.boyIcon
    }
  }

  static getGenderCuIcon (user: CenterUserDetailRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlCuIcon
    } else {
      return GenderType.boyCuIcon
    }
  }

  static getGenderColor (user: CenterUserDetailRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlColor
    } else {
      return GenderType.boyColor
    }
  }

  static getGenderBgColor (user: CenterUserDetailRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlBgColor
    } else {
      return GenderType.boyBgColor
    }
  }

  static getFollowStatusIcon (followStatus: string): string {
    if (followStatus === FollowStatus.follow) {
      return 'plus'
    } else if (followStatus === FollowStatus.eachFollow) {
      // return 'swap_horiz'
      return ''
    } else {
      return 'checkmark'
    }
  }

  static getFollowStatusColor (followStatus: string): string {
    if (followStatus === FollowStatus.follow) {
      return 'blue'
    } else {
      return 'gray'
    }
  }

  /*static getOnlineColor (user: CenterUserDetailRO): string {
    if (user.onlineFlag) {
      return Constants.onlineColor
    } else if (user.lastOnlineTime) {
      const lastDateTime = new Date(user.lastOnlineTime).getTime()
      const curDateTime = new Date().getTime()
      if (curDateTime / Constants.minute - 30 < lastDateTime / Constants.minute) {
        return Constants.onlineColor
      }
    }
    return Constants.offlineColor
  }*/
}
