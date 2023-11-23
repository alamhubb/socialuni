// 在一个 SocialuniMusic.d.ts 文件中
import '@vue/runtime-core';
import {SocialuniMusicStore} from "./store/SocialuniMusicStore";
import {SocialuniMusicRoleId} from "./constant/SocialuniMusicRoleId";

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $socialuniMusicStore: SocialuniMusicStore
        SocialuniMusicRoleId: typeof SocialuniMusicRoleId
    }
}