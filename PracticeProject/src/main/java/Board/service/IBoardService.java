package Board.service;

import java.util.List;
import java.util.Map;

import Board.vo.BoardCmtVO;
import Board.vo.BoardVO;
import Board.vo.PagingVO;

public interface IBoardService {
	/**
	 * 게시판 전체 리스트 조회
	 * @return List<BoardVO>
	 */
	public List<BoardVO> GetBoardAllList();
	
	/**
	 * 게시글 작성
	 * @return 1 or 0
	 */
	public int AddBoard(BoardVO bvo);
	
	/**
	 * 게시글 디테일 
	 * @return BoardVO
	 */
	public BoardVO GetBoardDetail(int b_no);
	
	/**
	 * 게시글 삭제
	 * @return 1 or 0
	 */
	public int DeleteBoard(int b_no);
	
	/**
	 * 게시글 수정
	 * @return 1 or 0
	 */
	public int UpdateBoard(BoardVO bvo);
	
	/**
	 * 댓글 전체 리스트
	 * @return
	 */
	public List<BoardCmtVO> GetCommentAllList(int b_no);
	
	/**
	 * 댓글 입력
	 * @return
	 */
	public int AddComment(BoardCmtVO bcvo);
	
	/**
	 * 선택한 댓글 불러오기
	 * @param bc_no
	 * @return
	 */
	public BoardCmtVO getOneComment(int bc_no);

	/**
	 * 댓글 수정하기
	 * @param bcvo
	 * @return
	 */
	public int UpdateComment(BoardCmtVO bcvo);
	
	/**
	 * 댓글 삭제하기
	 * @param bcno
	 * @return
	 */
	public int DeleteComment(int bcno);
	
	/**
	 * 페이징
	 * @param start
	 * @param end
	 * @return List<BoardVO>
	 */
	public List<BoardVO> pagingList(Map<String, Integer> pMap);
	
	/**
	 * 현재 게시글 수
	 * @return int
	 */
	public int getBoardCnt();
}
