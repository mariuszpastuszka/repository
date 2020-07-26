package pl.sda.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.repository.dao.UserDao;
import pl.sda.repository.domain.SdaUser;
import pl.sda.repository.dto.SdaUserDto;
import pl.sda.repository.repo_spring.MyUserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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

    @Transactional
    public boolean addTwoSdaUsers() {
        SdaUser first = new SdaUser(LocalDateTime.now().toString(), "first", "Java", 10, true);
        SdaUser second = new SdaUser(LocalDateTime.now().toString(), "second", "Java", 10_000, true);
//        myUserRepo.saveAll(List.of(first, second));
        myUserRepo.save(first);

        Random random = new Random();
        if (random.nextInt() % 2 == 0) {
            throw new RuntimeException();
        }
        myUserRepo.save(second);
        return true;
    }

    @Transactional
    public boolean changeSdaUser() {
        var sdaUser = myUserRepo.findById("11111111111111111111");
        sdaUser.ifPresent(sdaUser1 -> {
            sdaUser1.setName("Pamela");
        });
        return true;
    }
}
