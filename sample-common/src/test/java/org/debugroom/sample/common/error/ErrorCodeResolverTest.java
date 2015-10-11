package org.debugroom.sample.common.error;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.categories.Category;

import org.debugroom.sample.common.exception.BusinessException;
import org.debugroom.sample.common.exception.SystemException;
import org.debugroom.sample.common.test.junit.category.UnitTests;



/**
 * 
 * @author org.debugroom
 *
 */
@RunWith(Enclosed.class)
public class ErrorCodeResolverTest {

    @Category(UnitTests.class)
    @RunWith(Enclosed.class)
    public static class ErrorCodeResolverUnitTest{
        
        @RunWith(Theories.class)
        public static class WhiteBoxTest{
            
            @DataPoints
            public static GetErrorCodeFixture[] getErrorCodeFixtures = {
                new GetErrorCodeFixture()
                    .exception(new BusinessException())
                    .expected("sample.common.error.0001"),
                new GetErrorCodeFixture()
                    .exception(new SystemException())
                    .expected("sample.common.error.0002"),
                new GetErrorCodeFixture()
                    .exception(new Exception())
                    .expected("sample.common.error.0000"),
                new GetErrorCodeFixture()
                    .exception(null)
                    .expected(null),
            };
            
            @Theory
            public void testCase1(GetErrorCodeFixture getErrorCodeFixture)
                throws Exception{
                
                ErrorCodeResolver errorCodeFactory = ErrorCodeResolver.INSTANCE;

                //Verify
                assertThat(getErrorCodeFixture.toString(),
                        errorCodeFactory.getErrorCode(getErrorCodeFixture.exception), 
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
        
        @RunWith(Theories.class)
        public static class BlackBoxTest{
            
            @DataPoints
            public static NormalTestCaseFixture[] normalTestCaseFixtures = {
                new NormalTestCaseFixture()
                        .exception(new BusinessException())
                        .expected("sample.common.error.0001"),
                new NormalTestCaseFixture()
                        .exception(new SystemException())
                        .expected("sample.common.error.0002"),
            };
            
            @DataPoints
            public static AbnormalTestCaseFixture[] abnormalTestCaseFixtures = {
                new AbnormalTestCaseFixture()
                        .exception(null)
                        .expected(null),
            };

            @Theory
            public void testCase1(NormalTestCaseFixture normalTestCaseFixture)
               throws Exception{
                ErrorCodeResolver errorCodeFactory = ErrorCodeResolver.INSTANCE;

                //Verify
                assertThat(normalTestCaseFixture.toString(),
                        errorCodeFactory.getErrorCode(normalTestCaseFixture.exception), 
                        is(normalTestCaseFixture.expected));
            }

            @Theory
            public void testCase2(AbnormalTestCaseFixture abnormalTestCaseFixture)
                throws Exception{

                ErrorCodeResolver errorCodeFactory = ErrorCodeResolver.INSTANCE;
                
                //Verify
                assertThat(abnormalTestCaseFixture.toString(),
                        errorCodeFactory.getErrorCode(abnormalTestCaseFixture.exception),
                        is(abnormalTestCaseFixture.expected));
                
            }

            public static class NormalTestCaseFixture{
                Exception exception; 
                String expected;
                
                NormalTestCaseFixture(){}
                NormalTestCaseFixture(Exception exception, String expected){
                    this.exception = exception;
                    this.expected = expected;
                }
                
                public NormalTestCaseFixture exception(Exception exception){
                    this.exception = exception;
                    return this;
                }
                
                public NormalTestCaseFixture expected(String expected){
                    this.expected = expected;
                    return this;
                }

                @Override
                public String toString(){
                    return String.format("when exception = %s, expceted = %s ", exception, expected);
                }            
            }
                
            public static class AbnormalTestCaseFixture{
                Exception exception; 
                String expected;
                    
                AbnormalTestCaseFixture(){}
                AbnormalTestCaseFixture(Exception exception, String expected){
                    this.exception = exception;
                    this.expected = expected;
                }
                public AbnormalTestCaseFixture exception(Exception exception){
                    this.exception = exception;
                    return this;
                }
                public AbnormalTestCaseFixture expected(String expected){
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
