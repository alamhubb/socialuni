export default class LocationQueryQO {
  //纬度
  latitude: string
  //经度
  longitude: string

  constructor (latitude: number, longitude: number) {
    this.latitude = String(latitude)
    this.longitude = String(longitude)
  }
}
