export default class TalkUtil {
  static getHugIcon (hasHugged: boolean): string {
    if (hasHugged) {
      return 'heart-fill'
    } else {
      return 'heart'
    }
  }

  static getHugColor (hasHugged: boolean): string[] {
    if (hasHugged) {
      return ['color-red3']
    } else {
      return []
    }
  }
}
