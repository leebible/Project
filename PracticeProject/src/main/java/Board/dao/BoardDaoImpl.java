package Board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import Board.vo.BoardCmtVO;
import Board.vo.BoardVO;
import Board.vo.PagingVO;
import kr.or.ddit.util.MybatisUtil;

public class BoardDaoImpl implements IBoardDao {
	private static BoardDaoImpl dao;
	private BoardDaoImpl() {
	}
	public static BoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
		
	@Override
	public List<BoardVO> GetBoardAllList() {
		List<BoardVO> blist = new ArrayList<BoardVO>();
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			blist = session.selectList("board.GetBoardAllList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return blist;
	}
	@Override
	public int AddBoard(BoardVO bvo) {
		SqlSession session = null;
		int cnt=0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("board.AddBoard",bvo);
			if(cnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	@Override
	public BoardVO GetBoardDetail(int b_no) {
		BoardVO bvo = new BoardVO();
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			bvo = session.selectOne("board.GetBoardDetail",b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return bvo;
	}
	@Override
	public int DeleteBoard(int b_no) {
		SqlSession session = null;
		System.out.println("daoimpl 까진 도찻");
		int cnt=0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("board.DeleteBoard",b_no);
			if(cnt>0) {
				session.commit();
				System.out.println("Daoimpl에서 delete cnt:"+cnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	
	@Override
	public int UpdateBoard(BoardVO bvo) {
		SqlSession session = null;
		int cnt=0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("board.UpdateBoard",bvo);
			if(cnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	@Override
	public List<BoardCmtVO> GetCommentAllList(int b_no) {
		List<BoardCmtVO> bclist = new ArrayList<BoardCmtVO>();
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			bclist = session.selectList("board.GetCommentAllList",b_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return bclist;
	}
	@Override
	public int AddComment(BoardCmtVO bcvo) {
		SqlSession session = null;
		int cnt=0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("AddComment",bcvo);
			if(cnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	@Override
	public BoardCmtVO getOneComment(int bc_no) {
		BoardCmtVO bcvo = new BoardCmtVO();
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			bcvo = session.selectOne("board.getOneComment",bc_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return bcvo;
	}
	@Override
	public int UpdateComment(BoardCmtVO bcvo) {
		SqlSession session = null;
		int cnt=0;
		try {
			System.out.println("업데이트daoimpl"+bcvo);
			session = MybatisUtil.getSqlSession();
			cnt = session.update("board.UpdateComment",bcvo);
			if(cnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	@Override
	public int DeleteComment(int bcno) {
		SqlSession session = null;
		int cnt=0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("board.DeleteComment",bcno);
			if(cnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	
	
	@Override
	public List<BoardVO> pagingList(Map<String, Integer> pMap) {
	    List<BoardVO> pagelist = new ArrayList<>();
	    SqlSession session = null;
	    try {
	        session = MybatisUtil.getSqlSession();
	        pagelist = session.selectList("board.pagingList", pMap);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if(session != null) session.close();
	    }
	    return pagelist;
	}
	
	
	
	
	@Override
	public int getBoardCnt() {
		int cnt = 0;
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.selectOne("board.getBoardCnt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
}
