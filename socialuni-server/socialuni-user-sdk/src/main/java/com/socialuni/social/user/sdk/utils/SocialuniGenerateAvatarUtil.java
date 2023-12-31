package com.socialuni.social.user.sdk.utils;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;

import java.util.*;

public class SocialuniGenerateAvatarUtil {
    static final List<String> boyAvatars = Arrays.asList(
            "socialuni/common/app/defaultAvatar/boy/00e93901213fb80e2392b51a73d12f2eb83894b8.jpg",
            "socialuni/common/app/defaultAvatar/boy/023b5bb5c9ea15cee6cb96c5f3003af33b87b2d1.jpg",
            "socialuni/common/app/defaultAvatar/boy/09fa513d269759eee2d25a05f7fb43166c22df7a.jpg",
            "socialuni/common/app/defaultAvatar/boy/0d338744ebf81a4c42c42d04922a6059242da60d.jpg",
            "socialuni/common/app/defaultAvatar/boy/0bd162d9f2d3572c50cc0810cf13632763d0c3d4.jpg",
            "socialuni/common/app/defaultAvatar/boy/0ff41bd5ad6eddc4dfe585357cdbb6fd536633f9.jpg",
            "socialuni/common/app/defaultAvatar/boy/2e2eb9389b504fc28a7564c8a0dde71191ef6dd1.jpg",
            "socialuni/common/app/defaultAvatar/boy/359b033b5bb5c9eadad6752f9039b6003bf3b3d1.jpg",
            "socialuni/common/app/defaultAvatar/boy/3812b31bb051f8196c16ea8f9fb44aed2f73e7c1.jpg",
            "socialuni/common/app/defaultAvatar/boy/42166d224f4a20a45256e80ad5529822730ed0b7.jpg",
            "socialuni/common/app/defaultAvatar/boy/472309f79052982277d6f80e92ca7bcb0b46d412.jpg",
            "socialuni/common/app/defaultAvatar/boy/472309f79052982298d7030d92ca7bcb0b46d41c.jpg",
            "socialuni/common/app/defaultAvatar/boy/4bed2e738bd4b31c4b49a075c2d6277f9f2ff883.jpg",
            "socialuni/common/app/defaultAvatar/boy/574e9258d109b3de7146a5a489bf6c81810a4c05.jpg",
            "socialuni/common/app/defaultAvatar/boy/5ab5c9ea15ce36d30421f5fc7ff33a87e850b1d1.jpg",
            "socialuni/common/app/defaultAvatar/boy/5fdf8db1cb13495488fed538414e9258d0094a8d.jpg",
            "socialuni/common/app/defaultAvatar/boy/63d9f2d3572c11dfebff2bef262762d0f603c2d4.jpg",
            "socialuni/common/app/defaultAvatar/boy/6f061d950a7b0208a111092f27d9f2d3562cc820.jpg",
            "socialuni/common/app/defaultAvatar/boy/810a19d8bc3eb1355baa8375e31ea8d3fc1f448a.jpg",
            "socialuni/common/app/defaultAvatar/boy/8718367adab44aede996882af61c8701a08bfb20.jpg",
            "socialuni/common/app/defaultAvatar/boy/9358d109b3de9c8253abdafd6381800a18d843f1.jpg",
            "socialuni/common/app/defaultAvatar/boy/9a504fc2d5628535d639e8efd5ef76c6a6ef6326.jpg",
            "socialuni/common/app/defaultAvatar/boy/9c16fdfaaf51f3de62d6281d83eef01f3a297967.jpg",
            "socialuni/common/app/defaultAvatar/boy/a6efce1b9d16fdfaf8960023f18f8c5495ee7b01.jpg",
            "socialuni/common/app/defaultAvatar/boy/b151f8198618367a1f5149136b738bd4b21ce520.jpg",
            "socialuni/common/app/defaultAvatar/boy/b151f8198618367a36ba96186b738bd4b21ce5c1.jpg",
            "socialuni/common/app/defaultAvatar/boy/b8014a90f603738d67bfcaeff61bb051f919ec03.jpg",
            "socialuni/common/app/defaultAvatar/boy/caef76094b36acaf0c0eac3239d98d1000e99c15.jpg",
            "socialuni/common/app/defaultAvatar/boy/cf1b9d16fdfaaf5199c95572c95494eef11f7ad1.jpg",
            "socialuni/common/app/defaultAvatar/boy/d058ccbf6c81800aeb1cc8c2f43533fa838b4705.jpg",
            "socialuni/common/app/defaultAvatar/boy/dbb44aed2e738bd40ec388ffe48b87d6267ff91a.jpg",
            "socialuni/common/app/defaultAvatar/boy/dbb44aed2e738bd4781686ffe48b87d6267ff9cd.jpg",
            "socialuni/common/app/defaultAvatar/boy/dc54564e9258d1095f1ae87e9458ccbf6d814d05.jpg",
            "socialuni/common/app/defaultAvatar/boy/f703738da9773912425f62a5bd198618377ae2fe.jpg",
            "socialuni/common/app/defaultAvatar/boy/f9198618367adab482a52f8dced4b31c8601e483.jpg",
            "socialuni/common/app/defaultAvatar/boy/faedab64034f78f0684fbd3d3c310a55b2191cb3.jpg"
    );

