package apidto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripInfo {
	String POST_SN;
	String LANG_CODE_ID;
	String POST_SJ;
	String ADDRESS;
	String NEW_ADDRESS;
	String CMMN_TELNO;
	String CMMN_USE_TIME;
	String SUBWAY_INFO;
}
