package pl.sda.repository.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.sda.repository.domain.SdaUser;

import javax.swing.text.html.Option;
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

    private static final String USER_SELECT_QUERY = "" +
            "select pesel, name, assigned_course, price, payed              " +
            "from SDA_USER                                                  " +
            "where pesel = ?;                                               ";

    private static final String SELECT_USER_BY_PESEL_AND_NAME = "" +
            "select pesel, name, assigned_course, price, payed              " +
            "from SDA_USER                                                  " +
            "where pesel = ? and name = ?;                                  ";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<SdaUser> rowMapper = (rs, num) -> new SdaUser(rs.getString("pesel"),
            rs.getString("name"),
            rs.getString("assigned_course"),
            rs.getDouble("price"),
            rs.getBoolean("payed"));

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

//        List<SdaUser> result = jdbcTemplate.query(SELECT_ALL_QUERY, rowMapper);
        List<SdaUser> result = jdbcTemplate.query(SELECT_ALL_QUERY, this::mapRowToUser);
//        List<SdaUser> result = jdbcTemplate.query(SELECT_ALL_QUERY, UserDao::mapRowToUserStatic);
//        List<SdaUser> result = jdbcTemplate.query(SELECT_ALL_QUERY, (rs, rowNum) -> mapRowToUser(rs, rowNum));
        log.info("readAllUsers(): {}", result);
        return result;
    }

    public Optional<SdaUser> findUserByPesel(String pesel) {
        log.info("findUserByPesel() - pesel: [{}]", pesel);

        Optional<SdaUser> result = Optional.empty();
        try {
            SdaUser queryResult = jdbcTemplate.queryForObject(USER_SELECT_QUERY, this::mapRowToUser, pesel);
            return Optional.ofNullable(queryResult);
        } catch (DataAccessException exc) {
            // haha
            log.warn("exception occurred during query for object", exc);
        }
        return result;
    }

    public Optional<SdaUser> findUserByPeselAndName(String pesel, String name) {
         jdbcTemplate.queryForObject(SELECT_USER_BY_PESEL_AND_NAME, this::mapRowToUser, pesel, name);

         // TODO: homework
         return Optional.empty();
    }

    public boolean deleteUserByPesel(String pesel) {
        log.info("deleteUserByPesel() - pesel: [{}]", pesel);
//        jdbcTemplate.update() - can handle: insert, update, delete
        return false;
    }


    private static SdaUser mapRowToUserStatic(ResultSet rs, int rowNum) throws SQLException {
        return new SdaUser(rs.getString("pesel"),
                rs.getString("name"),
                rs.getString("assigned_course"),
                rs.getDouble("price"),
                rs.getBoolean("payed"));
    }

    private SdaUser mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new SdaUser(rs.getString("pesel"),
                rs.getString("name"),
                rs.getString("assigned_course"),
                rs.getDouble("price"),
                rs.getBoolean("payed"));
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
