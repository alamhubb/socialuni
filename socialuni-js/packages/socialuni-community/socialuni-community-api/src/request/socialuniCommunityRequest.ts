import SocialuniAxiosCreate from "socialuni-api-base/src/SocialuniAxios";
import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";

const socialuniCommunityRequest = SocialuniAxiosCreate({
    baseURL: SocialuniAPIConfig.socialuniCommunityUrl
})

export default socialuniCommunityRequest

