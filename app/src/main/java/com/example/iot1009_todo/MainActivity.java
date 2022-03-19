package com.example.iot1009_todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

// implemented my onclick listener interface
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
{
    //declared all my variables
    ListView lv;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> itemsCopy = new ArrayList<>();
    Button addButton;
    ArrayAdapter<String> itemsAdapter;
    EditText entryText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // linked my buttons to their xml counterparts
        addButton = findViewById(R.id.addbutton);
        entryText = findViewById(R.id.plain_text_input);
        lv =  findViewById(R.id.list_view);

        // set my onclicklistener for my add button
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.addbutton)
        {
            // makes sure the value isn't null when you add so that a blank item can't be added
            if (!String.valueOf(entryText.getText()).equals(""))
            {
                // used an arrayAdapter (pain in the a**) to add items from an arrayList as
                // text views as well as a button with it
                itemsAdapter =
                        new ArrayAdapter<>(this, R.layout.list_items_new, R.id.textview, itemsCopy);
                String input = String.valueOf(entryText.getText());
                // had to use 2 arrayLists because the clear function clears the arrayList
                items.add(input);
                itemsCopy.add(input);
                lv.setAdapter(itemsAdapter);
                entryText.setText(null);
            }
        }
    }
    // remove item method to be used to remove through a xaml onclick (thanks christian)
         public void removeItem(View view)
    {
        View parent = (View) view.getParent();
        TextView txtTask = parent.findViewById(R.id.textview);
        String task = String.valueOf(txtTask.getText());
        items.remove(task);
        itemsAdapter.clear();
        itemsAdapter.addAll(items);
    }
}