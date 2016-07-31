package com.codepath.simpletodo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.activity.MainActivity;
import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Todo;

import java.util.List;

/**
 * Created by carise on 6/21/16.
 */
public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private static final String TAG = TodoRecyclerAdapter.class.getCanonicalName();

    private List<Todo> mTodos;
    private Context mContext;
    private MainActivity mActivity;

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView mTodoTextView;
        public TodoViewHolder(View v) {
            super(v);
            mTodoTextView = (TextView) v.findViewById(R.id.todo_text);
        }
    }

    public TodoRecyclerAdapter(List<Todo> todos, Context ctx) {
        mTodos = todos;
        mContext = ctx;
        // TODO: fix this stupid hack
        mActivity = (MainActivity) ctx;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_todo_row, parent, false);

        return new TodoViewHolder(v);
    }

    // TODO: refactor this to be in the ViewHolder
    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = mTodos.get(position);
        holder.mTodoTextView.setText(todo.content);
        final int todoPosition = position;

        holder.mTodoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onEditItem(todoPosition);
            }
        });

        holder.mTodoTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mActivity.deleteItem(todoPosition);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }


}