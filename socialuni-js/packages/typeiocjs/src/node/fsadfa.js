const deepImportRE = /^([^@][^/]*)\/|^(@[^/]+\/[^/]+)\//;


const res= deepImportRE.exec('typeiocjs/src/TypeIocDecorator.ts')
console.log(res)
