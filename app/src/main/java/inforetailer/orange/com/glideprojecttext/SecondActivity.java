package inforetailer.orange.com.glideprojecttext;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by niut.l on 2017/10/16.
 * AndroidViewAnimations 动画库
 * https://github.com/daimajia/AndroidViewAnimations
 */

public class SecondActivity extends Activity {

    @InjectView(R.id.download)
    Button download;
    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.clear)
    Button clear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ButterKnife.inject(this);
    }

    /**
     * Effects

     Attension

     Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave

     Special

     Hinge, RollIn, RollOut,Landing,TakingOff,DropOut

     Bounce

     BounceIn, BounceInDown, BounceInLeft, BounceInRight, BounceInUp

     Fade

     FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight

     FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp

     Flip

     FlipInX, FlipOutX, FlipOutY

     Rotate

     RotateIn, RotateInDownLeft, RotateInDownRight, RotateInUpLeft, RotateInUpRight

     RotateOut, RotateOutDownLeft, RotateOutDownRight, RotateOutUpLeft, RotateOutUpRight

     Slide

     SlideInLeft, SlideInRight, SlideInUp, SlideInDown

     SlideOutLeft, SlideOutRight, SlideOutUp, SlideOutDown

     Zoom

     ZoomIn, ZoomInDown, ZoomInLeft, ZoomInRight, ZoomInUp

     ZoomOut, ZoomOutDown, ZoomOutLeft, ZoomOutRight, ZoomOutUp
     */


    @OnClick({R.id.download, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.download:
                postAsynHttp();
                break;
            case R.id.clear:
                content.setText("");

                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(findViewById(R.id.clear));
                break;
        }
    }

    private OkHttpClient mOkHttpClient;
    //异步POST请求
    private void postAsynHttp() {
        mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        content.setText(str);
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

//3.异步上传文件
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private void postAsynFile() {
        mOkHttpClient=new OkHttpClient();
        File file = new File("/sdcard/wangshu.txt");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("wangshu",response.body().string());
            }
        });
    }

//4.异步下载文件
    private void downAsynFile() {
        mOkHttpClient = new OkHttpClient();
        String url = "http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg";
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File("/sdcard/wangshu.jpg"));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    Log.i("wangshu", "IOException");
                    e.printStackTrace();
                }

                Log.d("wangshu", "文件下载成功");
            }
        });
    }
}
