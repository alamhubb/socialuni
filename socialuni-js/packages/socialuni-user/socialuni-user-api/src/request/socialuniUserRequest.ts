import SocialuniAxiosCreate from "@socialuni/socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "@socialuni/socialuni-api-base/src/SocialuniAPIConfig";

const socialuniUserRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniUserUrl
})

export default socialuniUserRequest
