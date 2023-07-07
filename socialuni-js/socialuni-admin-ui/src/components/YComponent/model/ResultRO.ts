import PageableRO from '@/components/YComponent/model/PageableRO'

export default class ResultRO<T> {
  code: string = null
  msg: string = null
  data: PageableRO<T> = null

  constructor(data?: any) {
    if (data) {
      this.data = new PageableRO(data)
    }
  }
}
