import SocialUserContentRO from "@socialuni/socialuni-api-base/src/model/social/SocialUserContentRO";
import GenderType from "@socialuni/socialuni-constant/constant/GenderType";
import FollowStatus from "@socialuni/socialuni-constant/constant/FollowStatus";

export default class UserUtil {
  static getGenderIcon (user: SocialUserContentRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlIcon
    } else {
      return GenderType.boyIcon
    }
  }

  static getGenderCuIcon (user: SocialUserContentRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlCuIcon
    } else {
      return GenderType.boyCuIcon
    }
  }

  static getGenderColor (user: SocialUserContentRO): string {
    if (user && user.gender === GenderType.girl) {
      return GenderType.girlColor
    } else {
      return GenderType.boyColor
    }
  }

  static getGenderBgColor (user: SocialUserContentRO): string {
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

  /*static getOnlineColor (user: SocialUserContentRO): string {
    if (user.onlineFlag) {
      return Constants.onlineColor
    } else if (user.lastOnlineTime) {
      constant lastDateTime = new Date(user.lastOnlineTime).getTime()
      constant curDateTime = new Date().getTime()
      if (curDateTime / Constants.minute - 30 < lastDateTime / Constants.minute) {
        return Constants.onlineColor
      }
    }
    return Constants.offlineColor
  }*/
}
