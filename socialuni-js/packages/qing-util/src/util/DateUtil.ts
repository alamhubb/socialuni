/**
 * Parse the time to string
 * @param {(Date)} dateProp
 * @param {string} cFormat
 * @returns {string}
 */
import Constants from "socialuni-constant/constant/Constant";
import moment from "moment";



// 示例
export default class DateUtil {
    static convertToTime(ms) {
        return moment.utc(ms).format("mm:ss");
    }

    static parseTimeSecond(dateProp: Date, cFormat?: string) {
        return this.parseTime(dateProp,'{y}-{m}-{d} {h}:{i}:{s}')
    }

    static parseTime(dateProp: Date, cFormat?: string) {
        const format = cFormat || '{y}-{m}-{d} {h}:{i}'
        const date = new Date(dateProp)
        const formatObj = {
            y: date.getFullYear(),
            m: date.getMonth() + 1,
            d: date.getDate(),
            h: date.getHours(),
            i: date.getMinutes(),
            s: date.getSeconds(),
            a: date.getDay()
        }
        const timeStr = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
            let value = formatObj[key]
            // Note: getDay() returns 0 on Sunday
            if (key === 'a') {
                return ['日', '一', '二', '三', '四', '五', '六'][value]
            }
            if (result.length > 0 && value < 10) {
                value = '0' + value
            }
            return value || 0
        })
        return timeStr
    }

    static formatMinutes(time: number) {
        return this.parseTime(new Date(time), '{h}:{i}')
    }

    static formatMS(time: number) {
        return this.parseTime(new Date(time), '{i}:{s}')
    }

    static formatHMS(time: number) {
        let str = ''
        if (time > Constants.hour) {
            str += this.parseTime(new Date(time), '{h}小时')
        }
        if (time > Constants.minute) {
            str += this.parseTime(new Date(time), '{i}分钟')
        }
        str += this.parseTime(new Date(time), '{s}秒')
        return str
    }


    /**
     * @param {string} dateStr
     * @returns {string}
     * talk的时间
     */
    static formatTime(dateStr: string | number | Date) {
        let date: Date
        // 如果字符串类型，ios平台不支持直接转date，需要工具类转换
        if (typeof dateStr === 'string') {
            date = this.backendDateFormat(dateStr)
        } else if (typeof dateStr === 'number') {
            date = new Date(dateStr)
        } else {
            date = dateStr
        }
        const time = new Date(date).getTime()
        const d = new Date(time)
        const now = Date.now()
        const diff = (now - d.getTime()) / 1000
        if (diff < 1) {
            return '刚刚'
        } else if (diff < 3600) {
            // less 1 hour
            return Math.ceil(diff / 60) + '分钟前'
        } else if (diff < 3600 * 24) {
            return Math.ceil(diff / 3600) + '小时前'
        } else if (diff < 3600 * 24 * 2) {
            return '昨天'
        }
        return (d.getMonth() + 1 + '-' + d.getDate())
    }

    static backendDateFormat(dateStr: string): Date {
        dateStr = dateStr || ''
        dateStr = dateStr.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').replace(/-/g, '/').substr(0, 19)
        let date = new Date(dateStr)
        // +8小时
        date = new Date(date.getTime() + 8 * 60 * 60 * 1000)
        return date
    }


    static parseDate(date: Date) {
        const format = '{y}-{m}-{d}'
        return this.parseTime(date, format)
    }

    static parseStrToTime(dateStr: string) {
        let date: Date = null
        if (dateStr) {
            date = this.backendDateFormat(dateStr)
        }
        return this.parseDate(date)
    }

    /**
     * Show plural label if time is plural number
     * @param {number} time
     * @param {string} label
     * @return {string}
     */
    static pluralize(time, label) {
        if (time === 1) {
            return time + label
        }
        return time + label + 's'
    }

    /**
     * @param {number} time
     */
    static timeAgo(time) {
        const between = Date.now() / 1000 - Number(time)
        if (between < 3600) {
            return this.pluralize(~~(between / 60), ' minute')
        } else if (between < 86400) {
            return this.pluralize(~~(between / 3600), ' hour')
        } else {
            return this.pluralize(~~(between / 86400), ' day')
        }
    }
}
