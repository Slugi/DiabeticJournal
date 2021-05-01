package pl.diabeticjournal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.Token;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

Optional<Token> findTokenByTokenValue(String value);
}
