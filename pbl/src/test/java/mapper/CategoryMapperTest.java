package mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Category;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class CategoryMapperTest {
	private CategoryMapper categoryMapper = MybatisUtil.getSqlSession().getMapper(CategoryMapper.class);
	
	@Test
	@DisplayName("목록조회")
	public void testList() {
		List<Category> list = categoryMapper.list();
		list.forEach(b -> log.info("{}", b));
	}
}
