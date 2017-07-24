package kr.or.connect.jgb.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kr.or.connect.jgb.domain.Users;
import kr.or.connect.jgb.domain.dto.NaverLoginUserInfo;
import kr.or.connect.jgb.domain.dto.NaverLoginUserResult;
import kr.or.connect.jgb.domain.dto.NaverLoginUserToken;
import kr.or.connect.jgb.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private UserService userService;

	@Autowired
	LoginController(UserService userService){
		this.userService = userService;
	}
	
	@Value("${spring.naverlogin.clientId}")
	private String clientId;
	 
	@Value("${spring.naverlogin.clientSecret}")
	private String clientSecret;
	
	private final String requestURL = "https://nid.naver.com/oauth2.0/authorize?";
	private final String tokenURL = "https://nid.naver.com/oauth2.0/token?";
	private final String callbackURL = "http://localhost:8080/login/callback";
	private final String profileURL = "https://openapi.naver.com/v1/nid/me";
	
	@GetMapping
	public String naverLogin(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		
		if(request.getSession().getAttribute("user") == null) {
			String state = generateState();
		
			request.getSession().setAttribute("state", state);
			
			
			String URL = requestURL +
					"client_id="+clientId+
					"&response_type=code"+
					"&redirect_uri=" + URLEncoder.encode(callbackURL,"UTF-8") +
					"&state=" + state;
			
			return "redirect:"+URL;
		}else {
			return "redirect:/myreservation";
		}
	}
	
	public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	
	@GetMapping("/callback")
	public String naverCallback(
			@RequestParam(value="code",required=false) String code, 
			@RequestParam("state") String state,
			Model model,HttpServletRequest request) {
		// 상태 토큰으로 사용할 랜덤 문자열 생성
		HttpSession session = request.getSession();
		if(!session.getAttribute("state").equals(state)) {
			System.out.println("잘못된 접근");	
		}else {
			String URL = tokenURL
					+"client_id="+clientId
					+"&client_secret="+clientSecret
					+"&grant_type=authorization_code"
					+"&state="+state
					+"&code="+code;
			RestTemplate restTemplate = new RestTemplate();
			NaverLoginUserToken userToken = restTemplate.getForObject(URL, NaverLoginUserToken.class);
						
			HttpHeaders header = new HttpHeaders(); 
			header.set("Authorization", "Bearer " + userToken.getAccessToken());
			ResponseEntity<NaverLoginUserResult> response = 
					restTemplate.exchange(profileURL, 
					HttpMethod.GET, new HttpEntity(header), NaverLoginUserResult.class);
			NaverLoginUserInfo userInfo = response.getBody().getResponse();
			System.out.println(userInfo);
			
			int userId = userService.isRegistered(userInfo.getEmail());
			Users user;
			
			if(userId !=0) {
				user = userService.get(userId);
			}else {
				user = userService.addNaverUser(userInfo);
			}
			
			session.setAttribute("user",user);
			
		}
		
		return "redirect:/";
	}


}
