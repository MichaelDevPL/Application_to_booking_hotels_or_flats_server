package app.web.appgateway.user.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
        ROLE_CLIENT_BASIC, ROLE_CLIENT_PREMIUM;

        @Override
        public String getAuthority() {
                return name();
        }
}
