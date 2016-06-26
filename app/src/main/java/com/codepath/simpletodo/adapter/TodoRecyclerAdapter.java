package com.codepath.simpletodo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.helper.DataHandler;
import com.codepath.simpletodo.model.Todo;

import java.util.List;

/**
 * Created by carise on 6/21/16.
 */
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private List<Todo> mTodos;

    private DataHandler mDataHandler;

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView mTodoTextView;
        public TodoViewHolder(View v) {
            super(v);
            mTodoTextView = (TextView) v.findViewById(R.id.todo_text);
        }
    }

    public TodoRecyclerAdapter(List<Todo> todos, DataHandler dataHandler) {
        mTodos = todos;
        mDataHandler = dataHandler;
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
        final int todoPosition = position;

        holder.mTodoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataHandler.onEditItem(todoPosition);
            }
        });

        holder.mTodoTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDataHandler.deleteItem(todoPosition);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }
}