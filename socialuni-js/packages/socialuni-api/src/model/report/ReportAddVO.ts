export default class ReportAddVO {
  public reportType: string
  public reportContentType: string
  public content: string
  public contentId: string

  // public infoVersionNo: number

  constructor(reportContentType: string, reportType: string, content: string) {
    this.reportContentType = reportContentType
    this.reportType = reportType
    this.content = content
  }
}

