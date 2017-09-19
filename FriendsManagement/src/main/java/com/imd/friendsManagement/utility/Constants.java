package com.imd.friendsManagement.utility;

/**
 * 
 * @author Gilang ZW
 *
 */
public class Constants {

	public static final String HEADER_PATH = "/service";

	public static final class SUB_PATH {
		// People
		public static final String REGISTER_PEOPLE = "/add-people";

		// Friend
		public static final String ADD_FRIEND = "/add-friend";
		public static final String FIND_ALL_FRIEND = "/find-all-friend";
		public static final String FIND_F_BETWEEN_TWO_EMAIL = "/find-f-between-two-email";

		// Subcribe
		public static final String ADD_SUBSCRIBE = "/add-subscribe";
		public static final String BLOCK_PEOPLE = "/block-people";
		
		// SendReceivedEmail
		public static final String SEND_EMAIL = "/send-email";
		
	}

	public static final class STATUS {
		public static final String FRIEND = "F";
		public static final String BLOKED = "B";
		public static final String SUBSCRIBE = "S";
		public static final String UNSUBSCRIBE = "U";
	}

	public static final class UTILS {
		public static final String ERROR = "Error";
		public static final String CODE_ERROR = "Error Code";
		public static final String SUCCESS = "Success";
		public static final String DESCRIPTION = "Description";
		public static final String SIE = "System Internal Error";
	}

	public static final class ERROR_CODE {
		public static final String RC001 = "RC-001";
		public static final String RC002 = "RC-002";
		public static final String RC003 = "RC-003";

		public static final class PEOPLE {
			public static final String RCP001 = "RCP-001";
		}
		
		public static final class FRIEND {
			public static final String RCF001 = "RCF-001";
			public static final String RCF002 = "RCF-002";
			public static final String RCF003 = "RCF-003";
			public static final String RCF004 = "RCF-004";
			public static final String RCF005 = "RCF-005";
		}

		public static final class SUBCRIBE {
			public static final String RCS001 = "RCS-001";
			public static final String RCS002 = "RCS-002";
			public static final String RCS003 = "RCS-003";
		}
	}
}