    static final List<String> girlAvatars = Arrays.asList(
            "socialuni/common/app/defaultAvatar/girl/0df431adcbef76096deda5516bdda3cc7dd99e1d.jpg",
            "socialuni/common/app/defaultAvatar/girl/11385343fbf2b211368ed256978065380cd78e02.jpg",
            "socialuni/common/app/defaultAvatar/girl/1e30e924b899a901f01a72f058950a7b0308f5cd.jpg",
            "socialuni/common/app/defaultAvatar/girl/314e251f95cad1c8d674cae93a3e6709c83d5134.jpg",
            "socialuni/common/app/defaultAvatar/girl/4034970a304e251ffa38d236e286c9177e3e5334.jpg",
            "socialuni/common/app/defaultAvatar/girl/32fa828ba61ea8d3914540cad20a304e241f589c.jpg",
            "socialuni/common/app/defaultAvatar/girl/4610b912c8fcc3ce523008fed745d688d53f200f.jpg",
            "socialuni/common/app/defaultAvatar/girl/4e4a20a4462309f7788b97dc370e0cf3d6cad61c.jpg",
            "socialuni/common/app/defaultAvatar/girl/503d269759ee3d6db544bd0506166d224e4ade7a.jpg",
            "socialuni/common/app/defaultAvatar/girl/54fbb2fb43166d22ab9b2f5a032309f79152d214.jpg",
            "socialuni/common/app/defaultAvatar/girl/55e736d12f2eb938c647ae3f90628535e4dd6fd1.jpg",
            "socialuni/common/app/defaultAvatar/girl/5d6034a85edf8db1fb0760a24c23dd54574e74ce.jpg",
            "socialuni/common/app/defaultAvatar/girl/6a600c338744ebf84bf2e4f29cf9d72a6159a778.jpg",
            "socialuni/common/app/defaultAvatar/girl/6c224f4a20a44623ee269facdd22720e0df3d714.jpg",
            "socialuni/common/app/defaultAvatar/girl/728da9773912b31b42152aedc318367adbb4e1fe.jpg",
            "socialuni/common/app/defaultAvatar/girl/728da9773912b31b61990be4c318367adbb4e103.jpg",
            "socialuni/common/app/defaultAvatar/girl/730e0cf3d7ca7bcbe4a7fde1fb096b63f724a824.jpg",
            "socialuni/common/app/defaultAvatar/girl/730e0cf3d7ca7bcbf099f1e1fb096b63f724a81a.jpg",
            "socialuni/common/app/defaultAvatar/girl/7aec54e736d12f2e8a7991ae0ac2d562843568fb.jpg",
            "socialuni/common/app/defaultAvatar/girl/7c1ed21b0ef41bd52151d93a14da81cb38db3df9.jpg",
            "socialuni/common/app/defaultAvatar/girl/8326cffc1e178a8264b5aa6cb303738da877e876.jpg",
            "socialuni/common/app/defaultAvatar/girl/8601a18b87d6277f43f6f3f56d381f30e824fc1c.jpg",
            "socialuni/common/app/defaultAvatar/girl/8644ebf81a4c510fcd3223d72559252dd52aa50d.jpg",
            "socialuni/common/app/defaultAvatar/girl/86d6277f9e2f0708e5e019ceac24b899a801f213.jpg",
            "socialuni/common/app/defaultAvatar/girl/908fa0ec08fa513ddb3fb613786d55fbb3fbd963.jpg",
            "socialuni/common/app/defaultAvatar/girl/a08b87d6277f9e2f4dc79ebe0830e924b999f392.jpg",
            "socialuni/common/app/defaultAvatar/girl/a1ec08fa513d26978751329310fbb2fb4216d87a.jpg",
            "socialuni/common/app/defaultAvatar/girl/b151f8198618367a0f7cb9106b738bd4b21ce503.jpg",
            "socialuni/common/app/defaultAvatar/girl/b151f8198618367a1dfc4b136b738bd4b21ce583.jpg",
            "socialuni/common/app/defaultAvatar/girl/b219ebc4b74543a970fa08015b178a82b801141e.jpg",
            "socialuni/common/app/defaultAvatar/girl/b21c8701a18b87d6dc5a92c4090828381e30fdd5.jpg",
            "socialuni/common/app/defaultAvatar/girl/b3119313b07eca80cda56effcc2397dda1448302.jpg",
            "socialuni/common/app/defaultAvatar/girl/b64543a98226cffceacb4d7ffc014a90f703ea1f.jpg",
            "socialuni/common/app/defaultAvatar/girl/b7003af33a87e950ae7ab8a155385343faf2b48a.jpg",
            "socialuni/common/app/defaultAvatar/girl/bba1cd11728b4710c150c20286cec3fdfd032384.jpg",
            "socialuni/common/app/defaultAvatar/girl/c75c10385343fbf2168cf13bed7eca8065388f02.jpg",
            "socialuni/common/app/defaultAvatar/girl/c8ea15ce36d3d5396fae06057f87e950342ab061.jpg",
            "socialuni/common/app/defaultAvatar/girl/c995d143ad4bd113d4fc83fc1fafa40f4afb05ce.jpg",
            "socialuni/common/app/defaultAvatar/girl/cb8065380cd79123c9c766b4e8345982b3b780cf.jpg",
            "socialuni/common/app/defaultAvatar/girl/cefc1e178a82b901bd4c31fe368da9773812ef1f.jpg",
            "socialuni/common/app/defaultAvatar/girl/d01373f082025aaf0e034a05beedab64024f1ace.jpg",
            "socialuni/common/app/defaultAvatar/girl/d31b0ef41bd5ad6e50fb5524c4cb39dbb7fd3cf9.jpg",
            "socialuni/common/app/defaultAvatar/girl/d50735fae6cd7b895b8c07df182442a7d8330e8d.jpg",
            "socialuni/common/app/defaultAvatar/girl/eac4b74543a982261548feebcf82b9014b90eb76.jpg",
            "socialuni/common/app/defaultAvatar/girl/eaf81a4c510fd9f9c2e194a4602dd42a2934a40d.jpg",
            "socialuni/common/app/defaultAvatar/girl/eaf81a4c510fd9f9d1ca63a7602dd42a2934a41a.jpg",
            "socialuni/common/app/defaultAvatar/girl/f11f3a292df5e0fecb74bdcf196034a85fdf728b.jpg",
            "socialuni/common/app/defaultAvatar/girl/fcfaaf51f3deb48fd6936713b51f3a292cf57801.jpg"
    );

    public static String getBoyAvatar() {
        Random random = new Random();
        Integer size = boyAvatars.size();
        return SocialuniSystemConst.getStaticResourceUrl() + boyAvatars.get(random.nextInt(size)) + "!avatar";
    }

    public static String getGirlAvatar() {
        Random random = new Random();
        Integer size = girlAvatars.size();
        return SocialuniSystemConst.getStaticResourceUrl() + girlAvatars.get(random.nextInt(size)) + "!avatar";
    }
}
