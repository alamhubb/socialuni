import SocialuniAxiosCreate from "@socialuni/socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "@socialuni/socialuni-api-base/src/SocialuniAPIConfig";

const socialuniImRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniImUrl
})

export default socialuniImRequest
