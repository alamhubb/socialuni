import type {Rule} from "unocss";
import ColorStyles from "./ColorStyles";
import ObjectUtil from "../../utils/ObjectUtil";
import SizeStyles from "./SizeStyles";
import {match} from "assert";

export const directionMap: { [key in string]: string[] } = {
    'l': ['left'],
    'r': ['right'],
    't': ['top'],
    'b': ['bottom'],
    'x': ['left', 'right'],
    'y': ['top', 'bottom'],
    'd': [''],
    'g': [''],
}

export default class UnocssRuleUtil {
    static defaultUnit = 'px'

    static getNumStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[3]
        const valueUnit = match[4]
        return this.getStyles(propertyPrefix, direction, size, valueUnit)
    }

    static getDirectionStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[2]
        const valueUnit = match[3]
        return this.getStyles('', direction, size, valueUnit)
    }

    static getBorderRadiusStyles(match: string[]): { [key in string]: string } {
        const direction = match[1]
        const size = match[3]
        const valueUnit = match[4]

        const defaultRadiusValue = 5

        if (direction === 'd') {
            return {
                'border-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        } else if (direction === 't') {
            return {
                'border-top-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-top-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        } else if (direction === 'b') {
            return {
                'border-bottom-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        } else if (direction === 'l') {
            return {
                'border-top-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
            }
        } else if (direction === 'r') {
            return {
                'border-top-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        } else if (direction === 'x') {
            return {
                'border-top-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-top-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        } else if (direction === 'y') {
            return {
                'border-top-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-top-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-left-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`,
                'border-bottom-right-radius': `${size || defaultRadiusValue}${valueUnit || this.defaultUnit}`
            }
        }
        return {}
    }

    static getStyles(propertyPrefix: string, direction: string, size: any, valueUnit: string = '') {
        if (size === undefined || size === '' || size === null) {
            size = 20
        } else {
            if (SizeStyles.sizesMap[size] !== undefined) {
                size = SizeStyles.sizesMap[size]
            }
        }
        if (size > 0) {
            valueUnit = valueUnit || this.defaultUnit
        }

        const styleValue = size + (valueUnit)

        const directionAry: string[] = directionMap[direction]
        if (!directionAry) {
            return
        }
        const style: { [key in string]: string } = {}
        for (const string of directionAry) {
            if (propertyPrefix) {
                if (string) {
                    style[(propertyPrefix + '-' + string)] = styleValue
                } else {
                    style[propertyPrefix] = styleValue
                }
            } else {
                style[string] = styleValue
            }
        }
        console.log(style)
        return style
    }
}
