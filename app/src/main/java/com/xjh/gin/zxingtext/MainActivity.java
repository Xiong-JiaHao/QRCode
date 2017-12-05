package com.xjh.gin.zxingtext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mTvResult;
    private ImageView mImage;
    private EditText mInput;
    private CheckBox mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_result);
        mImage = findViewById(R.id.image);
        mInput = findViewById(R.id.et_text);
        mLogo = findViewById(R.id.cb_logo);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scan(View view){
        Log.e("tags","1");
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);
        Log.e("tags","2");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String res = bundle.getString("result");
            Log.e("tags",res);
            mTvResult.setText(res);
        }
    }

    public void make(View view){
        String input = mInput.getText().toString();
        if(input.equals("")){
            Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }
        else{
            Bitmap bitmap = EncodingUtils.createQRCode(input,500,500,mLogo.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher):null);
            mImage.setImageBitmap(bitmap);
        }
    }
}
