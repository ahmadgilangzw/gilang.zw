package com.imd.friendsManagement.utility;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public final class EngineUtils {

	public static String convertCharset(String text) {
		String result = "";
		try {
			result = new String(text.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getMessageBusinessException(BusinessException e){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put(Constants.UTILS.ERROR, true);
		param.put(Constants.UTILS.CODE_ERROR, e.getErrorCode());
		param.put(Constants.UTILS.DESCRIPTION, e.getMessage());

		return PojoJsonMapper.toJson(param);
	}
	
	public static String getMessageException(Exception e){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put(Constants.UTILS.ERROR, true);
		param.put(Constants.UTILS.DESCRIPTION, Constants.UTILS.SIE);

		return PojoJsonMapper.toJson(param);
	}
}
