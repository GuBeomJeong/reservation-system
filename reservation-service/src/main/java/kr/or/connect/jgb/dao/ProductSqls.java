package kr.or.connect.jgb.dao;

public class ProductSqls {
	final static String SELECT_ALL =
			"select p.id,name,place_name,description,i.file_id " + 
			"from product p,display_info d,product_image i " + 
			"where p.id = d.product_id and p.id = i.product_id "
			+ "group by p.id "
			+ "limit :limit offset :offset";
	final static String SELECT_ALL_BY_CATEGORY = 
			"select p.id,name,place_name,description,i.file_id" + 
			"from product p,display_info d,product_image i" + 
			"where p.id = d.product_id and p.id = i.product_id "
			+ "and p.category_id = :category_id "
			+ "group by p.id "
			+ "limit :limit offset :offset";
	//final static String SELECT_ALL = "select * from product limit :limit offset :offset";
	//final static String SELECT_ALL_BY_CATEGORY = "select * from product where category_id = :category_id limit :limit offset :offset";
    final static String SELECT_BY_ID = "select id, name from product where id = :id";
    final static String UPDATE_BY_ID = "update product set name = :name where id = :id";
    final static String DELETE_BY_ID = "delete from product where id = :id";
    
	final static String SELECT_DETAIL_BY_ID = 
			"select p.id,name,place_lot,place_street,tel,homepage,email,description,event,content " + 
			"from product p,display_info dis,product_detail de " + 
			"where p.id = :id and p.id = dis.product_id and p.id = de.product_id ";
	
	
	
}
