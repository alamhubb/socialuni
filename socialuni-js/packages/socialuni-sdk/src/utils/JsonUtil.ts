import ObjectUtil from "./ObjectUtil";

export default class JsonUtil {
    static toJson(object: any): string {
        // return JSON.stringify(object)
        if (object) {
            return JSON.stringify(object)
        }
        return null
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
