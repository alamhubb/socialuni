export default class SocialuniConfig {
    static readonly socialuniUrl = (import.meta.env.VITE_APP_SOCIALUNI_URL || 'https://api.socialuni.cn')
    static readonly socialuniUserUrl = (import.meta.env.VITE_APP_SOCIALUNI_USER_URL) || this.socialuniUrl
    static readonly socialuniCommunityUrl = (import.meta.env.VITE_APP_SOCIALUNI_COMMUNITY_URL) || this.socialuniUrl
    static readonly socialuniImUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_URL) || this.socialuniUrl
    static readonly socialuniImWebsocketUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_WEBSOCKET_URL) || this.socialuniUrl
    static readonly socialuniSecretKey = import.meta.env.VITE_APP_SOCIALUNI_SECRET_KEY || null
}

