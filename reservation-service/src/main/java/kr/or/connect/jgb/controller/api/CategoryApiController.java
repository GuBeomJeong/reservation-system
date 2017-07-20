package kr.or.connect.jgb.controller.api;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.jgb.domain.Category;
import kr.or.connect.jgb.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> list(HttpServletRequest req,HttpServletResponse res) {
		return categoryService.getAll();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable int id, @RequestBody Category category) {
		category.setId(id);
		categoryService.update(category);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		categoryService.delete(id);
		
	}
	
}