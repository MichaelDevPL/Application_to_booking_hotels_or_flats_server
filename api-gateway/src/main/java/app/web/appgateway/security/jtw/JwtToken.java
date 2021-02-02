package app.web.appgateway.security.jtw;

public class JwtToken {
    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
