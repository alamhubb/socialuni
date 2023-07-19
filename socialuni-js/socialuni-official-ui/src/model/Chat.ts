import Message from '@/model/Message'

export default class Chat {
    public id: number
    public nickname: string
    public img: string
    public type: string
    public messages: Message[]
}
