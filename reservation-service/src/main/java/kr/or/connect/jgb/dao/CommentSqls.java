package kr.or.connect.jgb.dao;

public class CommentSqls {
	final static String SELECT_BY_PRODUCTID_3 = 
			"select c.id,c.create_date,score,comment,username "
			+ "from reservation_user_comment c inner join users u on c.user_id = u.id "
			+ "where product_id = :product_id limit 3";
	
	final static String COUNT_AVERAGE_BY_PRODUCTID =
			"select count(*) as count,avg(score) as average from reservation_user_comment "
			+ "where product_id = :product_id";
}
