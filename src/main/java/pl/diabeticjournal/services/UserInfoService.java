package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;
import pl.diabeticjournal.repository.UserInfoRepository;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
@Transactional
public class UserInfoService {

    private final UserInfoRepository userInfoRepo;

    public void addUserInfo(UserInfo userInfo, User user) {
        userInfo.setUser(user);
        userInfo.setAge(userInfo.getAge());
        userInfo.setDiabeticType(userInfo.getDiabeticType());
        userInfo.setGender(userInfo.getGender());
        userInfo.setHeight(userInfo.getHeight());
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setWeight(userInfo.getWeight());
        userInfoRepo.save(userInfo);

    }

    public UserInfo showUserInfo(User user) {
        return userInfoRepo.findUserInfoByUser(user).orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));
    }

    public boolean isUserInfoExists(User user) {
        return userInfoRepo.findUserInfoByUser(user).isPresent();
    }

    public UserInfo getUserInfoByUserId(Long id) {
        return userInfoRepo.findUserInfoByUserId(id).orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));
    }

    public void deleteUserInfo(UserInfo userInfo) {
        userInfoRepo.delete(userInfo);
    }

    public void editUserInfo(UserInfo userInfo) {
        userInfo.setUser(userInfo.getUser());
        userInfo.setAge(userInfo.getAge());
        userInfo.setDiabeticType(userInfo.getDiabeticType());
        userInfo.setGender(userInfo.getGender());
        userInfo.setHeight(userInfo.getHeight());
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setWeight(userInfo.getWeight());
        userInfoRepo.save(userInfo);

    }
}
