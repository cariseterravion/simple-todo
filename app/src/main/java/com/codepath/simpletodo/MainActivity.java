package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.adapter.TodoRecyclerAdapter;
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
public class MainActivity extends AppCompatActivity {
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

        mAdapter = new TodoRecyclerAdapter(mTodos);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        Todo newTodo = new Todo();
        newTodo.text = itemText;
        Log.d(TAG, "new todo: "+itemText);
        mTodos.add(newTodo);
        mAdapter.notifyDataSetChanged();
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
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

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, Todo.toText(mTodos));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}