import xmljs, {Element} from 'xml-js'
import ElementXmlVO from "../model/base/ElementXmlVO";
import NumberUtil from "./NumberUtil";

export default class XmlUtil {
    static parseXml(xml: string): ElementXmlVO<any> {
        return xmljs.xml2js(xml, {
            compact: false,
            attributeValueFn: function (value) {
                if (value === 'false') {
                    return false
                } else if (value === 'true') {
                    return true
                } else if (value.includes('&quot;')) {
                    return value.replaceAll('&quot;', '"')
                }
                // 转义&
                return NumberUtil.isNumber(value) ? Number(value) : value
                // return value
            }
        }) as ElementXmlVO<any>
    }

    static toXml(obj: Element): string {
        return xmljs.js2xml(obj, {
            compact: false,
            textFn: function (value) {
                if (value === 'null' || value === null) {
                    return ''
                }
                return value
            },
            attributeValueFn: function (value) {
                /* if (value === 'null' || value === null) {
                  return ''
                }*/
                // 转义&
                return value
                    // 需要转义,保存的时候不转义会报错
                    .replace(/&/g, '&amp;')
                    // 这种需要转义回去,要不然后端你无法解析这种会报错
                    .replace(/&amp;quot;/g, '&quot;')
                    .replace(/</g, '&lt;')
                    .replace(/>/g, '&gt;')
            }
        }) as string
    }
}
