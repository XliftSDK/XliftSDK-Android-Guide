package jp.xlift.android.sdk.example;

import android.databinding.ObservableArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import jp.xlift.android.sdk.infeed.XliftInfeedAd;
import jp.xlift.android.sdk.infeed.XliftInfeedAdLoader;

public class ListViewActivity extends AppCompatActivity {
    ObservableArrayList<XliftInfeedAd> observableArrayList = new ObservableArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new XliftInfeedAdArrayAdapter(ListViewActivity.this, observableArrayList));

        // mdediaIdは、管理画面でメディア登録を行った際に発行されたIDをお使い下さい
        XliftInfeedAdLoader xliftInfeedAdLoader = new XliftInfeedAdLoader(123456789, true);
        xliftInfeedAdLoader.loadInfeedAd(new XliftInfeedAdLoader.Callback() {
            @Override
            public void onSuccess(final List<XliftInfeedAd> infeedAds) {
                observableArrayList.addAll(infeedAds);
            }

            @Override
            public void onFailure(Exception e) {
                Log.w("X-lift Example", e);
            }
        });
    }
}
