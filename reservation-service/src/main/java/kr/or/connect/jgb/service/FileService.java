package kr.or.connect.jgb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.jgb.domain.Files;

public interface FileService {
	public Files get(int id);
    public int delete(int id);
    public int update(Files files);
    public void inputFile(String title,MultipartFile file,int productId);
    public Files addFile(Files file,int productId);
    public List<Integer> getByProduct(int productId);
}
