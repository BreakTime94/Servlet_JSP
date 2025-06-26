package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import mapper.BoardMapper;
import util.MybatisUtil;

@Slf4j
public class BoardService {

	public List<Board> list(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			
			List<Board> list = mapper.list(cri);
			return list; //현재 지정값 page 1 amount 10
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Board findBy(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.increaseCnt(bno);
			Board board = mapper.selectOne(bno);
			
			return board;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void increaseCnt(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.increaseCnt(bno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try{
			
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.insert(board);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			board.getAttachs().forEach(a -> {
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
			session.commit();
		}
		catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public long getCount(Criteria cri) {
		try(SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void modify(Board board) {
		SqlSession session = MybatisUtil.getSqlSession(false);
		try{
			
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.update(board);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			//기존 첨부파일 메타데이터 제거
			attachMapper.deleteByBno(board.getBno());
			
			//새로 첨부파일 메타데이터 등록
			board.getAttachs().forEach(a -> {
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
			session.commit();
		}
		catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public void remove(Long bno) {
		try(SqlSession session = MybatisUtil.getSqlSession()){	
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.delete(bno);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		new BoardService().list(new Criteria()).forEach(b -> log.info("{}" , b.getTitle()));
	}
	
}
