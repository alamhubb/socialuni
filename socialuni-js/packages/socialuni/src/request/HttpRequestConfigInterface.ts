import UniUtil from "socialuni-util/src/util/UniUtil"
import { Config, RequestConfig, Response } from "./UniAppHttpRequest"
import { socialuniTokenModule } from "../store/SocialuniTokenModule"

export interface HttpRequestConfigInterface {
  getConfig: () => Config

  requestBefore: (config: RequestConfig) => RequestConfig

  responseSuccess: (response: Response) => any

  responseFail: (response: Response) => Response
}
