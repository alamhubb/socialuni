package io.agora.media;

import io.agora.media.ByteBuf;
import io.agora.media.Packable;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
