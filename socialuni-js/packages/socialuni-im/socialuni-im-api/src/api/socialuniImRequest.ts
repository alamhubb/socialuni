import SocialuniAxiosCreate from "socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";

const socialuniImRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniImUrl
})

export default socialuniImRequest
