package com.codepath.simpletodo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Todo;

/**
 * Created by carise on 8/14/16.
 */
public class TodoCursorRecyclerViewAdapter extends CursorRecyclerViewAdapter<TodoCursorRecyclerViewAdapter.ViewHolder> {

    public TodoCursorRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.todo_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(TodoCursorRecyclerViewAdapter.ViewHolder viewHolder, Cursor cursor) {
        Todo todoItem = Todo.fromCursor(cursor);
        Log.d("TCRV", todoItem.content);
        viewHolder.mTextView.setText(todoItem.content);
    }
}
