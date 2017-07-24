package kr.or.connect.jgb.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.jgb.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	ProductController(ProductService productService){
		this.productService = productService;
	}
	
	@GetMapping("/{id:[\\d]+}")
	public String detail(Model model,@PathVariable int id) {
		return "detail";
	}
	
	
	@GetMapping("/{id:[\\d]+}/reserve")
	public String reserve(HttpServletRequest request) {
		if(request.getSession().getAttribute("user") == null) {
			
			return "redirect:/";
		}else {
			return "reserve";
		}
		
	}
}
