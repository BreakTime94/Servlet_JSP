package domain;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class Review {
	private Long rno; //Primary Key
	private String content;
	private String regdate;
	private Integer rating;
	private String writer; // Foreign Key
	private Long pno;
	
}
