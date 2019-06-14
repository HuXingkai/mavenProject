package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * 根据用户名和密码获取匹配的用户
     * @param userName 用户名
     * @param password 密码
     * @return 1表示用户名密码正确，0表示用户名密码错误
     */
    public int getMatchCount(String userName,String password){
        String sqlStr = "SELECT count(*) FROM t_user WHERE user_name=? and password=?";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{userName, password},Integer.class);
    }

    private final static String MATCH_COUNT_SQL = "SELECT * FROM t_user WHERE user_name=?";

    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";

    /**
     * 通过用户名获得用户对象
     * @param userName 用户名
     * @return user对象
     */
    public User findUserByUserName(final String userName){
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }

    /**
     * 更新用户积分、最后登录IP、最后登录时间
     * @param user
     */
    public void updateLogeinInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIP(), user.getCredits(), user.getUserId()});
    }
}
