package member.repository;

import member.model.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, String> {
    RememberMeToken findBySeries(String series);
    List<RememberMeToken> findByUserName(String username);
}
