export default class Pageable {
  currentPage = 1
  pageSize = 10
  total = 0

  constructor(currentPage: number, pageSize: number) {
    this.currentPage = currentPage
    this.pageSize = pageSize
  }
}
