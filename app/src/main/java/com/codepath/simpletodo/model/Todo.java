package com.codepath.simpletodo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carise on 6/21/16.
 */
public class Todo {
    public String text;
    public Timestamp dueDate;
    public Timestamp remindTime;

    /**
     * For now, working only with text so have this method
     * @param todoTexts
     * @return
     */
    public static List<Todo> fromText(List<String> todoTexts) {
        List<Todo> todos = new ArrayList<>();
        if (todoTexts != null) {
            for (String text : todoTexts) {
                Todo todo = new Todo();
                todo.text = text;
                todos.add(todo);
            }
        }
        return todos;
    }

    public static List<String> toText(List<Todo> todos) {
        List<String> texts = new ArrayList<>();
        if (todos != null) {
            for (Todo todo : todos) {
                texts.add(todo.text);
            }
        }
        return texts;
    }
}
