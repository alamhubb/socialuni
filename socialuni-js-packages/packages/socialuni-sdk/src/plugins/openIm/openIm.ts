import {OpenImAPI} from "./OpenImAPI";
import OpenImWebSDK from "./OpenImWebSDK";

let openIm: OpenImAPI = null;
// #ifdef APP-PLUS
// import app_im from "./app";
// openIm = app_im;
// #endif
// #ifdef H5
openIm = new OpenImWebSDK();
// #endif
export default openIm;
