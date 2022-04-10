import XmlUtil from '@/socialuni/utils/XmlUtil'
import ElementXmlVO from '@/socialuni/model/base/ElementXmlVO'

export default class TencentCosIdInfoRO {
  public Address: string = null
  public Authority: string = null
  public Birth: string = null
  public IdNum: string = null
  public Name: string = null
  public Nation: string = null
  public Sex: string = null
  public ValidDate: string = null

  constructor (xmlResult: string) {
    const rootXml: ElementXmlVO<any> = XmlUtil.parseXml(xmlResult)
    const resXml = rootXml.elements[0]
    const idInfoXml = resXml.elements[0]
    const idInfoRO: TencentCosIdInfoRO = {} as TencentCosIdInfoRO
    for (const idInfoRoElement of idInfoXml.elements) {
      if (idInfoRoElement.elements) {
        idInfoRO[idInfoRoElement.name] = idInfoRoElement.elements[0].text
      }
    }
    this.Address = idInfoRO.Address || null
    this.Authority = idInfoRO.Authority || null
    this.Birth = idInfoRO.Birth || null
    this.IdNum = idInfoRO.IdNum || null
    this.Name = idInfoRO.Name || null
    this.Nation = idInfoRO.Nation || null
    this.Sex = idInfoRO.Sex || null
    this.ValidDate = idInfoRO.ValidDate || null
  }
}
