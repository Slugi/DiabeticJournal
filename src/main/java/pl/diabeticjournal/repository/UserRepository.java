package pl.diabeticjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUserName(String userName);
Optional<User> findUserByEmail(String email);




}
