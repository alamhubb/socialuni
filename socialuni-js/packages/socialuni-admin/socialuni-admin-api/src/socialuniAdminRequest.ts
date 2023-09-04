import SocialuniAxiosCreate from "@socialuni/socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "@socialuni/socialuni-api-base/src/SocialuniAPIConfig";

const socialuniAdminRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniUrl
})

export default socialuniAdminRequest
