package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attach {
	private String uuid; //unjversal unique id
	private String path;
	private boolean image; 
	private String origin;
	private Long bno;
	private Long rno;
}
