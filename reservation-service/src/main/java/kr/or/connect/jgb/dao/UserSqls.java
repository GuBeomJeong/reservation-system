package kr.or.connect.jgb.dao;

public class UserSqls {
	final static String SELECT_BY_EMAIL = "select count(*) from users where email = :email";
}
