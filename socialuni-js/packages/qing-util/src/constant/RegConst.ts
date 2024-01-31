export default class RegConst {
    static startWithNumber = /^[0-9]/;
    static startWithNumberOrLetter = /^[0-9a-zA-Z]/;
    static startWithLetter = /^[a-zA-Z]/;
    static startWithNotLetter = /^[^a-zA-Z]/;
    static isAllLetter = /^[a-zA-Z]+$/;
}
