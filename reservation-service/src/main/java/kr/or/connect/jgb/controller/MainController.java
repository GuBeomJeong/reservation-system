package kr.or.connect.jgb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.jgb.domain.Category;
import kr.or.connect.jgb.service.CategoryService;

@Controller
@RequestMapping("/")
public class MainController {
	
	private CategoryService categoryService;
	
	@Autowired
	MainController(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	@GetMapping("/category")
	public String catergory(Model model) {
		List<Category> categories;
		categories = categoryService.getAll();
		model.addAttribute("categories", categories);
		return "category";
	}
	
	@PostMapping("/category")
    public String create(@RequestParam String name) {
        Category category = new Category(name);
        categoryService.addCategory(category);
        return "redirect:/category";
    } // requestparam에 값이 안들어 오면 에러가남
	
	@GetMapping("/myreservation")
	public String myreservation(Model model) {
		return "myreservation";
	}
		
	@GetMapping
	public String index(Model model) {
		return "mainpage";
	}
	
	
	

}
