package jp.xlift.android.sdk.example

import android.content.Intent
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView

import jp.xlift.android.sdk.infeed.XliftInfeedAd
import java.io.InputStream

class XliftInfeedAdViewModel(val xliftInfeedAd: XliftInfeedAd) {

    val title: String
        get() = this.xliftInfeedAd.title

    val sourceTitle: String
        get() = if (xliftInfeedAd.adType == XliftInfeedAd.AdType.Ad)
            String.format("[PR] %s", xliftInfeedAd.siteTitle)
        else
            xliftInfeedAd.siteTitle

    fun loadImage(imageView: ImageView) {
        this.xliftInfeedAd.loadImage(object : XliftInfeedAd.LoadImageCallback {
            override fun onSuccess(imageInputStream: InputStream) {
                imageView.setImageBitmap(BitmapFactory.decodeStream(imageInputStream))
            }

            override fun onFailure(e: Exception) {
                Log.w("XliftSDK-Example", e)
            }
        })
    }

    fun onClick(view: View) {
        view.context.startActivity(
            Intent(Intent.ACTION_VIEW, xliftInfeedAd.createClickUri())
        )
    }

    companion object {
        @BindingAdapter("setXliftImage")
        @JvmStatic
        fun setImage(imageView: ImageView, viewModel: XliftInfeedAdViewModel) {
            viewModel.loadImage(imageView)
        }
    }
}
