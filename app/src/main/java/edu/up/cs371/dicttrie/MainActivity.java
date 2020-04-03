package edu.up.cs371.dicttrie;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~My Stuff~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 //       String[] wordBank = {"alpha", "all", "allowance", "zygote", "allowed","alpine"};

        DictionaryTrie dictTrie = new DictionaryTrie();
        dictTrie.initializeTop();
        dictTrie.readWordsFromFile(this);
        dictTrie.initializeEnglishDictionaryTrie();

//        for (int i = 0; i<wordBank.length; i++ ) {
//            dictTrie.addWord(wordBank[i]);
//        }

//        for (int i = 0; i < dictTrie.top.size(); i++) {
//            dictTrie.printSubTries(dictTrie.top.get(i));
//        }
        for (int i = 0; i < dictTrie.top.size(); i++) {
            dictTrie.printWordsInTrie(dictTrie.top.get(i));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
