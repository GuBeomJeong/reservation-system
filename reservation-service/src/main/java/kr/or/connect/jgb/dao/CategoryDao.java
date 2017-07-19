package kr.or.connect.jgb.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.jgb.domain.Category;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc; 
    private SimpleJdbcInsert insertAction; 
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class); 

   
    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource)  
                .withTableName("category")   
                .usingGeneratedKeyColumns("id"); 
    }

    public int insert(Category category){
        SqlParameterSource params = new BeanPropertySqlParameterSource(category);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public Category selectById(int id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(CategorySqls.SELECT_BY_ID,params,rowMapper);
    }
    
    public int update(Category category){
        SqlParameterSource params = new BeanPropertySqlParameterSource(category);
        return jdbc.update(CategorySqls.UPDATE_BY_ID, params);
    }

    public int delete(int id){
        Map<String, ?> params = Collections.singletonMap("id", id); // 수정할 수 없음 (immutable)
        return jdbc.update(CategorySqls.DELETE_BY_ID, params);
    }
    
    public List<Category> selectAll() {
    	Map<String, Object> params = Collections.emptyMap();
    	//jdbc.query(CategorySqls.SELECT_ALL,rowMapper);  // 스프링에서 빈 객체 사용
    	return jdbc.query(CategorySqls.SELECT_ALL,params,rowMapper);
    }
}
