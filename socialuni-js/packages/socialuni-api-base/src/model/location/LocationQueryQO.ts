export default class LocationQueryQO {
    //纬度
    latitude: number
    //经度
    longitude: number

    constructor(latitude: number = null, longitude: number = null) {
        this.latitude = latitude
        this.longitude = longitude
    }
}
