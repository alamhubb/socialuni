let im = null;
// #ifdef APP-PLUS
import app_im from "@/app";
im = app_im;
// #endif
// #ifdef H5
import web_im from "@/web";
im = web_im;
// #endif
export default im;
