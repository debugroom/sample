package org.debugroom.sample.common.exception;

/**
 * 
 * @author org.debugroom
 *
 */
public class BusinessException extends Exception{

    private static final long serialVersionUID = 1L;

    private String code;
    
    public BusinessException(){
        super();
    }
    
    public BusinessException(String code, String message){
        super(message);
        this.code = code;
    }

    public BusinessException(String code, Throwable cause){
        super(cause);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
