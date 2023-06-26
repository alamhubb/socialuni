export default class SystemUtil{
    static import(path:string){
        return import.meta.globEager(path)
    }
}
