export default class KeyValueObj<T> {
    key: string = null
    value: T = null


    constructor(key: string, value: T) {
        this.key = key;
        this.value = value;
    }
}
