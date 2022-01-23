import Display from "@/cssModule/singleStyle/Display";
import FlexDirection from "@/cssModule/singleStyle/FlexDirection";

export default class FlexStyle {
  static get flexRow() {
    return {
      ...Display.display_flex,
      ...FlexDirection.flexDirection_row,
    }
  }
}
