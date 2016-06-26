package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.activity.EditItemActivity;
import com.codepath.simpletodo.adapter.TodoRecyclerAdapter;
import com.codepath.simpletodo.helper.DataHandler;
import com.codepath.simpletodo.helper.DividerItemDecoration;
import com.codepath.simpletodo.model.Todo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author carise
 *
 * For CodePath
 */
public class MainActivity extends AppCompatActivity implements DataHandler {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Todo> mTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.lvItems);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        readItems();

        mAdapter = new TodoRecyclerAdapter(mTodos, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST), 0);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        addItem(itemText);
        etNewItem.setText("");
    }

    public void onEditItem(int position) {
        Todo todo = mTodos.get(position);
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("todo_item_position", position);
        intent.putExtra("todo_item_text", todo.text);
        startActivityForResult(intent, 0);
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

    @Override
    public void addItem(String itemText) {
        Todo newTodo = new Todo();
        newTodo.text = itemText;
        Log.d(TAG, "new todo: "+itemText);
        mTodos.add(newTodo);
        mAdapter.notifyDataSetChanged();
        writeItems();
    }

    @Override
    public void deleteItem(int position) {
        Log.d(TAG, "delete todo at position "+position);
        mTodos.remove(position);
        mAdapter.notifyDataSetChanged();
        writeItems();
    }

    @Override
    public void editItem(String itemText, int position) {
        Todo t = mTodos.get(position);
        t.text = itemText;
        mAdapter.notifyDataSetChanged();
        writeItems();
    }

    @Override
    public void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        List<String> items;
        try {
            items = new ArrayList<>(FileUtils.readLines(todoFile));
        } catch (IOException ioe) {
            items = new ArrayList<>();
        }
        mTodos = Todo.fromText(items);
    }

    @Override
    public void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, Todo.toText(mTodos));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}