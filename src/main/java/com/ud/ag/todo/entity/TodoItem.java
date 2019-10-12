package com.ud.ag.todo.entity;

public class TodoItem {

	private int id;
	private String text;
	private boolean isCompleted;
	private String createdAt;

	public TodoItem(int id, String text, boolean isCompleted, String createdAt) {
		super();
		this.id = id;
		this.text = text;
		this.isCompleted = isCompleted;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", text=" + text + ", isCompleted=" + isCompleted + ", createdAt=" + createdAt
				+ "]";
	}

}
