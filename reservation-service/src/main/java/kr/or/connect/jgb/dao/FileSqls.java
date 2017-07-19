package kr.or.connect.jgb.dao;

public class FileSqls {
	final static String SELECT_ALL = "select * from file";
    final static String SELECT_BY_ID = "select * from file where id = :id";
    //final static String UPDATE_BY_ID = "update file set name = :name where id = :id";
    final static String DELETE_BY_ID = "delete from file where id = :id";
    
    
    final static String SELECT_BY_PRODUCTID = 
			"select file_id from product_image where product_id = :product_id";
    
    final static String SELECT_BY_RESERVATION_USER_COMMENTID = 
			"select file_id from reservation_user_comment_image where reservation_user_comment_id = :reservation_user_comment_id";
    
    
}
