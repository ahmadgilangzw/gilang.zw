package com.imd.friendsManagement.utility;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Gilang ZW
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace"})
public class BusinessException extends RuntimeException implements Serializable {
    private String errorCode;
    private String fullMessage;

    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;

        translateMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public void translateMessage(String isoLanguage) {   
    	ResourceBundle bundle;
        try {
        	ResourceBundle.clearCache();
            bundle = ResourceBundle.getBundle("errors", new Locale(isoLanguage));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return;
        }
        
        try {        	
        	this.fullMessage = EngineUtils.convertCharset(bundle.getString(this.errorCode));
        } catch (MissingResourceException e) {
            this.fullMessage = this.errorCode;
        }
    }

    private void translateMessage() {
    	translateMessage("ind");
    }

    @Override
    public String getMessage() {
        return "(" + this.fullMessage.trim() +
                (super.getMessage() == null ? "" : " " + super.getMessage().trim()) + ")" ;
    }
}
