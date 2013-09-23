package alialicoo.com.dice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class SysUtil {

//    public SysUtil(Context context) {
//        Commdata.AppContext=context;
//       if( Commdata.VolleyQueue==null)
//       {
//    	   Commdata.VolleyQueue=Volley.newRequestQueue(context);
//       }
//    }
    
  

    void readSP() {
        SharedPreferences sp =  Commdata.AppContext.getSharedPreferences("data", 0);
        Commdata.helpshowflag = sp.getBoolean("helpshowflag", false);
        Commdata.autocoveredflag = sp.getBoolean("autocovered", false);
        Commdata.userrat_f = sp.getFloat("userat", 0f);
        // Log.d("readSP", ""+userrat_f);
//        if (!Commdata.helpshowflag) {
//            showhelpinfo();
//        }
    }

//    void showhelpinfo() {
//        Intent intent = new Intent( Commdata.AppContext, helppage.class);
//        Commdata.AppContext.startActivity(intent);
//    }

    public void copydbfile() {
        String outFileName = Commdata.dbpath + Commdata.dbname;
        // 检测是否已经创建
        File dir = new File(outFileName);
        if (dir.exists())
            return;
        // 检测/创建数据库的文件夹
        dir = new File(Commdata.dbpath);
        if (!dir.exists())
            dir.mkdir();

        InputStream input = null;
        OutputStream output = null;
        // 从资源中读取数据库流
        input =  Commdata.AppContext.getResources().openRawResource(R.raw.dicedb);

        try {
            output = new FileOutputStream(outFileName);
            // 拷贝到输出流
            byte[] buffer = new byte[2048];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void read_dice_db() {
        dicedb db = new dicedb();
        db.openDatabase();
        db.loadAll(Commdata.dices);
        db.close();
    }

//    void checkversionCode() {
//        // AppUpdate au = new AppUpdate();
//        // au.downapp();
//        updateinfoTh mth = new updateinfoTh();
//        mth.start();
//    }

    public void delad_sdcard() {
        try {
            // 获取扩展存储设备的文件目录
            File SDFile = new File(android.os.Environment.getExternalStorageDirectory() + "/DomobAppDownload");
            delAllFile(SDFile.getPath());
            File adpath = new File("/data/data/alialicoo.com.dice/databases");
            delAllFile(adpath.getPath());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile() && !temp.getPath().equalsIgnoreCase(Commdata.dbpath + Commdata.dbname)) {
                temp.delete();
            }
        }
    }
    
/*
    class updateinfoTh extends Thread {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            hget get = new hget();
            String bstr = get.getcontext(Commdata.update_addrs);

            try {
                String[] values = bstr.split("\\|");
                Commdata.apkdownloadaddrs = values[1];
                Commdata.versionCode_net = Integer.parseInt(values[0]);
            } catch (Exception e) {
                // TODO: handle exception
            }

            // if(mv>Integer.parseInt(Commdata.versionCode))
            // Toast.makeText(Dice.this, "请升级", Toast.LENGTH_LONG).show();
        }
    }
    */
}
