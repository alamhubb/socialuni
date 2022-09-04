import type {Rule} from "unocss";
import ColorStyles from "./ColorStyles";
import ObjectUtil from "../../utils/ObjectUtil";
import SizeStyles from "./SizeStyles";
import {match} from "assert";
import tinycolor2 from "tinycolor2";

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

    static getSizeStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[3]
        return this.getStyles(propertyPrefix, direction, size)
    }

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

    static getBorderWidthStyles(match: string[], theme): { [key in string]: string } {
        const direction = match[1]
        const width = match[2]

        // border-color: mix($color-white, $color-main, 60%);
        //bd bt
        //bd-them bt-theme
        //bd-them-2 bt-theme
        //bd-color-theme
        //bd-2
        if (direction === 'd') {
            return {
                'border-width': `${width}px`
            }
        } else if (direction === 't') {
            return {
                'border-top-width': `${width}px`,
            }
        } else if (direction === 'b') {
            return {
                'border-bottom-width': `${width}px`,
            }
        } else if (direction === 'l') {
            return {
                'border-left-width': `${width}px`,
            }
        } else if (direction === 'r') {
            return {
                'border-right-width': `${width}px`,
            }
        } else if (direction === 'x') {
            return {
                'border-left-width': `${width}px`,
                'border-right-width': `${width}px`,
            }
        } else if (direction === 'y') {
            return {
                'border-top-width': `${width}px`,
                'border-bottom-width': `${width}px`
            }
        }
        return {}
    }

    static getBorderDarkStyles(match: string[], theme): { [key in string]: string } {
        const direction = match[1]
        const colorTheme = match[3]
        let color = ColorStyles.themeColors.border
            if (colorTheme) {
                color = tinycolor2.mix(ColorStyles.colors[colorTheme], ColorStyles.themeColors.black, 10)
        }
        // border-color: mix($color-white, $color-main, 60%);
        //bd bt
        //bd-them bt-theme
        //bd-them-2 bt-theme
        //bd-color-theme
        //bd-2
        return this.getBorderColorStyles(direction, color);
    }
    static getBorderLightStyles(match: string[], theme): { [key in string]: string } {
        const direction = match[1]
        const colorTheme = match[3]
        let color = ColorStyles.themeColors.border
        if (colorTheme) {
            color = tinycolor2.mix(ColorStyles.colors[colorTheme], ColorStyles.themeColors.white, 90)
        }
        // border-color: mix($color-white, $color-main, 60%);
        //bd bt
        //bd-them bt-theme
        //bd-them-2 bt-theme
        //bd-color-theme
        //bd-2
        return this.getBorderColorStyles(direction, color);
    }
    static getBorderStyles(match: string[], theme): { [key in string]: string } {
        const direction = match[1]
        const colorTheme = match[3]
        let color = ColorStyles.themeColors.border
        if (colorTheme) {
            color = ColorStyles.colors[colorTheme]
        }
        // border-color: mix($color-white, $color-main, 60%);
        //bd bt
        //bd-them bt-theme
        //bd-them-2 bt-theme
        //bd-color-theme
        //bd-2
        return this.getBorderColorStyles(direction, color);
    }

    private static getBorderColorStyles(direction: string, color: string): { [key in string]: string } {
        if (direction === 'd') {
            return {
                'border': `1px solid ${color}`
            }
        } else if (direction === 't') {
            return {
                'border-top': `1px solid ${color}`,
            }
        } else if (direction === 'b') {
            return {
                'border-bottom': `1px solid ${color}`,
            }
        } else if (direction === 'l') {
            return {
                'border-left': `1px solid ${color}`,
            }
        } else if (direction === 'r') {
            return {
                'border-right': `1px solid ${color}`,
            }
        } else if (direction === 'x') {
            return {
                'border-left': `1px solid ${color}`,
                'border-right': `1px solid ${color}`,
            }
        } else if (direction === 'y') {
            return {
                'border-top': `1px solid ${color}`,
                'border-bottom': `1px solid ${color}`
            }
        }
        return {}
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
        return style
    }
}
