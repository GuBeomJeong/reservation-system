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


import kr.or.connect.jgb.domain.Product;
import kr.or.connect.jgb.domain.vo.ProductDetailVO;
import kr.or.connect.jgb.domain.vo.ProductMainVO;
import kr.or.connect.jgb.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/categories/{categoryId}/pages/{lastProductId}")
	public List<ProductMainVO> category(@PathVariable int categoryId,@PathVariable int lastProductId) {
		
		return productService.getAllByCategory(categoryId,lastProductId);
	}
	
	@GetMapping("/pages/{lastProductId}")
	public List<ProductMainVO> list(@PathVariable int lastProductId) {
		
		return productService.getAll(lastProductId);
	}
	
	@GetMapping("/{productId}")
	public ProductDetailVO product(@PathVariable int productId) {
		return productService.getDetail(productId);
	}
	
	
//	@PutMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	void update(@PathVariable int id, @RequestBody Category category) {
//		category.setId(id);
//		categoryService.update(category);
//	}
//	
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable int id) {
//		categoryService.delete(id);
//		
//	}
	
}