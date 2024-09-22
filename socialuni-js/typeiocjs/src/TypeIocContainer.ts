import KeyValueObj from "./KeyValueObj";
export class TypeIocContainer {
    private readonly container: Map<string, Object> = new Map();
    private readonly subscribe: Map<string, Set<KeyValueObj<Object>>> = new Map();
    private subscribeSet(key: string, value: Set<KeyValueObj<Object>>) {
        this.subscribe.set(key, value);
    }
    private subscribeGet(key: string): Set<KeyValueObj<Object>> {
        return this.subscribe.get(key);
    }
    publish(serviceName: string, obj: object) {
        this.containerSet(serviceName, obj);
        const keySet: Set<KeyValueObj<Object>> = this.subscribeGet(serviceName);
        if (keySet) {
            for (const keyItem of keySet) {
                const subObj = keyItem.value;
                subObj[keyItem.key] = obj;
            }
        }
    }
    subAndGet(serviceName: string, fieldName: string, obj: object) {
        const keySet = this.subscribeGet(serviceName);
        const keyValueObj = new KeyValueObj(fieldName, obj);
        if (keySet) {
            keySet.add(keyValueObj);
        }
        else {
            const set = new Set<KeyValueObj<Object>>;
            set.add(keyValueObj);
            this.subscribeSet(serviceName, set);
        }
        return this.containerGet(serviceName);
    }
    private containerSet(key: string, value: Object) {
        this.container.set(key, value);
    }
    containerGet(key: string) {
        return this.container.get(key);
        // let result = Reflect.getMetadata(TypeIocContainer.propertyResourceKey, prop);
        // const propObj = this.container.get(result)
        // if (propObj === undefined) {
        //     return null
        // }
        // return propObj
    }
}
const typeIocContainer = new TypeIocContainer();
export default typeIocContainer;
