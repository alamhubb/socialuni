export default class JsonUtil {
    static toJson(object: any): string {
        if (object || (object === 0) || (object === false)) {
            return JSON.stringify(object)
        }
        return ''
    }

    static toParse(objJson: string): any {
        if (objJson) {
            return JSON.parse(objJson)
        }
        return null
    }

    static log(object: any) {
        console.log(JSON.stringify(object))
    }
}
