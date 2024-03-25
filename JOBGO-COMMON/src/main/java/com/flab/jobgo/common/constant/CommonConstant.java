package com.flab.jobgo.common.constant;

public class CommonConstant {

	public static final String USER_REGIST_SUCCESS = "회원가입이 완료됐습니다.";
	public static final String INFO_UPDATE_SUCCESS = "정보 수정이 완료됐습니다.";
	public static final String REVIEW_REGIST_SUCCESS = "리뷰 등록이 완료됐습니다.";
	public static final String NOTICE_REGIST_SUCCESS = "공고 등록이 완료됐습니다.";
	public static final String NOTICE_UPDATE_SUCCESS = "공고 수정이 완료됐습니다.";
	public static final String RESUME_REGIST_SUCCESS = "이력서 등록이 완료됐습니다.";
	public static final String RESUME_UPDATE_SUCCESS = "이력서 수정이 완료됐습니다.";
	public static final String APPLY_REGIST_SUCCESS = "공고 지원이 완료됐습니다.";
	
	
	// 기업 정보 조회시 1차 산업분류 코드 -> 코드값으로 변환하여 반환하기 위한 Enum
	public enum FirstIndustryEnum{
		
		FIRST_INDST_001("001", "서비스업"),
		FIRST_INDST_002("002", "제조/화학"),
		FIRST_INDST_003("003", "의료/제약/복지"),
		FIRST_INDST_004("004", "유통/무역/운송"),
		FIRST_INDST_005("005", "교육업"),
		FIRST_INDST_006("006", "건설업"),
		FIRST_INDST_007("007", "IT/웹/통신"),
		FIRST_INDST_008("008", "미디어/디자인"),
		FIRST_INDST_009("009", "은행/금융업"),
		FIRST_INDST_010("010", "기관/협회");
		
	    private final String code;
	    private final String value;
	    
		private FirstIndustryEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}
		
		public static String getValue(String code) {
			for(FirstIndustryEnum industry : FirstIndustryEnum.values()) {
				if(industry.code == code) {
					return industry.value;
				}
			}
			return null;
		}
	}
	
	// 기업 정보 조회시 2차 산업분류 코드 -> 코드값으로 변환하여 반환하기 위한 Enum
	public enum SecondIndustryEnum{
		
		SECONDS_INDST_001("001", "호텔/여행/항공"),
		SECONDS_INDST_002("002", "외식업/식음료"),
		SECONDS_INDST_003("003", "뷰티/미용"),
		SECONDS_INDST_004("004", "기타 서비스업"),
		SECONDS_INDST_005("005", "전기/전자/제어"),
		SECONDS_INDST_006("006", "석유/화학/에너지"),
		SECONDS_INDST_007("007", "반도체/광학/디스플레이"),
		SECONDS_INDST_008("008", "기계/설비/자동차"),
		SECONDS_INDST_009("009", "조선/항공/우주"),
		SECONDS_INDST_010("001", "기타 제조업"),
		SECONDS_INDST_011("011", "의료"),
		SECONDS_INDST_012("012", "제약/보건/바이오"),
		SECONDS_INDST_013("013", "기타 의료/제약/복지"),
		SECONDS_INDST_014("014", "유통/무역/상사"),
		SECONDS_INDST_015("015", "운송/운수/물류"),
		SECONDS_INDST_016("016", "기타 유통/무역/운송"),
		SECONDS_INDST_017("017", "초중고/대학"),
		SECONDS_INDST_018("018", "유아/유치원"),
		SECONDS_INDST_019("019", "학원/어학원"),
		SECONDS_INDST_020("020", "기타 교육업"),
		SECONDS_INDST_021("021", "건설/건축/토목"),
		SECONDS_INDST_022("022", "실내/인테리어"),
		SECONDS_INDST_023("023", "기타 건설업"),
		SECONDS_INDST_024("024", "포털/인터넷/콘텐츠"),
		SECONDS_INDST_025("025", "네트워크/통신/모바일"),
		SECONDS_INDST_026("026", "하드웨어/장비"),
		SECONDS_INDST_027("027", "솔루션/SI/ERP"),
		SECONDS_INDST_028("028", "게임"),
		SECONDS_INDST_029("029", "기타 IT/웹/통신"),
		SECONDS_INDST_030("030", "신문/잡지/언론사"),
		SECONDS_INDST_031("031", "방송사/케이블"),
		SECONDS_INDST_032("032", "연예/엔터테인먼트"),
		SECONDS_INDST_033("033", "영화/배급/음악"),
		SECONDS_INDST_034("034", "공연/예술/문화"),
		SECONDS_INDST_035("035", "디자인/설계"),
		SECONDS_INDST_036("036", "기타 미디어/디자인"),
		SECONDS_INDST_037("037", "은행/금융/저축"),
		SECONDS_INDST_038("038", "증권/보험/카드"),
		SECONDS_INDST_039("039", "기타 금융업"),
		SECONDS_INDST_040("040", "정부/공공기관/공기업"),
		SECONDS_INDST_041("041", "협회/단체/조사"),
		SECONDS_INDST_042("042", "기타 기관/협회");
		
		
		private final String code;
		private final String value;
		
		private SecondIndustryEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}
		
		public static String getValue(String code) {
			for(SecondIndustryEnum industry : SecondIndustryEnum.values()) {
				if(industry.code == code) {
					return industry.value;
				}
			}
			return null;
		}
	}
	
	// 기업 정보 조회시 기업 형태 코드 -> 코드값으로 변환하여 반환하기 위한 Enum
	public enum EnterpriseTypeEnum{
		
		ENTERPRISE_TYPE_001("001", "중소기업"),
		ENTERPRISE_TYPE_002("002", "중견기업"),
		ENTERPRISE_TYPE_003("003", "대기업"),
		ENTERPRISE_TYPE_004("004", "공기업");
		
		
		private final String code;
		private final String value;
		
		private EnterpriseTypeEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}
		
		public static String getValue(String code) {
			for(EnterpriseTypeEnum type : EnterpriseTypeEnum.values()) {
				if(type.code == code) {
					return type.value;
				}
			}
			return null;
		}
		
		
	}
}
