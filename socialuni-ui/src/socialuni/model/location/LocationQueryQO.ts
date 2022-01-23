export default class LocationQueryQO {
  //纬度
  latitude: number
  //经度
  longitude: number

  constructor (latitude: number, longitude: number) {
    this.latitude = latitude
    this.longitude = longitude
  }
}
