package kr.or.connect.jgb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.jgb.dao.CommentDao;
import kr.or.connect.jgb.dao.FileDao;
import kr.or.connect.jgb.domain.vo.CommentVO;
import kr.or.connect.jgb.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	FileDao fileDao;

	@Override
	public List<CommentVO> getThreeByProduct(int productId) {
		// TODO Auto-generated method stub
		List<CommentVO> commentsVO = commentDao.selectThreeByProduct(productId);
		for(CommentVO commentVO : commentsVO) {
			commentVO.setFilesId(fileDao.selectByReservationUserComment(commentVO.getId()));
		}
		return commentsVO;
	}

}
