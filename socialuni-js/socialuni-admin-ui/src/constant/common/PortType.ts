export default class PortType {
  static readonly splitSymbol = '#'
  static readonly out = 'out'
  static readonly in = 'in'
  static readonly all = 'all'
  static readonly hideBottom = 'hideBottom'
  static readonly hideTop = 'hideTop'
  static readonly hideIn = 'hideIn'
  static readonly hideOut = 'hideOut'

  static readonly type = 'port-group'
  static readonly port = 'port'

  static readonly tableIn = 'table-in'
  static readonly tableOut = 'table-out'

  static getInputPortName(targetColumnName: string) {
    return targetColumnName + PortType.splitSymbol + PortType.in
  }

  static getOutPortName(targetColumnName: string) {
    return targetColumnName + PortType.splitSymbol + PortType.out
  }

  static getColumnName(port: string) {
    return port.split(PortType.splitSymbol)[0]
  }
}
