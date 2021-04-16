package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;
import pl.diabeticjournal.repository.UserInfoRepository;
import javax.transaction.Transactional;
import java.security.Principal;


@Service
@AllArgsConstructor
@Transactional
public class UserInfoService {

  private UserInfoRepository userInfoRepo;

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

}
