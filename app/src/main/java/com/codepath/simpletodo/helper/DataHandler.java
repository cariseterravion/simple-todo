package com.codepath.simpletodo.helper;

/**
 * Created by carise on 6/26/16.
 */
public interface DataHandler {
    void onEditItem(int position);
    void addItem(String text);
    void deleteItem(int position);
    void editItem(String text, int position);
    void readItems();
    void writeItems();
}
