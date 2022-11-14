export default class JsonUtil {
  static toJson (object: any): string {
    // return JSON.stringify(object)
    return JSON.stringify(object)
  }

  static parse (objJson: string): any {
    return JSON.parse(objJson)
  }

  static log (object: any) {
    console.log(JSON.stringify(object))
  }
}
