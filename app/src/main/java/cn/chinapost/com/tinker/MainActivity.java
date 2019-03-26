package cn.chinapost.com.tinker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.chinapost.com.tinker.customtinker.TinkerManager;
import cn.chinapost.com.tinker.imageloader.ImageLoaderManager;
import cn.chinapost.com.tinker.zxing.app.CaptureActivity;

public class MainActivity extends AppCompatActivity {


    private static final String FILE_END = ".apk";
    //private static final String URL ="http://f.hiphotos.baidu.com/image/pic/item/4034970a304e251f503521f5a586c9177e3e53f9.jpg";
    private String mPatchDir;
    private ImageView imageView;
    private String url;
    private ArrayList<String> mUrls;
    private EditText et;
    private String []  photos = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.edit_text);

        ///storage/emulated/0/Android/data/cn.chinapost.com.tinker/cache/tpatch/
        mPatchDir =  getExternalCacheDir().getAbsolutePath()+"/tpatch/";
        File file = new File(mPatchDir);
        if (file==null || !file.exists()){
            file.mkdir();
        }

        initUri();

        imageView = findViewById(R.id.image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,PhotoViewActivity.class);
                intent.putStringArrayListExtra("photo",mUrls);
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> initUri() {

        mUrls = new ArrayList<String>();
        mUrls.add("http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg");
        mUrls.add("http://c.hiphotos.baidu.com/image/pic/item/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg");
        mUrls.add("http://g.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d5277fd5d0628535e5dd6f4a.jpg");
        mUrls.add("http://e.hiphotos.baidu.com/image/pic/item/4e4a20a4462309f7e41f5cfe760e0cf3d6cad6ee.jpg");
        mUrls.add("http://b.hiphotos.baidu.com/image/pic/item/9d82d158ccbf6c81b94575cfb93eb13533fa40a2.jpg");
        mUrls.add("http://e.hiphotos.baidu.com/image/pic/item/4bed2e738bd4b31c1badd5a685d6277f9e2ff81e.jpg");
        mUrls.add("http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg");
        mUrls.add("http://a.hiphotos.baidu.com/image/pic/item/f2deb48f8c5494ee5080c8142ff5e0fe99257e19.jpg");
        mUrls.add("http://f.hiphotos.baidu.com/image/pic/item/4034970a304e251f503521f5a586c9177e3e53f9.jpg");
        return mUrls;
    }

    int count = 0;
    public void loadPatch(View view) {
        //TinkerManager.loadPatch(getPatchName(),"");
        url =  mUrls.get(count);
        ImageLoaderManager.getInstance(this).displayImage(imageView,url);
        count++;
        if (count==mUrls.size()){
            count = 0;
        }
    }

    private String getPatchName() {
        return mPatchDir.concat("imooc").concat(FILE_END);
    }

    public void view_pager(View view) {
        startActivityForResult(new Intent(this,CaptureActivity.class),0x00);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (RESULT_OK==resultCode){
            if (requestCode==0x00){
                String result = data.getStringExtra("content");
                //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                et.setText(result);
            }
        }else {
            Toast.makeText(this, "无法获取扫描结果", Toast.LENGTH_SHORT).show();
        }
    }

    public void take_photo(View view) {
        startActivityForResult(new Intent(this,TakePhotoActivity.class),0x11);
    }
}