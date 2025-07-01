package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MybatisUtil;

@Slf4j
public class GhostFileCleanupJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//파일인스턴스 생성 > 어제 날짜의 업로드 폴더 ?
		File file = new File(UploadFile.UPLOAD_PATH, genYesterdayPath());
		log.info("{} {}", file, file.exists());
		
		if(!file.exists() || !file.isDirectory()) {
			return;
		}

		List<File> fsListFiles = new ArrayList<>(Arrays.asList(file.listFiles())); // 변화가 있어야 함
		SqlSession session = MybatisUtil.getSqlSession();
		//현재 이슈
		//dbListFiles에는 thumbnail 파일에 대한 추가가 되지 않음
		
		//이미지 파일 2개 일반 파일 1개로 구성된 총 3개의 attach라면
		
		//이미지 파일 2개 + thumbnail 2개 + 일반 파일 1개로 구성된 총 5개의 attachlist로 변경되어야함.
		
		
		List<Attach> attachs = new ArrayList<>(session.getMapper(AttachMapper.class).selectYesterdayList());
		log.info("==============썸네일 없는 파일==============");
		attachs.forEach(a -> log.info("{}", a));
		
		List<Attach> thumbs = new ArrayList<>(attachs).stream().filter(Attach :: isImage).map(Attach :: toThumb).toList();
		log.info("==============썸네일만==============");
		thumbs.forEach(a -> log.info("{}", a));
		
		attachs.addAll(thumbs);
		log.info("==============둘 다 합친거==============");
		attachs.forEach(a -> log.info("{}", a));		
		//체이닝을 통해 한꺼번에 처리하기보단 List<Attach>상태에서 추가작업 후 추후에 List<File>로 변경
		
		List<File> dbListFiles = attachs.stream().map(Attach :: toFile).toList(); // 변화가 있으면 안된다. 빼는 애는
		//근데 썸네일이 같이 지워져버린다. 그러면 안된다능
		log.info("==============삭제대상 파일==============");
		dbListFiles.forEach(a -> log.info("{}", a));		
	
		session.close();
		
		fsListFiles.removeAll(dbListFiles);
	
		log.info("==============삭제후 남은 파일==============");
		fsListFiles.forEach(a -> log.info("{}", a));		
		
		fsListFiles.forEach(f -> f.delete());

		log.info("==============fslist==============");
		fsListFiles.forEach(a -> log.info("{}", a));		
		
	}
	private String genYesterdayPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
	
	
	public static void main(String[] args) throws Exception{
		new GhostFileCleanupJob().execute(null);
	}
}
