package social.web.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "socialuni.web")
public class SocialuniWebProperties {

    private String tokenSecretKey;
    private String tokenName;

}
