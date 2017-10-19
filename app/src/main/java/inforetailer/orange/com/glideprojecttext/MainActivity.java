package inforetailer.orange.com.glideprojecttext;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.liuguangqiang.cookie.CookieBar;
import com.liuguangqiang.cookie.OnActionClickListener;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.BitmapBatchCallback;
import com.zxy.tiny.callback.BitmapCallback;
import com.zxy.tiny.callback.FileBatchCallback;
import com.zxy.tiny.callback.FileCallback;
import com.zxy.tiny.callback.FileWithBitmapBatchCallback;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissonItem;

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
    @InjectView(R.id.dialog)
    FloatingActionButton dialog;

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

        setPermission();

//        tinyBitmap();
    }

    private void tinyBitmap() {
        Tiny.getInstance().init(getApplication());//set in application


//        AsBitmap

        Tiny.BitmapCompressOptions bitmapCompressOptions = new Tiny.BitmapCompressOptions();
        //options.height = xxx;//some compression configuration.
        Tiny.getInstance().source("").asBitmap().withOptions(bitmapCompressOptions).compress(new BitmapCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap bitmap, Throwable t) {
                //return the compressed bitmap object
            }
        });


//        AsFile

        Tiny.FileCompressOptions fileCompressOptions = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").asFile().withOptions(fileCompressOptions).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile, Throwable t) {
                //return the compressed file path
            }
        });

//        AsFileWithReturnBitmap

        Tiny.FileCompressOptions fileCompressOptions2 = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").asFile().withOptions(fileCompressOptions2).compress(new FileWithBitmapCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                //return the compressed file path and bitmap object
            }
        });


//        BatchAsBitmap

        Tiny.BitmapCompressOptions bitmapCompressOptions2 = new Tiny.BitmapCompressOptions();
        Tiny.getInstance().source("").batchAsBitmap().withOptions(bitmapCompressOptions2).batchCompress(new BitmapBatchCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap[] bitmaps, Throwable t) {
                //return the batch compressed bitmap object
            }
        });


//        BatchAsFile

        Tiny.FileCompressOptions fileCompressOptions3 = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").batchAsFile().withOptions(fileCompressOptions3).batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile, Throwable t) {
                //return the batch compressed file path
            }
        });


//        BatchAsFileWithReturnBitmap

        Tiny.FileCompressOptions fileCompressOptions4 = new Tiny.FileCompressOptions();
        Tiny.getInstance().source("").batchAsFile().withOptions(fileCompressOptions4).batchCompress(new FileWithBitmapBatchCallback() {
            @Override
            public void callback(boolean isSuccess, Bitmap[] bitmaps, String[] outfile, Throwable t) {
                //return the batch compressed file path and bitmap object
            }
        });
    }

    private void setPermission() {
        /*
        List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
permissonItems.add(new PermissonItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_memory));
permissonItems.add(new PermissonItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
HiPermission.create(MainActivity.this)
            .permissions(permissonItems)
            .checkMutiPermission(...);
         */
        List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
        permissonItems.add(new PermissonItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_memory));
        permissonItems.add(new PermissonItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
//        HiPermission.create(context)
//                .permissions(permissonItems)
//                .checkMutiPermission(...);

        HiPermission.create(MainActivity.this)
                .title("亲爱的上帝")
                .permissions(permissonItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//图标的颜色
                .msg("为了保护世界的和平，开启这些权限吧！\n你我一起拯救世界！")
                .style(R.style.PermissionBlueStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        Toast.makeText(context, "用户关闭权限申请", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(context, "所有权限申请完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        Toast.makeText(context, "onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                        Log.i("ntl", "onGuarantee");
                    }
                });
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


    @OnClick({R.id.fab, R.id.next, R.id.dialog})
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

                startActivity(new Intent(context, SecondActivity.class));

                break;

            case R.id.dialog:

                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(findViewById(R.id.next));
                /*
                layoutGravity
backgroundColor
titleColor
messageColor
actionColor
duration
                 */

//                new CookieBar.Builder(MainActivity.this)
//                        .setLayoutGravity(Gravity.TOP)
//                        .setTitle("TITLE")
//                        .setMessage("MESSAGE")
//                        .show();

                new CookieBar.Builder(MainActivity.this)
                        .setTitle("TITLE")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("MESSAGE")
                        .setAction("ACTION", new OnActionClickListener() {
                            @Override
                            public void onClick() {
                            }
                        })
                        .show();
                break;
        }
    }


}
