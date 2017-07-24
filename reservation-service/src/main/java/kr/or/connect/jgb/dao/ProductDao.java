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

import kr.or.connect.jgb.domain.Product;
import kr.or.connect.jgb.domain.ProductImage;
import kr.or.connect.jgb.domain.ProductPrice;
import kr.or.connect.jgb.domain.vo.ProductDetailVO;
import kr.or.connect.jgb.domain.vo.ProductMainVO;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private SimpleJdbcInsert imageInsertAction; 
 
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    private RowMapper<ProductMainVO> mainVORowMapper = BeanPropertyRowMapper.newInstance(ProductMainVO.class); 
    private RowMapper<ProductDetailVO> detailVORowMapper = BeanPropertyRowMapper.newInstance(ProductDetailVO.class); 
    private RowMapper<ProductPrice> priceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
    
    
    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource)  
                .withTableName("product")   
                .usingGeneratedKeyColumns("id"); 
        this.imageInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product_image") 
                .usingGeneratedKeyColumns("id"); 
    }

    public int insert(Product product){
        SqlParameterSource params = new BeanPropertySqlParameterSource(product);
        return insertAction.executeAndReturnKey(params).intValue();
    }
    
    public int insertImage(ProductImage productImage){
        SqlParameterSource params = new BeanPropertySqlParameterSource(productImage);
        return imageInsertAction.executeAndReturnKey(params).intValue();
    }

    public Product selectById(int id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(ProductSqls.SELECT_BY_ID,params,rowMapper);
    }
    
    public int update(Product product){
        SqlParameterSource params = new BeanPropertySqlParameterSource(product);
        return jdbc.update(ProductSqls.UPDATE_BY_ID, params);
    }

    public int delete(int id){
        Map<String, ?> params = Collections.singletonMap("id", id);
        return jdbc.update(ProductSqls.DELETE_BY_ID, params);
    }
    
    public List<ProductMainVO> selectAll(int lastProductId) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("limit", 10);
    	params.put("last_id", lastProductId);
    	
    	return jdbc.query(ProductSqls.SELECT_ALL,params,mainVORowMapper);
    }
    
    public List<ProductMainVO> selectAllByCategory(int categoryId,int lastProductId){
    	Map<String, Object> params = new HashMap<>();
        params.put("category_id", categoryId);
        params.put("limit", 10);
    	params.put("last_id", lastProductId);
    	return jdbc.query(ProductSqls.SELECT_ALL_BY_CATEGORY,params,mainVORowMapper);
    }
    
    public ProductDetailVO selectDetailById(int productId) {
    	Map<String, Object> params = new HashMap<>();
        params.put("id", productId);
        return jdbc.queryForObject(ProductSqls.SELECT_DETAIL_BY_ID,params,detailVORowMapper);
    }
    
    public List<ProductPrice> selectPriceByProductId(int productId) {
    	Map<String, Object> params = new HashMap<>();
        params.put("product_id", productId);
    	return jdbc.query(ProductSqls.SELECT_PRICE_BY_PRODUCTID,params,priceRowMapper);
    }
}
