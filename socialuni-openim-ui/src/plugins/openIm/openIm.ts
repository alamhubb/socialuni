import {OpenImAPI} from "@/plugins/openIm/OpenImAPI";
import OpenImWebAPI from "@/plugins/openIm/OpenImWebAPI";

let openIm: OpenImAPI = null;
// #ifdef APP-PLUS
import app_im from "./app";
openIm = app_im;
// #endif
// #ifdef H5
openIm = new OpenImWebAPI();
// #endif
export default openIm;
