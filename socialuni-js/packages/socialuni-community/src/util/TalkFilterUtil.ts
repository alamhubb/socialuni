import GenderType from "socialuni-constant/constant/GenderType";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class TalkFilterUtil {
    static readonly minAgeFilterKey: string = 'minAgeFilter'
    static readonly maxAgeFilterKey: string = 'maxAgeFilter'
    static readonly genderFilterKey: string = 'genderFilter'
    static readonly notFirstSetAgeKey: string = 'notFirstSetAge'
    // 附近
    static readonly minAgeFilterDefault: number = 8
    static readonly maxAgeFilterDefault: number = 40

    // static readonly genderFilterDefault: string = GenderType.all

    static getNotFirstSetAge(): boolean {
        return !!SocialuniAppUtil.StorageUtil.getObj(TalkFilterUtil.notFirstSetAgeKey)
    }

    static getMinAgeFilter(): number {
        return Number(SocialuniAppUtil.StorageUtil.getObj(TalkFilterUtil.minAgeFilterKey)) || TalkFilterUtil.minAgeFilterDefault
    }

    static getMaxAgeFilter(): number {
        return Number(SocialuniAppUtil.StorageUtil.getObj(TalkFilterUtil.maxAgeFilterKey)) || TalkFilterUtil.maxAgeFilterDefault
    }

    static getGenderFilter(): string {
        //如果值不在列表中，返回默认值
        const storeGender = SocialuniAppUtil.StorageUtil.getObj(TalkFilterUtil.genderFilterKey)
        const mapGender = GenderType.mapOld.get(storeGender)
        //兼容处理，旧版本key转新版本
        if (storeGender && mapGender) {
            return mapGender
        }
        return GenderType.talkQueryFilterMap.get(GenderType.all)
    }

    static setFilterData(genderFilter: string, minAge: number, maxAge: number) {
        SocialuniAppUtil.StorageUtil.setObj(TalkFilterUtil.genderFilterKey, genderFilter)
        SocialuniAppUtil.StorageUtil.setObj(TalkFilterUtil.minAgeFilterKey, minAge)
        SocialuniAppUtil.StorageUtil.setObj(TalkFilterUtil.maxAgeFilterKey, maxAge)
    }

    static setFistSetUserAge(minAge: number, maxAge: number) {
        StorageUtil.setObj(TalkFilterUtil.notFirstSetAgeKey, true)
        StorageUtil.setObj(TalkFilterUtil.minAgeFilterKey, minAge)
        StorageUtil.setObj(TalkFilterUtil.maxAgeFilterKey, maxAge)
    }
}
