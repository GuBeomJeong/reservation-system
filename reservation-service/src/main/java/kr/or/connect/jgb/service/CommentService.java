package kr.or.connect.jgb.service;

import java.util.List;

import kr.or.connect.jgb.domain.vo.CommentVO;

public interface CommentService {
	public List<CommentVO> getThreeByProduct(int productId);

}
