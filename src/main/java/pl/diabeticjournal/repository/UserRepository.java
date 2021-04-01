package pl.diabeticjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.diabeticjournal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
