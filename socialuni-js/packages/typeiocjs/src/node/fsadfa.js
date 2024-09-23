const deepImportRE = /^([^@][^/]*)\/|^(@[^/]+\/[^/]+)\//;


const res= deepImportRE.exec('vue-router')
console.log(res)
