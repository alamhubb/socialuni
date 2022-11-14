import { parseTime } from '@/utils/DateFormatUtil'

export default class DateUtils {
  static second = 1000
  static minutes: number = DateUtils.second * 60
  static hour: number = DateUtils.minutes * 60
  static day: number = DateUtils.hour * 24
  static week: number = DateUtils.day * 7

  static getWeekday(dateStr: string | Date | number): number {
    let curDate = new Date()
    if (dateStr) {
      curDate = new Date(dateStr)
    }
    const day = curDate.getDay()
    return day === 0 ? 7 : day
  }

  // 得到序号，日子-1
  static getMonthDayNo(dateStr: string | Date | number): number {
    return this.getMonthDay(dateStr) - 1
  }

  static getMonthDay(dateStr: string | Date | number): number {
    let curDate = new Date()
    if (dateStr) {
      curDate = new Date(dateStr)
    }
    return Number(parseTime(curDate, '{d}'))
  }

  // 获取一个月有多少天
  static getMonAllDayNum(dateStr: string | Date | number): number {
    let curDate = new Date()
    if (dateStr) {
      curDate = new Date(dateStr)
    }
    const date = new Date(curDate.getFullYear(), curDate.getMonth() + 1, 0)
    return this.getMonthDay(date)
  }

  // 按周聚合，分组，把周1-周日的日子合并为 2020/11/23-2020/11/29
  static getWeekStr(dateStr: string): string {
    const curDate = new Date(dateStr)
    const curWeekday = DateUtils.getWeekday(dateStr)
    let Monday = curDate
    // 如果不为周1
    if (curWeekday !== 1) {
      // 如果不为周1，则获取周1的日期
      Monday = new Date(curDate.getTime() - (curWeekday - 1) * DateUtils.day)
    }
    const Sunday = new Date(Monday.getTime() + 6 * DateUtils.day)
    const MondayStr = Monday.toLocaleDateString()
    const SundayStr = Sunday.toLocaleDateString()
    return MondayStr + '-' + SundayStr
  }

  static plus(date: Date, time: number): Date {
    return new Date(date.getTime() + time)
  }

  static pickerOptions = {
    shortcuts: [{
      text: '最近一周',
      onClick(picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近一个月',
      onClick(picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
        picker.$emit('pick', [start, end])
      }
    }, {
      text: '最近三个月',
      onClick(picker) {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
        picker.$emit('pick', [start, end])
      }
    }]
  }
}
