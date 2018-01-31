package jp.xlift.android.sdk.example

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.xlift.android.sdk.example.databinding.ListItemBinding
import jp.xlift.android.sdk.infeed.XliftInfeedAd

class XliftInfeedAdArrayAdapter(context: Context, observableAds: ObservableArrayList<XliftInfeedAd>) : ArrayAdapter<XliftInfeedAd>(context, 0, observableAds) {

    init {
        observableAds.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableArrayList<XliftInfeedAd>>() {
            override fun onItemRangeChanged(observableArrayList: ObservableArrayList<XliftInfeedAd>, i: Int, i1: Int) {
                notifyDataSetChanged()
            }

            override fun onItemRangeInserted(observableArrayList: ObservableArrayList<XliftInfeedAd>, i: Int, i1: Int) {
                notifyDataSetChanged()
            }

            override fun onItemRangeMoved(observableArrayList: ObservableArrayList<XliftInfeedAd>, i: Int, i1: Int, i2: Int) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(observableArrayList: ObservableArrayList<XliftInfeedAd>, i: Int, i1: Int) {
                notifyDataSetChanged()
            }

            override fun onChanged(observableArrayList: ObservableArrayList<XliftInfeedAd>) {
                notifyDataSetChanged()
            }
        })
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItemBinding = convertView?.let {
            it.tag as ListItemBinding
        } ?: run {
            val inflater = LayoutInflater.from(context)
            val binding = DataBindingUtil.inflate<ListItemBinding>(inflater, R.layout.list_item, parent, false)
            binding.root.tag = binding
            binding
        }
        listItemBinding.viewModel = XliftInfeedAdViewModel(this.getItem(position))
        return listItemBinding.root
    }
}
