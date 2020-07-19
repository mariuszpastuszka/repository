package pl.sda.repository.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.sda.repository.domain.SdaUser;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * using jdbc template
 */
@Repository
@Slf4j
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE/UPDATE
    public SdaUser saveUser(SdaUser sdaUser) {
        log.info("saveUser() - argument: [{}]", sdaUser);
        return sdaUser;
    }

    public List<SdaUser> readAllUsers() {
        log.info("readAllUsers()");
        return Collections.emptyList();
    }

    public Optional<SdaUser> findUserByPesel(String pesel) {
        log.info("findUserByPesel() - pesel: [{}]", pesel);
        return Optional.empty();
    }

    public boolean deleteUserByPesel(String pesel) {
        log.info("deleteUserByPesel() - pesel: [{}]", pesel);
        return false;
    }
}
