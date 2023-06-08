/**
 * @Author qingchi
 * @Date 2021-10-25 22:34
 * @Version 1.0
 */
export interface QQMapResult {
  /*  adcode: "110106"
    city: "北京市"
    city_code: "156110000"
    district: "丰台区"
    location: {lat: 39.76289, lng: 116.125}
    name: "中国,北京市,北京市,丰台区"
    nation: "中国"
    nation_code: "156"
    province: "北京市"*/
  adcode: string
  city: string
  city_code: string
  district: string
  location: { lat: number, lng: number }
  name: string
  nation: string
  nation_code: string
  province: string
}
