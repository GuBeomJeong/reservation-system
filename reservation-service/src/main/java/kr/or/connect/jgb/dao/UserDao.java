package kr.or.connect.jgb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.jgb.domain.Category;
import kr.or.connect.jgb.domain.Comment;
import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.CommentCountAvgDTO;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;
import kr.or.connect.jgb.domain.vo.CommentVO;

@Repository
public class UserDao {

	private NamedParameterJdbcTemplate jdbc; 
    private SimpleJdbcInsert insertAction; 
    private RowMapper<Users> rowMapper = BeanPropertyRowMapper.newInstance(Users.class); 

    public UserDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource) 
                .withTableName("users")  
                .usingGeneratedKeyColumns("id"); 
    }
    
    public int insert(Users users){
        SqlParameterSource params = new BeanPropertySqlParameterSource(users);
        return insertAction.executeAndReturnKey(params).intValue();
    }
        
    public Integer checkUser(String email){
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return jdbc.queryForObject(UserSqls.SELECT_BY_EMAIL,params,Integer.class);
    }
    
    public Users selectById(int id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(UserSqls.SELECT_BY_ID,params,rowMapper);
    }
    

    
    

}
