package com.example.appkotlin

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast

import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlaybackException

import java.util.ArrayList

import com.videopalyer.kotlin.listener.VideoInfoListener
import com.videopalyer.kotlin.video.ManualPlayer
import com.videopalyer.kotlin.widget.VideoPlayerView

class MainDetailedActivity : Activity() {

    private var exoPlayerManager: ManualPlayer? = null
    private var videoPlayerView: VideoPlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        videoPlayerView = findViewById(R.id.exo_play_context_id) as VideoPlayerView
        exoPlayerManager = ManualPlayer(this, videoPlayerView, DataSource(this.application))
        //设置视频标题
        exoPlayerManager!!.setTitle("视频标题")
        //设置水印图
        exoPlayerManager!!.setExoPlayWatermarkImg(R.mipmap.watermark_big)
        exoPlayerManager!!.setOnWindowListener(object : VideoWindowListener() {
            fun onCurrentIndex(currentIndex: Int, windowCount: Int) {
                Toast.makeText(application, currentIndex.toString() + "windowCount:" + windowCount, Toast.LENGTH_SHORT).show()
            }
        })
        //设置开始播放进度
        //  exoPlayerManager.setPosition(1000);
        // exoPlayerManager.setPlayUri("http://mp4.vjshi.com/2017-10-17/b81c7a35932c5bbacdc177534398fe87.mp4","http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4");
        // exoPlayerManager.setPlayUri(Environment.getExternalStorageDirectory().getAbsolutePath()+"/VID_20170925_154925.mp4");
        //String [] test={"http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4","http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4","http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4"};
        //String[] name={"超清","高清","标清"};
        //开启线路设置
        // exoPlayerManager.setShowVideoSwitch(true);
        //exoPlayerManager.setPlaySwitchUri(test,name);
        val bean = TestDataBean()
        val bean1 = TestDataBean()
        val listss = ArrayList<TestDataBean>()
        if (Build.VERSION.SDK_INT < 21) {//低版本不支持高分辨视频
            //exoPlayerManager.setPlayUri("http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4");
            bean.setUri("http://120.25.246.21/vrMobile/travelVideo/zhejiang_xuanchuanpian.mp4")
            bean1.setUri("http://mp4.vjshi.com/2017-10-17/b81c7a35932c5bbacdc177534398fe87.mp4")
        } else {
            //4k 视频
            // exoPlayerManager.setPlayUri("http://mp4.vjshi.com/2016-07-13/16190d61b7dbddbeb721f1b994fd7424.mp4");
            bean.setUri("http://mp4.vjshi.com/2016-07-13/16190d61b7dbddbeb721f1b994fd7424.mp4")
            bean1.setUri("http://mp4.vjshi.com/2017-10-17/b81c7a35932c5bbacdc177534398fe87.mp4")
        }
        listss.add(bean)
        listss.add(bean1)
        exoPlayerManager!!.setPlayUri(listss)
        exoPlayerManager!!.setVideoInfoListener(object : VideoInfoListener {
            override fun onPlayStart() {

            }

            override fun onLoadingChanged() {

            }

            override fun onPlayerError(e: ExoPlaybackException?) {

            }

            override fun onPlayEnd() {
                Toast.makeText(application, "asd", Toast.LENGTH_SHORT).show()
            }

            override fun onRepeatModeChanged(repeatMode: Int) {

            }

            override fun isPlaying(playWhenReady: Boolean) {

            }
        })
        //是否屏蔽进度控件拖拽快进视频（例如广告视频，（不允许用户））
        //exoPlayerManager.setSeekBarSeek(false);
        //设置视循环播放
        //exoPlayerManager.setLooping(10);
        //d隐藏控制布局
        // exoPlayerManager.hideControllerView();
        //隐藏进度条
        // exoPlayerManager.hideSeekBar();
        //显示进度条
        //  exoPlayerManager.showSeekBar();
        //是否播放
        // exoPlayerManager.isPlaying();
        //设置播放视频倍数  快进播放和慢放播放
        //exoPlayerManager.setPlaybackParameters(2f,2f);
        // videoPlayerView.getPreviewImage().setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this)
                .load("http://i3.letvimg.com/lc08_yunzhuanma/201707/29/20/49/3280a525bef381311b374579f360e80a_v2_MTMxODYyNjMw/thumb/2_960_540.jpg")
                .fitCenter()
                .placeholder(R.mipmap.test)
                .into(videoPlayerView!!.previewImage)


    }

    public override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        exoPlayerManager!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        exoPlayerManager!!.onPause()
    }


    override fun onDestroy() {
        exoPlayerManager!!.onDestroy()
        super.onDestroy()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        exoPlayerManager!!.onConfigurationChanged(newConfig)//横竖屏切换
        super.onConfigurationChanged(newConfig)
        /*  if (!VideoPlayUtils.isLand(this)) { //隐藏状态栏的
            VideoPlayUtils.hideActionBar(this);
        }*/
    }

    override fun onBackPressed() {
        if (exoPlayerManager!!.onBackPressed()) {
            ActivityCompat.finishAfterTransition(this)

        }
    }

    companion object {
        private val TAG = "MainDetailedActivity"
    }
}
