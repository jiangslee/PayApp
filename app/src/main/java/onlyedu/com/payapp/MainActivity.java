package onlyedu.com.payapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import onlyedu.com.payapp.Scan.ScanActivity;
import onlyedu.com.payapp.WebActivity.WebActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);


        btn1.setOnClickListener(onClickListenerbtn1);
        btn2.setOnClickListener(onClickListenerbtn2);
        btn3.setOnClickListener(onClickListenerbtn3);

        LibConfig.context = getApplicationContext();
        HttpUrl.host = LibConfig.getKeyShareVarForString("host");

    }


    View.OnClickListener onClickListenerbtn3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final EditText editText=new EditText(MainActivity.this);

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("输入测试服务地址");
            builder.setView(editText);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LibConfig.setKeyShareVar("host",editText.getText().toString());
                    HttpUrl.host = editText.getText().toString();
                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("取消",null);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }
    };


    /**
     * 扫码收款
     */
    View.OnClickListener onClickListenerbtn1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, ScanActivity.class);
            startActivityForResult(intent,ScanActivity.SCANRESULTREQUEST);

        }
    };

    /**
     * 扫码签到
     */
    View.OnClickListener onClickListenerbtn2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MainActivity.this,"暂时没有开放",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, WebActivity.class);
            startActivity(intent);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==ScanActivity.SCANRESULTREQUEST)
        {
            if (resultCode ==1)
            {
                String code =data.getExtras().getString("code");
                Log.i("条码:" ,code);
                HttpUrl.codeUrl = String.format(HttpUrl.codeUrlModel,HttpUrl.host,code);
                Intent intent=new Intent(MainActivity.this,WebActivity.class);
                intent.putExtra("url",HttpUrl.codeUrl);
                startActivity(intent);
            }
        }
    }

}
