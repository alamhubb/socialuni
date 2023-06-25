export default class SocialuniAPIConfig {
    static readonly socialuniUrl = (import.meta.env.VITE_APP_SOCIALUNI_URL || 'https://api.socialuni.cn')
    static readonly socialuniUserUrl = (import.meta.env.VITE_APP_SOCIALUNI_USER_URL) || this.socialuniUrl
    static readonly socialuniCommunityUrl = (import.meta.env.VITE_APP_SOCIALUNI_COMMUNITY_URL) || this.socialuniUrl
    static readonly socialuniImUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_URL) || this.socialuniUrl
    static readonly socialuniWebsocketUrl = (import.meta.env.VITE_APP_SOCIALUNI_WEBSOCKET_URL) || 'wss://api.socialuni.cn'
    static readonly socialuniSecretKey = import.meta.env.VITE_APP_SOCIALUNI_SECRET_KEY || null
    // static readonly socialuniUrl = 'https://api.socialuni.cn'
    // static readonly socialuniUserUrl = this.socialuniUrl
    // static readonly socialuniCommunityUrl = this.socialuniUrl
    // static readonly socialuniImUrl = this.socialuniUrl
    // static readonly socialuniWebsocketUrl = 'wss://api.socialuni.cn'
    // static readonly socialuniSecretKey = null
}

