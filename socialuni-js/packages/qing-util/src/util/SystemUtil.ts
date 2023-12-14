export default class SystemUtil{
    static import(path:string){
        return import.meta.glob(path,{eager:true})
    }
}
