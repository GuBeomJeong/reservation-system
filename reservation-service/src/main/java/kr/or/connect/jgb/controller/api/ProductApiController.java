package kr.or.connect.jgb.controller.api;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import kr.or.connect.jgb.domain.vo.CommentVO;
import kr.or.connect.jgb.domain.vo.ProductDetailVO;
import kr.or.connect.jgb.domain.vo.ProductMainVO;
import kr.or.connect.jgb.domain.vo.ProductReserveVO;
import kr.or.connect.jgb.service.CommentService;
import kr.or.connect.jgb.service.FileService;
import kr.or.connect.jgb.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	
	private ProductService productService;
	private CommentService commentService;
	private FileService fileService;
	
	@Autowired
	ProductApiController(ProductService productService,CommentService commentService,FileService fileService){
		this.productService = productService;
		this.commentService = commentService;
		this.fileService = fileService;
	}
	
	@GetMapping
	public List<ProductMainVO> getProductsFromLastId(@RequestParam int lastProductId) {		
		return productService.getAll(lastProductId);
	}
	
	@GetMapping("/{productId:[\\d]+}")
	public ProductDetailVO getProduct(@PathVariable int productId) {
		return productService.getDetail(productId);
	}
	
	@GetMapping("/{productId:[\\d]+}/comments")
	public List<CommentVO> getThreeComments(@PathVariable int productId){
		return commentService.getThreeByProduct(productId);
	}
	
	@GetMapping("/{productId:[\\d]+}/files")
	public List<Integer> files(@PathVariable int productId){
		return fileService.getByProduct(productId);
	}
	
	@GetMapping("/{productId:[\\d]+}/reserve")
	public ProductReserveVO getReserve(@PathVariable int productId) {
		return productService.getReserve(productId);
	}

	
}