package org.debugroom.sample.common.error;

import org.debugroom.sample.common.exception.ExceptionResolver;

/**
 * 
 * @author org.debugroom
 *
 */
public enum ErrorCodeResolver implements ExceptionResolver{
    
    INSTANCE;
    
    public String getErrorCode(Exception exception){
        String errorCd = null;
        if(null != exception){
            String exceptionName = exception.getClass().getName();
            if(null != exceptionName){
                switch(exceptionName){
                    case "org.debugroom.sample.common.exception.BusinessException":
                        errorCd = "sample.common.error.0001";
                        break;
                    case "org.debugroom.sample.common.exception.SystemException":
                        errorCd = "sample.common.error.0002";
                        break;
                    default :
                        errorCd = "sample.common.error.0000";
                        break;
                }   
            }
        }
        return errorCd; 
    }

}
