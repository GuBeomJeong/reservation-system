package kr.or.connect.jgb.controller.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.jgb.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileApiController {
	@Autowired
	FileService fileService;
	
	@GetMapping("/products/{productId}")
	public Collection<Integer> files(@PathVariable int productId){
		return fileService.getByProduct(productId);
	}
}
