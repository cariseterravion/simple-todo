package com.codepath.simpletodo.model;

import android.database.Cursor;
import android.util.Log;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

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

    public Todo(int todoId, String content, Timestamp dueDate, Timestamp remindTime, int userId) {
        this.todoId = todoId;
        this.content = content;
        this.dueDate = dueDate;
        this.remindTime = remindTime;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringBuilder("Todo={")
                .append(" todoId =>").append(todoId).append(", ")
                .append(" content => ").append(content).append(" ,")
                .append("}")
                .toString();
    }

    public static Cursor fetchResultCursor() {
        String tableName = Cache.getTableInfo(Todo.class).getTableName();
        String resultRecords = new Select(tableName + ".*, " + tableName + ".Id as _id").
                from(Todo.class).toSql();
        Cursor resultCursor = Cache.openDatabase().rawQuery(resultRecords, null);
        return resultCursor;
    }

    public static Todo fromCursor(Cursor cursor) {
        Todo todo = new Todo();
        todo.todoId = cursor.getInt(cursor.getColumnIndexOrThrow("todo_id"));
        todo.content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
        Log.d("Todo", todo.toString());
        // TODO: get the other values
        return todo;
    }
}
