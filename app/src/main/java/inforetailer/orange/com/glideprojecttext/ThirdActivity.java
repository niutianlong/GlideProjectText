package inforetailer.orange.com.glideprojecttext;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by niut.l on 2017/10/16.
 */

public class ThirdActivity extends Activity {

    @InjectView(R.id.panorama_image_view)
    PanoramaImageView panoramaImageView;

    private GyroscopeObserver gyroscopeObserver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.inject(this);


        // Initialize GyroscopeObserver.
        gyroscopeObserver = new GyroscopeObserver();
        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        // The default value is π/9.
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);

        PanoramaImageView panoramaImageView = (PanoramaImageView) findViewById(R.id.panorama_image_view);
        // Set GyroscopeObserver for PanoramaImageView.
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

        panoramaImageView.setOnPanoramaScrollListener(new PanoramaImageView.OnPanoramaScrollListener() {
            @Override
            public void onScrolled(PanoramaImageView view, float offsetProgress) {
                // Do something here.
                // The offsetProgress range from -1 to 1, indicating the image scrolls
                // from left(top) to right(bottom).
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Register GyroscopeObserver.
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister GyroscopeObserver.
        gyroscopeObserver.unregister();
    }
}
