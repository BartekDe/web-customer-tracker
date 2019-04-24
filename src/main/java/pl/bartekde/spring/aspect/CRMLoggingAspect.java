package pl.bartekde.spring.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* pl.bartekde.spring.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	
	// do the same thing for service and dao
	@Pointcut("execution(* pl.bartekde.spring.service.*.*(..))")
	private void forServicePackage() {
		
	}
	
	@Pointcut("execution(* pl.bartekde.spring.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
		
	}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + theMethod);
		
		// display the args passed to the method
		
		// get the args
		Object[] args = theJoinPoint.getArgs();
		
		// loop through and display them
		for(Object tempArg : args) {
			myLogger.info("=====>> argument: " + tempArg);
		}
	}
	
	// add @AfterReturning advice
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display the method we return from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);
		
		// display returned data
		myLogger.info("=====>> result: " + theResult);
		
	}
}
