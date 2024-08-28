package com.socialuni.social.music.sdk.factory;

import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomSongListDO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicSongListItemRO;

import java.util.List;
import java.util.stream.Collectors;

public class SocialuniMusicSongListItemROFactory {

    public static SocialuniMusicSongListItemRO getSongListItemRO(SocialuniMusicRoomSongListDO item) {
        SocialuniMusicSongListItemRO socialuniMusicSongListItemRO = new SocialuniMusicSongListItemRO();

        socialuniMusicSongListItemRO.setMusicId(item.getId());
        socialuniMusicSongListItemRO.setNo(item.getOrderNo());
        socialuniMusicSongListItemRO.setSongId(item.getSongId());
        socialuniMusicSongListItemRO.setMusicName(item.getMusicName());
        socialuniMusicSongListItemRO.setAuthor(item.getAuthor());
        socialuniMusicSongListItemRO.setMusicTime(item.getMusicTime());


        socialuniMusicSongListItemRO.setMusicUrl(item.getMusicUrl());
        socialuniMusicSongListItemRO.setAlbum(item.getAlbum());
        socialuniMusicSongListItemRO.setAlbumImg(item.getAlbumImg());

        return socialuniMusicSongListItemRO;
    }


    public static SocialuniMusicRoomSongListDO getSongDOByQO(SocialuniMusicSongListItemRO item) {
        SocialuniMusicRoomSongListDO socialuniMusicSongListItemDO = new SocialuniMusicRoomSongListDO();

        socialuniMusicSongListItemDO.setOrderNo(item.getNo());
        socialuniMusicSongListItemDO.setSongId(item.getSongId());
        socialuniMusicSongListItemDO.setMusicName(item.getMusicName());
        socialuniMusicSongListItemDO.setAuthor(item.getAuthor());
        socialuniMusicSongListItemDO.setMusicTime(item.getMusicTime());

        socialuniMusicSongListItemDO.setMusicUrl(item.getMusicUrl());
        socialuniMusicSongListItemDO.setAlbum(item.getAlbum());
        socialuniMusicSongListItemDO.setAlbumImg(item.getAlbumImg());

        return socialuniMusicSongListItemDO;
    }


    public static List<SocialuniMusicSongListItemRO> getSongListItemROs(List<SocialuniMusicRoomSongListDO> items) {
        return items.stream().map(SocialuniMusicSongListItemROFactory::getSongListItemRO).collect(Collectors.toList());
    }
}
