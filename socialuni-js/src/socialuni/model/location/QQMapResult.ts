/**
 * @Author qingchi
 * @Date 2021-10-25 22:34
 * @Version 1.0
 */
export interface QQMapResult {
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
