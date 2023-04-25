package hello.libraryapp.repository.user;

import hello.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean isUserNotExist(long id){
        String readsql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, id).isEmpty();
    }
    public boolean isUserNameNotExist(String name){
        String readsql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, name).isEmpty();
    }
    public void updateUserName(String name,long id){
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name,id);
    }
    public void deleteUser(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql,name);
    }
    public void saveUser(String name,Integer age){
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql,name,age);
    }
    public List<UserResponse> getUser(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name  = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id,name,age);
        });
    }


}
