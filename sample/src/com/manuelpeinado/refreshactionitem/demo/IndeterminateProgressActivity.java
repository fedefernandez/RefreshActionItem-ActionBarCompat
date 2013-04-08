package com.manuelpeinado.refreshactionitem.demo;

import java.util.Random;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.manuelpeinado.refreshactionitem.demo.R;
import com.manuelpeinado.refreshactionitem.RefreshActionItem;
import com.manuelpeinado.refreshactionitem.RefreshActionItem.RefreshActionListener;

public class IndeterminateProgressActivity extends SherlockListActivity implements RefreshActionListener {
    private RefreshActionItem mRefreshActionItem;
    private Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indeterminate_progress);
    }

    private void loadData() {
        mRefreshActionItem.setDisplayMode(RefreshActionItem.MODE_INDETERMINATE);
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        mRefreshActionItem.setDisplayMode(RefreshActionItem.MODE_BUTTON);
                        String[] items = generateRandomItemList();
                        setListAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, items ));
                    }
                });
            }
            
        }).start();
    }

    private String[] generateRandomItemList() {
        String[] result = new String[100];
        for (int i = 0; i < result.length; ++i) {
            result[i] = Integer.toString(r.nextInt(1000)); 
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.refresh, menu);
        MenuItem item = menu.findItem(R.id.refresh_button);
        mRefreshActionItem = (RefreshActionItem)item.getActionView();
        mRefreshActionItem.setMenuItem(item);
        mRefreshActionItem.setRefreshActionListener(this);
        loadData();
        return true;
    }
    
    @Override
    public void onRefreshButtonClick(RefreshActionItem sender) {
        loadData();
    }
}