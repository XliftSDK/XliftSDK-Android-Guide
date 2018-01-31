# 目次

* [XliftSDKのインストール](#install)
* [インフィード広告](#infeed)
    * [メディア設定](#infeed/setting)
    * [インフィード広告のロード](#infeed/load)
    * [インフィード広告の表示](#infeed/display)
    * [インフィード広告のクリック時の遷移処理](#infeed/click)

<a name="install"></a>
# XliftSDKのインストール
## Gradleでインストール
build.gradleに下記を追加し、[Sync Project]を実行
```groovy
repositories {
...
    maven { url 'https://xliftsdk.github.io/XliftSDK-Android/'}
}

dependencies {
...
    compile 'jp.x-lift.android:xlift-sdk:1.0.1'
}
```

<a name="infeed"></a>
# インフィード広告
<a name="infeed/setting"></a>
## インフィード広告用のメディア設定
[X-lift管理画面](https://console.x-lift.jp/)でインフィード広告を掲載するアプリをメディア登録してください。

「ウィジェット設定（スロットの決定）」にて、「Advertising」、または「自社枠」のスロットを作成して下さい。
スロット数が、一度の広告リクエストで取得する広告数となります。

<a name="infeed/load"></a>
## インフィード広告のロード

```java
//(1) インポート
import jp.xlift.android.sdk.infeed.XliftInfeedAd;
import jp.xlift.android.sdk.infeed.XliftInfeedAdLoader;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...

        // (2)XliftInfeedAdLoaderを生成。コンストラクタにメディアIDを指定。
        XliftInfeedAdLoader xliftInfeedAdLoader = new XliftInfeedAdLoader(123456789);
        // ※ テスト時は、第二引数でtrueを指定。
        // XliftInfeedAdLoader xliftInfeedAdLoader = new XliftInfeedAdLoader(123456789, true);

        // (3)インフィード広告をロード
        xliftInfeedAdLoader.loadInfeedAd(new XliftInfeedAdLoader.Callback() {
            // (4)Callbackを実装して、広告データ処理する
            @Override
            public void onSuccess(final List<XliftInfeedAd> infeedAds) {
                // (5)広告データを正常に取得できた時
            }

            @Override
            public void onFailure(Exception e) {
                // (6)エラー発生時
            }
        });
    }
}
```

<a name="infeed/display"></a>
## インフィード広告の表示
`XliftInfeedAd`の情報をもとに、表示させます。
広告については、表示内容が広告であることをユーザーが認識できる文言を必ず記述して下さい。

```java
  titleView.setText(xliftInfeedAd.getTitle());
  siteTitleView.setText(String.format("[PR] %s", xliftInfeedAd.getSiteTitle()));
  xliftInfeedAd.loadImage(new XliftInfeedAd.LoadImageCallback() {
      @Override
      public void onSuccess(InputStream imageInputStream) {
          imageView.setImageBitmap(BitmapFactory.decodeStream(imageInputStream));
      }

      @Override
      public void onFailure(Exception e) {
          Log.w("XliftSDK-Example", e);
      }
  });
```

<a name="infeed/click"></a>
## インフィード広告のクリック時の遷移処理
広告がクリックされたら、`XliftInfeedAd.createClickUri`で取得できる遷移先へ移動させる。
```java
  context.startActivity(
      Intent(Intent.ACTION_VIEW, xliftInfeedAd.createClickUri())
  )
```
