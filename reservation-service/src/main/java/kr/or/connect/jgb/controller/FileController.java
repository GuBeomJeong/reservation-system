package kr.or.connect.jgb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.jgb.domain.Files;
import kr.or.connect.jgb.service.CategoryService;
import kr.or.connect.jgb.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/files")
public class FileController {
	
	
	private FileService fileService;
	
	@Autowired
	FileController(FileService fileService){
		this.fileService = fileService;
	}
	

    @GetMapping
    public String fileform(){
        return "files";
    }

    @PostMapping
    public String create(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") int productId){

        fileService.inputFile(title,file,productId);

        return "redirect:/files";
    }

    @GetMapping(path="/{id}")
    public void displayImage(
            @PathVariable(name="id") int id,
            HttpServletResponse response
    ){ 	
    	Files file = fileService.get(id);
    	
        String originalFilename = file.getFileName();
        String contentType = file.getContentType();
        int fileSize = file.getFileLength();
        
        String saveFileName = file.getSaveFileName();

        response.setHeader("Content-Disposition", "inline; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(saveFileName);
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }

    }

}