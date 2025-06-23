package mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class ReplyMapperTest {
	private ReplyMapper replyMapper = MybatisUtil.getSqlSession().getMapper(ReplyMapper.class);
	
	@Test
	@DisplayName("댓글리스트 조회")
	public void replyList() {
		List<Reply> replies = replyMapper.list(132L, 35L);
		replies.forEach(b -> log.info("{}", b.getContent()));
	}
	@Test
	@DisplayName("댓글리스트 조회")
	public void replyListNotNull() {
		List<Reply> replies = replyMapper.list(132L, null);
		replies.forEach(b -> log.info("{}", b.getContent()));
	}
	@Test
	@DisplayName("댓글 단일 조회")
	public void selectOneTest() {
		//given
		Long rno = 5L;
		
		//when
		Reply reply = replyMapper.selectOne(rno);
		
		//then ~ actual, expect
		log.info("{}", reply);
	}
	@Test
	@DisplayName("댓글 등록")
	public void insertTest() {
		Reply reply = Reply.builder().content("매퍼 테스트 등록 댓글").id("sae").bno(132L).build();
		log.info("{}", reply);		
		replyMapper.insert(reply);
		log.info("{}", reply);
	}
	@Test
	@DisplayName("댓글 수정")
	public void updateTest() {
		Long rno = 4L;
		Reply reply = replyMapper.selectOne(rno);
		reply.setContent("수정내용");
		log.info("{}", reply);
		replyMapper.update(reply);
	}
	@Test
	@DisplayName("댓글삭제 테스트")
	public void deleteTest() {
		Long rno = 5L;
		replyMapper.delete(rno);
	}
}
