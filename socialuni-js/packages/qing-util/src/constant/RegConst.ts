export default class RegConst {
    static startWithNumber = /^[0-9]/;
    static startWithNumberOrLetter = /^[0-9a-zA-Z]/;
    static startWithLetter = /^[a-z]/;
    static startWithNotLetter = /^[^a-z]/;
    static isAllLetter = /^[a-zA-Z]+$/;
}
