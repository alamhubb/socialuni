export function parseTime(time, cFormat) {
  if (arguments.length === 0 || !time) {
    return null;
  }
  const format = cFormat || "{y}-{m}-{d} {h}:{i}:{s}";
  let date;
  if (typeof time === "object") {
    date = time;
  } else {
    if (typeof time === "string") {
      if (/^[0-9]+$/.test(time)) {
        time = parseInt(time);
      } else {
        time = time.replace(new RegExp(/-/gm), "/");
      }
    }

    if (typeof time === "number" && time.toString().length === 10) {
      time = time * 1000;
    }
    date = new Date(time);
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay(),
  };
  const weekList = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
  const time_str = format.replace(/{([ymdhisaM])+}/g, (result, key) => {
    const value = formatObj[key];
    if (key === "a") {
      return weekList[value];
    }
    return value.toString().padStart(2, "0");
  });
  return time_str;
}
/**
 * canvas绘图相关，把文字转化成只能行数，多余显示省略号
 * ctx: 当前的canvas
 * text: 文本
 * contentWidth: 文本最大宽度
 * lineNumber: 显示几行
 */
export function transformContent(ctx, text, contentWidth, lineNumber = 1) {
  let textArray = text.split(""); // 分割成字符串数组
  let textStr = "";
  let row = [];
  const length = textArray.length;
  let index = 0;
  for (let i = 0; i < length; i++) {
    const t = textArray[i];
    index = i;
    const width = ctx.measureText(textStr + t).width + i * 0.2;
    if (width < contentWidth) {
      textStr += t;
      if (i === length - 1) {
        row.push(textStr);
        textStr = "";
      }
    } else {
      row.push(textStr);
      textStr = t;
      if (row.length >= lineNumber) {
        break;
      }
    }
  }
  if (index < length) {
    //文字未显示完
    const lastIndex = row.length - 1;
    const str = row[lastIndex];
    const strLength = str.length;
    const str2 = "...";
    for (let j = strLength - 1; j > 0; j--) {
      let s = str.slice(0, j);
      const str1 = s + str2;
      const width = ctx.measureText(str1).width + j * 0.2;
      if (width < contentWidth) {
        row.splice(lastIndex, 1, str1);
        break;
      }
    }
  }
  return row;
}
