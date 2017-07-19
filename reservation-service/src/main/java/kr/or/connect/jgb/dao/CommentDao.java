package kr.or.connect.jgb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.jgb.domain.Comment;
import kr.or.connect.jgb.domain.dto.CommentCountAvgDTO;
import kr.or.connect.jgb.domain.vo.CommentVO;

@Repository
public class CommentDao {

	private NamedParameterJdbcTemplate jdbc; 
    private SimpleJdbcInsert insertAction; 
    private RowMapper<CommentVO> rowMapper = BeanPropertyRowMapper.newInstance(CommentVO.class); 
    private RowMapper<CommentCountAvgDTO> countAvgRowMapper = BeanPropertyRowMapper.newInstance(CommentCountAvgDTO.class); 

    public CommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource) 
                .withTableName("reservation_user_comment")  
                .usingGeneratedKeyColumns("id"); 
    }
    
    public List<CommentVO> selectThreeByProduct(int productId){
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", productId);
        return jdbc.query(CommentSqls.SELECT_BY_PRODUCTID_3,params,rowMapper);
    }
    
    public CommentCountAvgDTO selectConuntAverageByProduct(int productId){
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", productId);
        return jdbc.queryForObject(CommentSqls.COUNT_AVERAGE_BY_PRODUCTID,params,countAvgRowMapper);
    }
    
    

}
