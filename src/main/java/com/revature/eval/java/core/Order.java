package com.revature.eval.java.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This custom annotation was made to simulate JUnit 5's @Order annotation and 
 * allows the developers control of execution of these tests cases in EvaluationService.
 * 
 * This is then controlled by the OrdererRunner to manage test case execution by order number.
 * @author Azhya Knox
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Order {
	public int order();
}
