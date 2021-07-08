package kl.socialnetwork.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configurable
public class ApplicationSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated()
                .simpDestMatchers("/app/**").hasAnyAuthority("ROOT", "ADMIN", "USER")
                .simpSubscribeDestMatchers("/topic/**","/queue/**", "/chat/**", "/user/**").hasAnyAuthority("ROOT", "ADMIN", "USER")
//                .simpTypeMatchers(SUBSCRIBE, MESSAGE).denyAll()
                .anyMessage().denyAll();
    }
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

}
