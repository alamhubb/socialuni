package social.web.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socialuni.web")
public class SocialuniWebProperties {

    private String tokenSecretKey;
    private String tokenName;

}
