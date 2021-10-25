import User from "@/model/User"
import Chat from "@/model/Chat"

export default class Notify {
    public id: number
    public user: User
    public postUser: User
    public chat: Chat
    public comment: any
    public match: any
    public type: string
    public notifyStatus: number
    public date: Date
}
