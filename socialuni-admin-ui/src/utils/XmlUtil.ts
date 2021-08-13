import xmljs, { Element } from 'xml-js'

export default class XmlUtil {
  static parseXml(xml: string): Element {
    return xmljs.xml2js(xml, { compact: false }) as Element
  }
}
