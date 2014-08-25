package alialicoo.com.dice;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.List;

import org.apache.http.NameValuePair;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.R.bool;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

public class Commdata {
    
//    public final static int versionCode_local = 16;    
//    public static int versionCode_net=0;
//	public static RequestQueue VolleyQueue;
    
    public static Context AppContext=null;
    
    public static  List<ListDownbean> downdiceslist;
    public final static int maxdices = 9;

    public static long start;
    public static boolean listflag=true;
    public static boolean refsdl=true;
//    public static boolean canplay=true;
    public static boolean autocoveredflag=false;
    public final static String get_phone_loaction_servies_addr="http://j.maxmind.com/app/geoip.js";
    public final static String svraddr="http://0.happydice.duapp.com/";
    public final static String pstaddr = svraddr+"android_pst.php";
    public final static String pstaddr_exit = svraddr+"android_dr.php";
    public final static String pstaddr_count = svraddr+"dc_rank_count.php";
    public final static String getaddr_userrat=svraddr+"getuserrat.php";
    public final static String getdc_downlist_addr=svraddr+"dc_dwnlist.php";
    public final static String update_addrs=svraddr+"dice_update.php";
    public final static String user_log_addrs=svraddr+"input.php";
    public static String apkdownloadaddrs="";
     
    public static Location publiclm; 
  
    public static boolean helpshowflag=false;
    public static float userrat_f=0;
    
    public static String MD5ID = null;
    
    public static String dbpath = "/data/data/alialicoo.com.dice/databases/";
    public static String dbname = "dicedb.db";
    public static int screenHeight = 0;
    public static int screenWidth = 0;
    public static boolean exit = false;

    public static List<NameValuePair> postparams;
    public static List<NameValuePair> postparams_exit;
    public static List<dice_bean2> dices;

    public static String return_str;

	public static String Locale_Country;

    

//    public static  void InitVolleyQueue()
//    {
//    	 if( Commdata.VolleyQueue==null)
//         {
//      	   Commdata.VolleyQueue=Volley.newRequestQueue(  Commdata.AppContext);
//         }
//    }

    public static void  readSP() {
        SharedPreferences sp =  Commdata.AppContext.getSharedPreferences("data", 0);
        Commdata.helpshowflag = sp.getBoolean("helpshowflag", false);
        Commdata.autocoveredflag = sp.getBoolean("autocovered", false);
        Commdata.userrat_f = sp.getFloat("userat", 0f);
    }
   
    public static void copydbfile() {
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
        	return;
        }
    }
    
    public static void read_dice_db() {
        dicedb db = new dicedb();
        db.openDatabase();
        db.loadAll(Commdata.dices);
        db.close();
    }
    
    public static void delad_sdcard() {
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
}
