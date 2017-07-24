package kr.or.connect.jgb.dao;

public class ProductSqls {
	final static String SELECT_ALL = 
			"select p.id,name,place_name,description,i.file_id " 
			+"from product p left outer join display_info d on p.id = d.product_id "
			+"left outer join product_image i on p.id = i.product_id "
			+"where p.id > :last_id "
			+"group by p.id "
			+"limit :limit";
	final static String SELECT_ALL_BY_CATEGORY =
			"select p.id,name,place_name,description,i.file_id " 
			+"from product p left outer join display_info d on p.id = d.product_id "
			+"left outer join product_image i on p.id = i.product_id "
			+"where p.category_id = :category_id and p.id > :last_id "
			+"group by p.id "
			+"limit :limit";
	
    final static String SELECT_BY_ID = "select id, name from product where id = :id";
    final static String UPDATE_BY_ID = "update product set name = :name where id = :id";
    final static String DELETE_BY_ID = "delete from product where id = :id";
    
	final static String SELECT_DETAIL_BY_ID = 
			"select p.id,name,place_lot,place_street,tel,homepage,email,description,event,content,"
			+"sales_start,sales_end,sales_flag "  
			+"from product p left outer join display_info dis on p.id = dis.product_id "
			+"left outer join product_detail de on p.id = de.product_id "
			+"where p.id = :id";
	
	final static String SELECT_PRICE_BY_PRODUCTID = 
			"select price_type,price,discount_rate from product_price where product_id = :product_id";
	
	
	
}
