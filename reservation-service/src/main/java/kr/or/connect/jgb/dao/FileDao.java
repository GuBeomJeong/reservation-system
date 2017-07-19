package kr.or.connect.jgb.dao;

import java.util.Collections;
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

import kr.or.connect.jgb.domain.Files;


@Repository
public class FileDao {
	private NamedParameterJdbcTemplate jdbc; 
    private SimpleJdbcInsert insertAction; 
    private RowMapper<Files> rowMapper = BeanPropertyRowMapper.newInstance(Files.class); 


    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource) 
                .withTableName("file")  
                .usingGeneratedKeyColumns("id"); 
    }

    public int insert(Files file){
        SqlParameterSource params = new BeanPropertySqlParameterSource(file);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public Files selectById(int id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(FileSqls.SELECT_BY_ID,params,rowMapper);
    }
    
//    public int update(Files file){
//        SqlParameterSource params = new BeanPropertySqlParameterSource(file);
//        return jdbc.update(FileSqls.UPDATE_BY_ID, params);
//    }

    public int delete(int id){
        Map<String, ?> params = Collections.singletonMap("id", id);
        return jdbc.update(FileSqls.DELETE_BY_ID, params);
    }
    
    public List<Integer> selectByProduct(int productId){
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", productId);
        return jdbc.queryForList(FileSqls.SELECT_BY_PRODUCTID,params,Integer.class);
    }
    
    public List<Integer> selectByReservationUserComment(int reservationUserCommentId){
    	Map<String, Object> params = new HashMap<>();
        params.put("reservation_user_comment_id", reservationUserCommentId);
        return jdbc.queryForList(FileSqls.SELECT_BY_RESERVATION_USER_COMMENTID,params,Integer.class);
    }
}
