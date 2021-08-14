package socialuni.social.sdk.web.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("socialuni.social.sdk.web")
@EnableConfigurationProperties({
        SocialuniWebProperties.class,
})
public class SocialWebSDKAutoConfiguration {
}
