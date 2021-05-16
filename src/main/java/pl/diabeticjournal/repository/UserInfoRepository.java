package pl.diabeticjournal.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;

import java.util.Optional;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @EntityGraph(attributePaths = "user")
    Optional<UserInfo> findUserInfoByUser(User user);

    Optional<UserInfo> findUserInfoByUserId(Long id);
}