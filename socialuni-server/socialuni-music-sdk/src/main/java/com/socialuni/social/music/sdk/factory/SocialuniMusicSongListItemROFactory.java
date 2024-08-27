package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomSongListDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicSongListItemRO;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniMusicSongListItemROFactory {

    public static SocialuniMusicSongListItemRO getSongListItemRO(SocialuniMusicRoomSongListDO item) {
        SocialuniMusicSongListItemRO socialuniMusicSongListItemRO = new SocialuniMusicSongListItemRO();

        socialuniMusicSongListItemRO.setNo(item.getOrderNo());
        socialuniMusicSongListItemRO.setSongId(item.getSongId());
        socialuniMusicSongListItemRO.setMusicName(item.getMusicName());
        socialuniMusicSongListItemRO.setAuthor(item.getAuthor());
        socialuniMusicSongListItemRO.setMusicTime(item.getMusicTime());

        return socialuniMusicSongListItemRO;
    }

    public static List<SocialuniMusicSongListItemRO> getSongListItemROs(List<SocialuniMusicRoomSongListDO> items) {
        return items.stream().map(SocialuniMusicSongListItemROFactory::getSongListItemRO).collect(Collectors.toList());
    }
}
