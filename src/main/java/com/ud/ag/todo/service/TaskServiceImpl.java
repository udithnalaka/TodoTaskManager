package com.ud.ag.todo.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ud.ag.todo.entity.BalanceTestResult;

@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BalanceTestResult checkBracketsBalanced(String input) {

		try {
			return new BalanceTestResult(input, isBracketsBalanced(URLDecoder.decode(input, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("checkBracketsBalanced() - " + e);
			return null;
		}

	}

	/**
	 * Brackets in a string are considered to be balanced if the following criteria
	 * are met: - For every opening bracket, there is a matching closing bracket. -
	 * An opening bracket must appear before (to the left of) its matching closing
	 * bracket. - No unmatched braces lie between some pair of matched bracket.
	 * 
	 * @param input
	 * 
	 * @return boolean
	 */
	private boolean isBracketsBalanced(String input) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++) {
			char inputChar = input.charAt(i);
			if (inputChar == '[' || inputChar == '(' || inputChar == '{') {
				stack.push(inputChar);
			} else if (inputChar == ']') {
				if (stack.isEmpty() || stack.pop() != '[') {
					return false;
				}
			} else if (inputChar == ')') {
				if (stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			} else if (inputChar == '}') {
				if (stack.isEmpty() || stack.pop() != '{') {
					return false;
				}
			}

		}

		return stack.isEmpty();
	}

}
