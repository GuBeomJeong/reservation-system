package kr.or.connect.jgb.dao;

public class UserSqls {
	final static String COUNT_BY_EMAIL = "select count(*) from users where email = :email";
}
