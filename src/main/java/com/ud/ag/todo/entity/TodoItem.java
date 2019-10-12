package com.ud.ag.todo.entity;

import javax.validation.constraints.Size;

public class TodoItem {

	private int id;

	@Size(min = 1, max = 50)
	private String text;

	private boolean isCompleted;

	private String createdAt;
	
	public TodoItem() {
	}

	public TodoItem(int id, String text, boolean isCompleted, String createdAt) {
		super();
		this.id = id;
		this.text = text;
		this.isCompleted = isCompleted;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", text=" + text + ", isCompleted=" + isCompleted + ", createdAt=" + createdAt
				+ "]";
	}

}
