package com.manuelpeinado.refreshactionitem.demo;

import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class SampleListFragment extends ListFragment {

    public void setItems(String[] items) {
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, items));
    }

}
