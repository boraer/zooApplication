package org.zoo.logging;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class CommonLogging {

	private static Logger logger = LoggerFactory.getLogger("Zoo Logger");

	@Around(value = "BusinessLayerExecution()")
	public Object auditAround(ProceedingJoinPoint joinPoint) {
		Date date = new Date();
		String methodName = joinPoint.getSignature().getName();

		Object obj = null;
		joinPoint.getArgs();
		// logger.info("Method {} with input params : {} Date : {}",
		// methodName,Arrays.toString(joinPoint.getArgs()), date.toString());
		try {
			obj =  joinPoint.proceed();
			Date date2 = new Date();
			
			logger.info("Method {} return with input params : {} StartDate : {}  EndDate : {} with outparams : {} ", methodName,
					Arrays.toString(joinPoint.getArgs()), date.toString(),date2.toString(), obj.toString());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Date date2 = new Date();
			logger.error("Method {} with input params : {} StartDate : {} EndDate : {} Error : {} ", methodName,
					Arrays.toString(joinPoint.getArgs()), date.toString(),date2.toString(), e.getMessage());
		}
		return obj;
	}
	
	@Pointcut("within(org.zoo.businessservice..*)")
	public void BusinessLayerExecution() {
	}
	
}
