package pl.sda.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.repository.dao.UserDao;
import pl.sda.repository.domain.SdaUser;
import pl.sda.repository.dto.SdaUserDto;
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

    public SdaUserDto findAllUsersWithPageable(int pageNumber, int pageSize) {
        var sortOrder = Sort.by(Sort.Direction.ASC, "coursePrice")
                .and(Sort.by(Sort.Direction.DESC, "name"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortOrder);
        Page<SdaUser> queryResult = myUserRepo.findAll(pageable);
        return SdaUserDto.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .sdaUsers(queryResult.getContent())
                .numberOfPages(queryResult.getTotalPages())
                .build();
    }

}
