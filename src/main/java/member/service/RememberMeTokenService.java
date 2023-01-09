package member.service;

import member.model.RememberMeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class RememberMeTokenService implements PersistentTokenRepository {
    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        RememberMeToken newToken = new RememberMeToken(token);
        this.remembermeTokenRepository.save(newToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken token = this.rememberMeTokenRepository.findBySeries(series);
        if(token != null) {
            token.setToken(tokenValue);
            token.setLastUsed(lastUsed);
            this.rememberMeeTokenRepository.save(token);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {

    }


    @Override
    public void createNewToken(PersistentRememberMeToken token) {

    }
}
