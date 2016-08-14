package com.codepath.simpletodo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.adapter.TodoCursorRecyclerViewAdapter;
import com.codepath.simpletodo.model.Todo;
import com.codepath.simpletodo.ui.DividerItemDecoration;

/**
 * @author carise
 *
 * For CodePath
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private RecyclerView mRecyclerView;
    private TodoCursorRecyclerViewAdapter mAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.lvItems);
        mRecyclerView.setHasFixedSize(true);

        mCursor = Todo.fetchResultCursor();
        Log.d(TAG, "mCursor count: " + mCursor.getCount());
        mAdapter = new TodoCursorRecyclerViewAdapter(this, mCursor);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        addItem(itemText);
        etNewItem.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int position = data.getIntExtra("todo_item_position", -1);
            String text = data.getStringExtra("todo_item_text");

            Log.d(TAG, "position="+position+", text="+text);

            if (text == null || text.trim().length() == 0) {
                if (position > -1) {
                    deleteItem(position);
                }
            } else {
                if (position < 0) {
                    addItem(text);
                } else {
                    editItem(text, position);
                }
            }
        }
    }

    public void addItem(String itemText) {
        Todo newTodo = new Todo();
        newTodo.content = itemText;
        Log.d(TAG, "new todo: "+itemText);
        newTodo.save();
        mAdapter.changeCursor(mCursor);
    }

    public void deleteItem(int position) {
        Log.d(TAG, "delete todo at position "+position);
        //todo.delete();
        mAdapter.notifyDataSetChanged();
    }


    public void onEditItem(int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("todo_item_position", position);
        //intent.putExtra("todo_item_text", todo.content);
        startActivityForResult(intent, 0);
    }

    public void editItem(String itemText, int position) {
        /*
        Todo todo = mTodos.get(position);
        todo.content = itemText;
        todo.save();
        */
        mAdapter.changeCursor(mCursor);
    }

}