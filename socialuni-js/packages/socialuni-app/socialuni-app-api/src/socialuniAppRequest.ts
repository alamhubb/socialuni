import SocialuniAxiosCreate from "socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";

const socialuniAppRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniUrl
})

export default socialuniAppRequest
