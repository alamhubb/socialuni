package io.agora.media;

import io.agora.media.ByteBuf;

/**
 * Created by Li on 10/1/2016.
 */
public interface Packable {
    io.agora.media.ByteBuf marshal(ByteBuf out);
}
