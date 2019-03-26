package cn.chinapost.com.tinker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.util.Hashtable;
import java.util.Vector;

import cn.chinapost.com.tinker.idcardcamera.camera.CameraActivity;
import cn.chinapost.com.tinker.zxing.decode.BitmapLuminanceSource;
import cn.chinapost.com.tinker.zxing.decode.DecodeFormatManager;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView viewById;
    private EditText edit_text;
    private String codeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        viewById = findViewById(R.id.image_view);
        edit_text = findViewById(R.id.edit_text);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = ((BitmapDrawable) viewById.getDrawable()).getBitmap();
                        Result result = parsePic(bitmap);
                        if (result==null){
                            codeNo = null;
                        }else {
                            codeNo = result.toString();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                edit_text.setText(codeNo);
                            }
                        });
                    }
                }).start();

            }
        });
    }

    public void take_photo(View view) {
        CameraActivity.toCameraActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            //获取图片路径，显示图片
            final String path = CameraActivity.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                viewById.setImageBitmap(BitmapFactory.decodeFile(path));
            }
        }

    }

    public Result parsePic(Bitmap bitmap) {
        // 解析转换类型UTF-8
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();

            // 这里设置可扫描的类型，我这里选择了都支持
            decodeFormats.addAll(DecodeFormatManager.PRODUCT_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            //decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            //decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }

        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        hints.put(DecodeHintType.TRY_HARDER,true);
        // 新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
       /* int lWidth = bitmap.getWidth();
        int lHeight = bitmap.getHeight();
        int[] lPixels = new int[lWidth * lHeight];
        bitmap.getPixels(lPixels, 0, lWidth, 0, 0, lWidth, lHeight);
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(lWidth,
                lHeight, lPixels);
        // 将图片转换成二进制图片
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                rgbLuminanceSource));*/
        // 初始化解析对象
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(hints);
        //QRCodeReader reader = new QRCodeReader();
        // 开始解析
        Result result = null;
        try {
            result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(bitmap))));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
