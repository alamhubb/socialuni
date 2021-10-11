export default class PageableRO<T> {
  list: T[] = []
  endRow: number = null
  hasNextPage: boolean = null
  hasPreviousPage: boolean = null
  isFirstPage: boolean = null
  isLastPage: boolean = null
  navigateFirstPage: number = null
  navigateLastPage: number = null
  navigatePages: number = null
  navigatepageNums: number[] = []
  nextPage: number = null
  pageNum: number = null
  pageSize: number = null
  pages: number = null
  prePage: number = null
  size: number = null
  startRow: number = null
  total: number = null
}
