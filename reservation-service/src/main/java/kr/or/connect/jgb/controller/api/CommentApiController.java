package kr.or.connect.jgb.controller.api;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.jgb.domain.vo.CommentVO;
import kr.or.connect.jgb.service.CommentService;
import kr.or.connect.jgb.service.FileService;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {
	@Autowired
	CommentService commentService;
	
	@GetMapping("/products/{productId}")
	public List<CommentVO> comments(@PathVariable int productId){
		return commentService.getThreeByProduct(productId);
	}
}