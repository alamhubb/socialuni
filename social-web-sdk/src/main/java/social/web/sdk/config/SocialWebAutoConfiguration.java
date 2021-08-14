package social.web.sdk.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        SocialuniWebProperties.class,
})
public class SocialWebAutoConfiguration {
}
