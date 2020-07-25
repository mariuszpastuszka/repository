package pl.sda.repository.service;

import org.springframework.stereotype.Service;
import pl.sda.repository.dao.UserDao;
import pl.sda.repository.domain.SdaUser;

import java.util.List;

@Service
public class SdaUserService {

    private UserDao userDao;

    public SdaUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<SdaUser> allUsers() {
        return userDao.readAllUsers();
    }

    public SdaUser findUserByPesel(String pesel) {
        return userDao.findUserByPesel(pesel).orElse(new SdaUser());
    }
}
