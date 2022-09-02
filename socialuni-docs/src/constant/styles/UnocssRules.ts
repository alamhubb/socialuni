import type {Rule} from "unocss";
import ColorStyles from "./ColorStyles";
import ObjectUtil from "../../utils/ObjectUtil";
import UnocssRuleUtil, {directionMap} from "./UnocssRuleUtil";

export default class UnocssRules {
    static rules: Rule[] = [
        [/^p(.)(-(\d+)?(.+)+)?$/, match => UnocssRuleUtil.getStyles('padding', match)],
        [/^font-?(\d+)(.+)?$/, match => {
            return ({'font-size': `${match[1] || 20}${match[2] || 'px'}`})
        }],
    ]
}
