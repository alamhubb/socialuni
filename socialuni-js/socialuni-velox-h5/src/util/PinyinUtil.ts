import pinyin from 'pinyin';

export default class PinyinUtil {
    static convertToFirstUpperPinyin(text) {
        return pinyin(text, {
            style: pinyin.STYLE_NORMAL // 获取普通拼音
        }).map((word, index) => {
            if (index) {
                return word[0].charAt(0).toUpperCase() + word[0].slice(1);
            }
            return word
        }).join("");
    }
}
