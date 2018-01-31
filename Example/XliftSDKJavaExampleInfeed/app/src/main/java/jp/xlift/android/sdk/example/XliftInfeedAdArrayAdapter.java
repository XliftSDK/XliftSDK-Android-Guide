package jp.xlift.android.sdk.example;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import jp.xlift.android.sdk.example.databinding.ListItemBinding;
import jp.xlift.android.sdk.infeed.XliftInfeedAd;

public class XliftInfeedAdArrayAdapter extends ArrayAdapter<XliftInfeedAd> {

    public XliftInfeedAdArrayAdapter(Context context, ObservableArrayList<XliftInfeedAd> observableAds) {
        super(context, 0, observableAds);
        observableAds.addOnListChangedCallback(new ObservableArrayList.OnListChangedCallback<ObservableArrayList<XliftInfeedAd>>() {
            @Override
            public void onItemRangeChanged(ObservableArrayList<XliftInfeedAd> observableArrayList, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeInserted(ObservableArrayList<XliftInfeedAd> observableArrayList, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableArrayList<XliftInfeedAd> observableArrayList, int i, int i1, int i2) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableArrayList<XliftInfeedAd> observableArrayList, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onChanged(ObservableArrayList<XliftInfeedAd> observableArrayList) {
                notifyDataSetChanged();
            }
        });
    }

    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ListItemBinding binding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemBinding) convertView.getTag();
        }
        binding.setViewModel(new XliftInfeedAdViewModel(this.getItem(position)));
        return binding.getRoot();
    }
}
