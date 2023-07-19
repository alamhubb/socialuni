import type {Rule} from "unocss";
import UnocssRuleUtil, {directionMap} from "./UnocssRuleUtil";

export default class UnocssRules {
    static rules: Rule[] = [
        // [/^p(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('padding', match)],
        [/^p(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getNumStyles('padding', match)],
        // [/^m(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('margin', match)],
        [/^m(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getNumStyles('margin', match)],
        [/^font-?(\d+)(.+)?$/, match => {
            return ({'font-size': `${match[1] || 20}${match[2] || 'px'}`})
        }],
    ]
}
