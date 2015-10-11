package org.debugroom.sample.common.interceptor;

import org.springframework.util.StopWatch;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MeasureTimeAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String methodName = methodInvocation.getMethod().getName();
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start(methodName);
		System.out.println("[LOG] METHOD :" + methodName + " is calling.");
		
		Object object = methodInvocation.proceed();
		
		stopWatch.stop();
		System.out.println("[LOG] METHOD :" + methodName + " was calling.");
		System.out.println("[LOG] Time :" + stopWatch.getTotalTimeMillis() / 1000  + "[second].");
		return object;
	}

}
