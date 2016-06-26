package com.codepath.simpletodo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.R;

// TODO: use fragments instead of creating this new activity
public class EditItemActivity extends AppCompatActivity {
    private int position;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        position = intent.getIntExtra("todo_item_position", -1);
        text = intent.getStringExtra("todo_item_text");
        if (text == null) {
            text = "";
        }
        EditText editTextView = (EditText) findViewById(R.id.edit_item_text);
        editTextView.setText(text);
        editTextView.setSelection(text.length());
    }

    public void onSaveItem(View v) {
        text = ((EditText) findViewById(R.id.edit_item_text)).getText().toString();
        Intent output = new Intent();
        output.putExtra("todo_item_text", text);
        output.putExtra("todo_item_position", position);
        setResult(RESULT_OK, output);
        finish();
    }
}
