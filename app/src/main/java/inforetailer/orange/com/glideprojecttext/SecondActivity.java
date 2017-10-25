package inforetailer.orange.com.glideprojecttext;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.stone.vega.library.VegaLayoutManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
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
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//创建默认的线性LayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setLayoutManager(new VegaLayoutManager());
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能x
        recyclerView.setHasFixedSize(true);
//创建并设置Adapter
        MyAdapter mAdapter = new MyAdapter(getDummyDatas());
        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                //DO your fucking bussiness here!
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(findViewById(R.id.clear));
            }
        });
        recyclerView.setAdapter(mAdapter);

//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mLayoutManager = new GridLayoutManager(context,columNum);
//        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void addItem(MyAdapter mAdapter, String content, int position) {
//        datas.add(position, content);
        mAdapter.notifyItemInserted(position); //Attention!
    }

    public void removeItem(MyAdapter mAdapter, String model) {
//        int position = datas.indexOf(model);
//        datas.remove(position);
//        mAdapter.notifyItemRemoved(position);//Attention!
    }

    private String[] datas;

    private String[] getDummyDatas() {
        datas = new String[]{"this is 1", "this is 2", "this is 3", "this is 4", "this is 5", "this is 6"};
        return datas;
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
        public String[] datas = null;


        public OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public MyAdapter(String[] datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            view.setOnClickListener(this);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(datas[position]);
            viewHolder.itemView.setTag(datas[position]);
        }


        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.length;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v, (String) v.getTag());
            }
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.item_text);
            }
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    /**
     * Effects
     * <p>
     * Attension
     * <p>
     * Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave
     * <p>
     * Special
     * <p>
     * Hinge, RollIn, RollOut,Landing,TakingOff,DropOut
     * <p>
     * Bounce
     * <p>
     * BounceIn, BounceInDown, BounceInLeft, BounceInRight, BounceInUp
     * <p>
     * Fade
     * <p>
     * FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight
     * <p>
     * FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp
     * <p>
     * Flip
     * <p>
     * FlipInX, FlipOutX, FlipOutY
     * <p>
     * Rotate
     * <p>
     * RotateIn, RotateInDownLeft, RotateInDownRight, RotateInUpLeft, RotateInUpRight
     * <p>
     * RotateOut, RotateOutDownLeft, RotateOutDownRight, RotateOutUpLeft, RotateOutUpRight
     * <p>
     * Slide
     * <p>
     * SlideInLeft, SlideInRight, SlideInUp, SlideInDown
     * <p>
     * SlideOutLeft, SlideOutRight, SlideOutUp, SlideOutDown
     * <p>
     * Zoom
     * <p>
     * ZoomIn, ZoomInDown, ZoomInLeft, ZoomInRight, ZoomInUp
     * <p>
     * ZoomOut, ZoomOutDown, ZoomOutLeft, ZoomOutRight, ZoomOutUp
     */


    @OnClick({R.id.download, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.download:
                postAsynHttp();
                break;
            case R.id.clear:
                content.setText("");

//                YoYo.with(Techniques.Tada)
//                        .duration(700)
//                        .repeat(5)
//                        .playOn(findViewById(R.id.clear));

                break;
        }
    }

    private OkHttpClient mOkHttpClient;

    //异步POST请求
    private void postAsynHttp() {
        mOkHttpClient = new OkHttpClient();
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
        mOkHttpClient = new OkHttpClient();
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
                Log.i("wangshu", response.body().string());
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
