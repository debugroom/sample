package org.debugroom.sample.spring.common.error;

import org.springframework.dao.DataAccessException;

import org.debugroom.sample.common.error.ErrorCodeResolver;
import org.debugroom.sample.common.exception.ExceptionResolver;
/**
 * 
 * @author org.debugroom
 *
 */
public enum DataAccessErrorCodeResolver implements ExceptionResolver{

    INSTANCE;
    
    private ExceptionResolver exceptionResolver = ErrorCodeResolver.INSTANCE;

    public String getErrorCode(Exception exception){
        String errorCd = null;
        if(null != exception){
            String exceptionName = exception.getClass().getName();
            if(exception instanceof DataAccessException){
                switch(exceptionName){
                    case "org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException":
                        errorCd = "sample.spring.common.error.0001";
                        break;
                    case "org.springframework.dao.CleanupFailureDataAccessException":
                        errorCd = "sample.spring.common.error.0002";
                        break;
                    case "org.springframework.dao.ConcurrencyFailureException":
                        errorCd = "sample.spring.common.error.0003";
                        break;
                    case "org.springframework.dao.DataAccessResourceFailureException":
                        errorCd = "sample.spring.common.error.0004";
                        break;
                    case "org.springframework.dao.DataIntegrityViolationException":
                        errorCd = "sample.spring.common.error.0005";
                        break;
                    case "org.springframework.dao.DataRetrievalFailureException":
                        errorCd = "sample.spring.common.error.0006";
                        break;
                    case "org.springframework.dao.InvalidDataAccessApiUsageException":
                        errorCd = "sample.spring.common.error.0007";
                        break;
                    case "org.springframework.dao.InvalidDataAccessResourceUsageException":
                        errorCd = "sample.spring.common.error.0008";
                        break;
                    case "org.springframework.dao.OptimisticLockingFailureException":
                        errorCd = "sample.spring.common.error.0009";
                        break;
                    case "org.springframework.dao.PermissionDeniedDataAccessException":
                        errorCd = "sample.spring.common.error.0010";
                        break;
                    case "org.springframework.dao.PessimisticLockingFailureException":
                        errorCd = "sample.spring.common.error.0009";
                        break;
                    default :
                        errorCd = exceptionResolver.getErrorCode(exception);
                        break;
                }
            }else{
                errorCd = exceptionResolver.getErrorCode(exception);
            }
        }
        return errorCd;
    }

}
