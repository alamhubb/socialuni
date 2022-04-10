export default class Pageable {
  pageNum = 1
  pageNo = 1
  page = 1
  pageSize = 10
  limit = 10
  total = 0

  constructor(currentPage = 1, pageSize = 10) {
    this.pageNum = currentPage
    this.pageNo = currentPage
    this.page = currentPage
    this.pageSize = pageSize
    this.limit = pageSize
  }
}
