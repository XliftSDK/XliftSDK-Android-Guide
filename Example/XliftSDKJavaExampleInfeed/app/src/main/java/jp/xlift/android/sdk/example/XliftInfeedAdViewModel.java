package jp.xlift.android.sdk.example;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

import jp.xlift.android.sdk.infeed.XliftInfeedAd;

public class XliftInfeedAdViewModel {
    private XliftInfeedAd xliftInfeedAd;

    public XliftInfeedAdViewModel(XliftInfeedAd xliftInfeedAd) {
        this.xliftInfeedAd = xliftInfeedAd;
    }

    public XliftInfeedAd getXliftInfeedAd() {
        return xliftInfeedAd;
    }

    public String getTitle() {
        return this.xliftInfeedAd.getTitle();
    }

    public String getSourceTitle() {
        return xliftInfeedAd.getAdType() == XliftInfeedAd.AdType.Ad
                ? String.format("[PR] %s", xliftInfeedAd.getSiteTitle()) : xliftInfeedAd.getSiteTitle();
    }

    public void loadImage(final ImageView imageView) {
        this.xliftInfeedAd.loadImage(new XliftInfeedAd.LoadImageCallback() {
            @Override
            public void onSuccess(InputStream imageInputStream) {
                imageView.setImageBitmap(BitmapFactory.decodeStream(imageInputStream));
            }

            @Override
            public void onFailure(Exception e) {
                Log.w("XliftSDK-Example", e);
            }
        });
    }

    public void onClick(View view) {
        view.getContext().startActivity(
            new Intent(Intent.ACTION_VIEW, xliftInfeedAd.createClickUri())
        );
    }

    @BindingAdapter("setXliftImage")
    public static void setImage(final ImageView imageView, XliftInfeedAdViewModel viewModel) {
        viewModel.loadImage(imageView);
    }
}
