import Display from "../singleStyle/Display";
import FlexDirection from "../singleStyle/FlexDirection";

export default class FlexStyle {
  static get flexRow() {
    return {
      ...Display.flex,
      ...FlexDirection.row,
    }
  }
}
