package org.debugroom.sample.spring.common.error;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;

import org.debugroom.sample.common.exception.BusinessException;

/**
 * 
 * @author org.debugroom
 *
 */
@RunWith(Enclosed.class)
public class DataAccessErrorCodeResolverTest {
    
    @RunWith(Enclosed.class)
    public static class DataAccessErrorCodeResolverUnitTest{
        
        @RunWith(Theories.class)
        public static class WhiteBoxTest{
            
            @DataPoints
            public static GetErrorCodeFixture[] getErrorCodeFixtures = {
                new GetErrorCodeFixture()
                        .exception(new DataSourceLookupFailureException("No foud datasource."))
                        .expected("sample.spring.common.error.0001"),
                new GetErrorCodeFixture()
                        .exception(new CleanupFailureDataAccessException("Error with clean up.", null))
                        .expected("sample.spring.common.error.0002"),
                new GetErrorCodeFixture()
                        .exception(new ConcurrencyFailureException("Error with rocking."))
                        .expected("sample.spring.common.error.0003"),
                new GetErrorCodeFixture()
                        .exception(new DataAccessResourceFailureException("No getting resource for DB access."))
                        .expected("sample.spring.common.error.0004"),
                new GetErrorCodeFixture()
                        .exception(new DataIntegrityViolationException("Error with database update."))
                        .expected("sample.spring.common.error.0005"),
                new GetErrorCodeFixture()
                        .exception(new DataRetrievalFailureException("No result with retrieving."))
                        .expected("sample.spring.common.error.0006"),
                new GetErrorCodeFixture()
                        .exception(new InvalidDataAccessApiUsageException("Mistakes with api usage."))
                        .expected("sample.spring.common.error.0007"),
                new GetErrorCodeFixture()
                        .exception(new InvalidDataAccessResourceUsageException("Mistakes with database resource usage."))
                        .expected("sample.spring.common.error.0008"),
                new GetErrorCodeFixture()
                        .exception(new OptimisticLockingFailureException("OptimisticLock error.")) 
                        .expected("sample.spring.common.error.0009"),
                new GetErrorCodeFixture()
                        .exception(new PermissionDeniedDataAccessException("No accessable data.", null))
                        .expected("sample.spring.common.error.0010"),
                new GetErrorCodeFixture()
                        .exception(new PessimisticLockingFailureException("PessimisticLock error.")) 
                        .expected("sample.spring.common.error.0009"),
                new GetErrorCodeFixture()
                        .exception(new BusinessException())
                        .expected("sample.common.error.0001"),
                new GetErrorCodeFixture()
                        .exception(null)
                        .expected(null),
            };

            @Theory
            public void testCase1(GetErrorCodeFixture getErrorCodeFixture)
                throws Exception{
                
                DataAccessErrorCodeResolver dataAccessErrorCodeResolver
                    = DataAccessErrorCodeResolver.INSTANCE;
                
                assertThat(getErrorCodeFixture.toString(),
                        dataAccessErrorCodeResolver.getErrorCode(getErrorCodeFixture.exception),
                        is(getErrorCodeFixture.expected));
                
            }

            public static class GetErrorCodeFixture{
                Exception exception; 
                String expected;
                GetErrorCodeFixture(){};
                GetErrorCodeFixture(Exception exception, String expected){
                    this.exception = exception;
                    this.expected = expected;
                }
                public GetErrorCodeFixture exception(Exception exception){
                    this.exception = exception;
                    return this;
                }
                public GetErrorCodeFixture expected(String expected){
                    this.expected = expected;
                    return this;
                }
                @Override
                public String toString(){
                    return String.format("when exception = %s, expceted = %s ", exception, expected);
                
                }
            }
        }
    }
}
