package com.ud.ag.todo.service;

import com.ud.ag.todo.entity.BalanceTestResult;

public interface TaskService {

	/**
	 * check if the brackets in the input string is properly balanced.
	 * 
	 * @param input
	 * 
	 * @return {@link BalanceTestResult}
	 */
	public BalanceTestResult checkBracketsBalanced(final String input);

}
