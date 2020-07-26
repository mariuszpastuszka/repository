package pl.sda.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.sda.repository.dao.UserDao;
import pl.sda.repository.domain.SdaUser;
import pl.sda.repository.repo_spring.MyUserRepo;

import java.util.List;

@Service
public class SdaUserService {

    private UserDao userDao;
    private MyUserRepo myUserRepo;

    public SdaUserService(UserDao userDao, MyUserRepo myUserRepo) {
        this.userDao = userDao;
        this.myUserRepo = myUserRepo;
    }

    public List<SdaUser> allUsers() {
        return userDao.readAllUsers();
    }

    public SdaUser findUserByPesel(String pesel) {
        return userDao.findUserByPesel(pesel).orElse(new SdaUser());
    }

    public List<SdaUser> findAllUsersWithPageable(int pageNumber, int pageSize) {
        Page<SdaUser> queryResult = myUserRepo.findAll(PageRequest.of(pageNumber, pageSize));
        return queryResult.getContent();
    }
}
