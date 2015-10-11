package org.debugroom.sample.common.exception;

/**
 * 
 * @author org.debugroom
 *
 */
public interface ExceptionResolver {
    public String getErrorCode(Exception exception);
}
