export default class ReportAudit {
  public id: number
  public violation: boolean
  public violateType: string
  public auditNote: string

  constructor(id: number, violation: boolean, violateType: string, auditNote: string) {
    this.id = id
    this.violation = violation
    this.violateType = violateType
    this.auditNote = auditNote
  }
}
