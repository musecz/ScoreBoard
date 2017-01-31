package io.tailorweb.scoreboard.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import io.tailorweb.scoreboard.R;
import io.tailorweb.scoreboard.adapters.DataAdapter;
import io.tailorweb.scoreboard.model.Data;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_FILE = "io.tailorweb.scoreboard.preferences";
    private static final String KEY_STROKECOUNT = "KEY_STROKECOUNT";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Data[] mDatas = new Data[18];
    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mEditor = mSharedPreferences.edit();
        // Initialize Holes
        int score = 0;
        for (int i = 0; i < mDatas.length; i++) {
            score = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            mDatas[i] = new Data("Hole " + (i + 1) + " :", score);
        }
        mDataAdapter= new DataAdapter(this, mDatas);
        mRecyclerView.setAdapter(mDataAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < mDatas.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT + i, mDatas[i].getCount());
        }
        mEditor.apply();
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
        if (id == R.id.reset_score) {
            mEditor.clear();
            mEditor.apply();
            for (Data data : mDatas) {
                data.setCount(0);
            }
            mDataAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
