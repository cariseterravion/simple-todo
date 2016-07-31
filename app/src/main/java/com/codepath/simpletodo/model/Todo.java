package com.codepath.simpletodo.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

@Table(name="Todo")
public class Todo extends Model {
    @Column(name="todo_id", unique=true)
    public Integer todoId;

    @Column(name="content")
    public String content;

    @Column(name="due_date")
    public Timestamp dueDate;

    @Column(name="create_date")
    public Timestamp createDate;

    @Column(name="remind_time")
    public Timestamp remindTime;

    @Column(name="user_id")
    public Integer userId;

    public Todo() {
        super();
    }
}
