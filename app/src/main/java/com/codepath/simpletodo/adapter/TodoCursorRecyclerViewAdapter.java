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
    private static final String TAG = TodoCursorRecyclerViewAdapter.class.getCanonicalName();
    private OnItemClickListener mClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(Todo todo) {

        }
    };
    private OnItemLongClickListener mLongClickListener = new OnItemLongClickListener() {
        @Override
        public void onItemLongClick(Todo todo) {
            Log.d(TAG, "todo: " + todo);
            todo.delete();
            changeCursor(Todo.fetchResultCursor());
        }
    };

    public TodoCursorRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Todo todo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.todo_text);
        }

        public void bind(View itemView, final Todo todoItem,
                         final OnItemClickListener onItemClickListener,
                         final OnItemLongClickListener onItemLongClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(todoItem);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(todoItem);
                    return true;
                }
            });
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
        viewHolder.bind(viewHolder.itemView, todoItem, mClickListener, mLongClickListener);
        viewHolder.mTextView.setText(todoItem.content);
    }

}
