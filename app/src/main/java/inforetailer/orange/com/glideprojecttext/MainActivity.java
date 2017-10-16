package inforetailer.orange.com.glideprojecttext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.next)
    FloatingActionButton next;

    private Context context;

    /**
     * http://www.cnblogs.com/guilin-hu/p/5706916.html
     * https://github.com/wasabeef/glide-transformationsF
     * <p>
     * Transformations
     * <p>
     * Crop---
     * <p>
     * CropTransformation, CropCircleTransformation, CropSquareTransformation, RoundedCornersTransformation
     * <p>
     * Color---
     * <p>
     * ColorFilterTransformation, GrayscaleTransformation
     * <p>
     * Blur---
     * <p>
     * BlurTransformation
     * <p>
     * Mask---
     * <p>
     * MaskTransformation
     * <p>
     * GPU Filter (use GPUImage)---
     * <p>
     * Will require add dependencies for GPUImage.---
     * <p>
     * ToonFilterTransformation, SepiaFilterTransformation, ContrastFilterTransformation
     * InvertFilterTransformation, PixelationFilterTransformation, SketchFilterTransformation
     * SwirlFilterTransformation, BrightnessFilterTransformation, KuwaharaFilterTransformation VignetteFilterTransformation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        if (id == R.id.action_settings) {
            return false;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.fab, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Glide.with(context)
//                        .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
//                        .into(image);

//                Glide.with(context).load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
//                        .apply(bitmapTransform(new BlurTransformation(25)))
//                        .into(image);

                MultiTransformation multi = new MultiTransformation(
                        new BlurTransformation(25),
                        new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
                Glide.with(context).load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                        .apply(bitmapTransform(multi))
                        .into((ImageView) findViewById(R.id.image));

                break;
            case R.id.next:

                startActivity(new Intent(context,SecondActivity.class));

                break;
        }
    }
}
