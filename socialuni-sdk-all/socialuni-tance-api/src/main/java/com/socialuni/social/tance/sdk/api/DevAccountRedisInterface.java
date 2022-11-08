package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.tance.sdk.model.DevAccountModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


/**
 * @author qinkaiyuan
 * @since 1.0.0
 */
public interface DevAccountRedisInterface {
    DevAccountModel saveDevAccount(DevAccountModel devAccount);
}