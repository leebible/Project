package Board.service;

import java.util.List;
import java.util.Map;

import Board.dao.BoardDaoImpl;
import Board.vo.BoardCmtVO;
import Board.vo.BoardVO;
import Board.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	private BoardDaoImpl dao;
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	private static BoardServiceImpl service;
	
	public static BoardServiceImpl getInstance() {
		if(service==null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	@Override
	public List<BoardVO> GetBoardAllList() {
		return dao.GetBoardAllList();
	}

	@Override
	public int AddBoard(BoardVO bvo) {
		return dao.AddBoard(bvo);
	}

	@Override
	public BoardVO GetBoardDetail(int b_no) {
		return dao.GetBoardDetail(b_no);
	}

	@Override
	public int DeleteBoard(int b_no) {
		return dao.DeleteBoard(b_no);
	}

	@Override
	public int UpdateBoard(BoardVO bvo) {
		return dao.UpdateBoard(bvo);
	}

	@Override
	public List<BoardCmtVO> GetCommentAllList(int b_no) {
		return dao.GetCommentAllList(b_no);
	}

	@Override
	public int AddComment(BoardCmtVO bcvo) {
		return dao.AddComment(bcvo);
	}

	@Override
	public BoardCmtVO getOneComment(int bc_no) {
		return dao.getOneComment(bc_no);
	}

	@Override
	public int UpdateComment(BoardCmtVO bcvo) {
		return dao.UpdateComment(bcvo);
	}

	@Override
	public int DeleteComment(int bcno) {
		return dao.DeleteComment(bcno);
	}

	@Override
	public List<BoardVO> pagingList(Map<String, Integer> pMap) {
		return dao.pagingList(pMap);
	}

	@Override
	public int getBoardCnt() {
		return dao.getBoardCnt();
	}

}
