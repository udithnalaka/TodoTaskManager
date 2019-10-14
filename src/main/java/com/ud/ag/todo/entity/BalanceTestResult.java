package com.ud.ag.todo.entity;

public class BalanceTestResult {
	
	private String input;
	
	private boolean balanced;

	public BalanceTestResult(String input, boolean balanced) {
		super();
		this.input = input;
		this.balanced = balanced;
	}

	public String getInput() {
		return input;
	}

	public boolean isBalanced() {
		return balanced;
	}

	@Override
	public String toString() {
		return "BalanceTestResult [input=" + input + ", isBalanced=" + balanced + "]";
	}
	
}
