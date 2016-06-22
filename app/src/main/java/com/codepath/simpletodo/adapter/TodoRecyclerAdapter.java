package com.codepath.simpletodo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Todo;

import java.util.List;

/**
 * Created by carise on 6/21/16.
 */
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private List<Todo> mTodos;

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView mTodoTextView;
        public TodoViewHolder(View v) {
            super(v);
            mTodoTextView = (TextView) v.findViewById(R.id.todo_text);
        }
    }

    public TodoRecyclerAdapter(List<Todo> todos) {
        mTodos = todos;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_todo_row, parent, false);

        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = mTodos.get(position);
        holder.mTodoTextView.setText(todo.text);
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }
}