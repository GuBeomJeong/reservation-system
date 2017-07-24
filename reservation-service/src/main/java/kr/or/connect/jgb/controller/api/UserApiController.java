package kr.or.connect.jgb.controller.api;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.jgb.domain.Users;


@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@GetMapping
	public Users getProduct(HttpServletRequest request) {
		return (Users)request.getSession().getAttribute("user");
	}
	
}
