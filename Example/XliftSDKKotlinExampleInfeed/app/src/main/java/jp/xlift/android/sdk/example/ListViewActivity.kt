package jp.xlift.android.sdk.example

import android.databinding.ObservableArrayList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView

import jp.xlift.android.sdk.infeed.XliftInfeedAd
import jp.xlift.android.sdk.infeed.XliftInfeedAdLoader

class ListViewActivity : AppCompatActivity() {
    private var observableArrayList = ObservableArrayList<XliftInfeedAd>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = XliftInfeedAdArrayAdapter(this@ListViewActivity, observableArrayList)

        // mdediaIdは、管理画面でメディア登録を行った際に発行されたIDをお使い下さい
        val xliftInfeedAdLoader = XliftInfeedAdLoader(123456789, true)
        xliftInfeedAdLoader.loadInfeedAd(callback = object : XliftInfeedAdLoader.Callback {
            override fun onSuccess(infeedAds: List<XliftInfeedAd>) {
                observableArrayList.addAll(infeedAds)
            }

            override fun onFailure(e: Exception) {
                Log.w("X-lift Example", e)
            }
        })
    }
}
