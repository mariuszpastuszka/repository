package pl.sda.repository.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.sda.repository.domain.SdaUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * using jdbc template
 */
@Repository
@Slf4j
public class UserDao {

    private static final String SELECT_ALL_QUERY = "" +
            "select pesel, name, assigned_course, price, payed              " +
            "from SDA_USER;                                                 ";

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
//        jdbcTemplate.query("" +
//                        "select pesel, name, assigned_course, price, payed              " +
//                        "from SDA_USER;                                                 ",
//                new MyMapper());

         List<SdaUser> result = jdbcTemplate.query(SELECT_ALL_QUERY,
                (rs, num) -> new SdaUser(rs.getString("pesel"),
                        rs.getString("name"),
                        rs.getString("assigned_course"),
                        rs.getDouble("price"),
                        rs.getBoolean("payed")));
        log.info("readAllUsers(): {}", result);
        return result;
    }

    public Optional<SdaUser> findUserByPesel(String pesel) {
        log.info("findUserByPesel() - pesel: [{}]", pesel);
        return Optional.empty();
    }

    public boolean deleteUserByPesel(String pesel) {
        log.info("deleteUserByPesel() - pesel: [{}]", pesel);
        return false;
    }

    static class MyMapper implements RowMapper<SdaUser> {

        @Override
        public SdaUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SdaUser(rs.getString("pesel"),
                    rs.getString("name"),
                    rs.getString("assigned_course"),
                    rs.getDouble("price"),
                    rs.getBoolean("payed"));
        }
    }
}
