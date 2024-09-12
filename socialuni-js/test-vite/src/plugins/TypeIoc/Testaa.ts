interface TTTTAaaa {
    eat()
}

export function Service() {
    console.log(66)
}

@Service
export default class B implements TTTTAaaa {
    static name: haha = 'dada'

    eat() {
        console.log('eat')
    }

}

