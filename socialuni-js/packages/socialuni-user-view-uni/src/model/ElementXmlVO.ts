export default class ElementXmlVO<T extends { [key: string]: any }> {
  type = 'element'
  text: any = null
  name: string = null
  elements: ElementXmlVO<any>[] = []
  attributes: T = null

  constructor(name?: string) {
    this.name = name
  }
}
