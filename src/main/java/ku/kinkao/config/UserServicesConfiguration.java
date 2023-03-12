package ku.kinkao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;

@Configuration
public class UserServicesConfiguration {

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return new OAuth2UserServiceImpl();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserServiceImpl();
    }

    private class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
        private final DefaultOAuth2UserService delegate =
                new DefaultOAuth2UserService();

        @Override
        public OAuth2User loadUser(OAuth2UserRequest request)
                throws OAuth2AuthenticationException {

            String attribute = request.getClientRegistration()
                    .getProviderDetails()
                    .getUserInfoEndpoint()
                    .getUserNameAttributeName();
            OAuth2User user = delegate.loadUser(request);

            try {
                user = new DefaultOAuth2User(new ArrayList<>(),
                        user.getAttributes(), attribute);

            } catch (OAuth2AuthenticationException exception) {
                throw exception;
            }

            return user;
        }
    }

    private class OidcUserServiceImpl extends OidcUserService {

        { setOauth2UserService(oAuth2UserService()); }

        @Override
        public OidcUser loadUser(OidcUserRequest request)
                throws OAuth2AuthenticationException {
            String attribute = request.getClientRegistration()
                    .getProviderDetails()
                    .getUserInfoEndpoint()
                    .getUserNameAttributeName();
            OidcUser user = super.loadUser(request);

            try {
                user = new DefaultOidcUser(new ArrayList<>(),
                        user.getIdToken(), user.getUserInfo(), attribute);

            } catch (OAuth2AuthenticationException exception) {
                throw exception;
            }
            return user;
        }
    }
}
