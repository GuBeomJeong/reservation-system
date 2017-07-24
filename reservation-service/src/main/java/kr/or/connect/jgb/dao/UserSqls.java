package kr.or.connect.jgb.dao;

public class UserSqls {
	final static String SELECT_BY_EMAIL = "select id from users where email = :email";
	final static String SELECT_BY_ID = 
			"select id,username,email,tel,nickname,sns_id,sns_type from users where id = :id";
}
