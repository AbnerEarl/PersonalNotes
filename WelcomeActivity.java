package com.silang.superfileview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silang.superfileview.Upload.FtpUpload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WelcomeActivity extends AppCompatActivity {

    private Button startcon;
    private ImageView welcom;
    private TextView company;
    FtpUpload ff=new FtpUpload();
    //FtpUpload gg=new FtpUpload();
    private int ttt=0;



    private  String datapath= Environment.getExternalStorageDirectory() + "/Systems/Conference/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        startcon=(Button)this.findViewById(R.id.button_start);
        welcom=(ImageView)this.findViewById(R.id.imageView);
        company=(TextView)this.findViewById(R.id.textView);
        getData1();
        getData2();
        getDatatitle();

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {

                String companyname=GetCName(datapath+"CompanyName/CompanyName.txt");

                if (companyname!=null&&companyname.length()>2){
                    company.setText(companyname);
                }


                try{
                    Bitmap bm = BitmapFactory.decodeFile(datapath+"Welcome/Welcome.png");
                    welcom.setImageBitmap(bm);

                }catch (Exception e){
                    Log.i("图片加载错误：",e.toString());
                }


                if (ttt<5){
                    ttt++;
                    handler.postDelayed(this, 2000);
                }

            }
        };
        handler.postDelayed(runnable, 1000);


        startcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                Intent intent=new Intent(WelcomeActivity.this,ConferenceActivity.class);
                startActivity(intent);
                //finish();
            }
        });


    }

    private void getData1(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                File filecname = new File(datapath+"Welcome/");
                filecname.mkdirs();// 创建文件夹
                ff.download(WelcomeActivity.this,"Welcome/","Welcome.png","Welcome/");



            }
        }).start();


    }


    private void getData2(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                File filecname = new File(datapath+"CompanyName/");
                filecname.mkdirs();// 创建文件夹
                ff.download(WelcomeActivity.this,"CompanyName/","CompanyName.txt","CompanyName/");

                String companyname=GetCName(datapath+"CompanyName/CompanyName.txt");

                if (companyname!=null&&companyname.length()>2){
                    company.setText(companyname);
                }


            }
        }).start();
    }



    private String GetCName(String path) {
        StringBuffer sb = new StringBuffer();
        String content = "";

        try {

            File file = new File(path);
            /*BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            br.close();
*/

            InputStream instream = new FileInputStream(file);
            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream, "GBK");
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line = "";
                //分行读取
                while ((line = buffreader.readLine()) != null) {
                    content += line;
                }
                instream.close();       //关闭输入流
            }


        } catch (Exception e) {
            Log.i("读取文件错误：", e.toString());
        }
        return content;
    }


    private void getDatatitle(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                File filecname = new File(datapath+"ConferenceTitle/");
                filecname.mkdirs();// 创建文件夹
                ff.download(WelcomeActivity.this,"ConferenceTitle/","ConferenceTitle.txt","ConferenceTitle/");


            }
        }).start();

    }



    //删除文件夹和文件夹里面的文件
    public  void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public  void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身

        WelcomeActivity.this.finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog isExit = new AlertDialog.Builder(this).create();


            isExit.setMessage("您确定要退出系统吗？");

            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);

            isExit.show();

        }

        return false;

    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE: {

                    deleteDir(datapath);
                  //  finish();



                }
                break;
                case AlertDialog.BUTTON_NEGATIVE:
                    break;
                default:
                    break;
            }
        }
    };

}
