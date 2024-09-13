//父级属性，设置define
//子属性，沟槽函数中设计值


function Parent() {
    Object.defineProperty(this.constructor.prototype, 'name',  {
        value: 'haha',
        writable: true,
        enumerable: true,
        configurable: true
    })
}


function Child(){

}

Child.prototype = new Parent()

const child = new Child()

console.log(child.name)

