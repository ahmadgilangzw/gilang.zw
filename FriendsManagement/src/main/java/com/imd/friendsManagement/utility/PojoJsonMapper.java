package com.imd.friendsManagement.utility;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class PojoJsonMapper {
	private final static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setDateFormat(new SimpleDateFormat("dd MM yyyy HH:mm:ss"));
        mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL));
    }

    public static <T> String toJson(T pojo) {
        String result;
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Constants.ERROR_CODE.RC003);
        }

        return result;
    }
    
    public static <T> String toJsonNotPretty(T pojo) {
        String result;
        try {
            result = mapper.writeValueAsString(pojo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(Constants.ERROR_CODE.RC003);
        }

        return result;
    }

}
