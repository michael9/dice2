package alialicoo.com.dice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Add_page extends Activity {
    ListView dslst = null;
    DslistAdapter dadapter;
    int dwnlistn = -1;

    // ProgressDialog mpDialog=null;
    // PopupWindow progressdlg=null;
    // TextView progress_title = null;
    // Dialog td = null;
    // SurfaceView surfview=null;
    // SurfaceHolder surfholder=null;
    // boolean df=false;
    // AnimationDrawable animatorD = null;
    /*
     * Handler mhandler = new Handler() {
     * 
     * @Override public void handleMessage(Message msg) { // TODO Auto-generated
     * method stub super.handleMessage(msg);
     * 
     * switch (msg.what) { case 1: td.cancel(); // mpDialog.cancel(); //
     * CloseProgressDlg(); break; case 2:
     * 
     * break;
     * 
     * default: break; } }
     * 
     * };
     */
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        switch (requestCode) {
        case 1:
            if (resultCode == 1) {
                add2dices(dwnlistn);
            }
            break;
        case 2:
            if (Commdata.downdiceslist != null) {
                setlist();
            } else {
                finish();
            }
            break;

        default:
            break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        // LayoutInflater mLayoutInflater = (LayoutInflater) Commdata.AppContext
        // .getSystemService(LAYOUT_INFLATER_SERVICE);
        // View progressdlg_view = mLayoutInflater.inflate(R.layout.myprogress,
        // null);
        // progressdlg=new
        // PopupWindow(progressdlg_view,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        // progress_title=(TextView)findViewById(R.id.myprogressdlg_title);
        // progress_title.setText("获取骰子中...");
        // progressdlg.showAtLocation(findViewById(R.id.add_page_main),
        // Gravity.CENTER, 0, 0);
        //

        // mpDialog = new ProgressDialog(Add_page.this);
        // mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // mpDialog.setContentView(R.layout.myprogress);
        // mpDialog.setCancelable(true);
        // mpDialog.setTitle("获取骰子中...");
        // mpDialog.setOnCancelListener(new OnCancelListener() {
        //
        // @Override
        // public void onCancel(DialogInterface dialog) {
        // // TODO Auto-generated method stub
        // if( Commdata.downdiceslist!=null)
        // {
        // setlist();
        // }
        // else{
        // finish();
        // }
        // }
        // });

        dslst = (ListView) findViewById(R.id.dslst);
        dslst.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                // Toast.makeText(Add_page.this,
                // "下载"+Commdate.downdiceslist.get(arg2).ds_name, 1500).show();
                dwnlistn = -1;
                if (Commdata.downdiceslist != null) {
                    dwnlistn = arg2;
                    Intent intent = new Intent(Add_page.this, CMSGDlg.class);
                    intent.putExtra("msg_id", R.string.msg_download_the_dice);
                    startActivityForResult(intent, 1);
                }
            }
        });

        // LoadinfoAsy la=new LoadinfoAsy();
        // la.execute(null);
        // mpDialog.show();

        // LayoutInflater mLayoutInflater = getLayoutInflater();
        // View layout =mLayoutInflater.inflate(R.layout.myprogress,
        // (ViewGroup)findViewById(R.id.add_page_main));

        // td= new Dialog(this,android.R.style.Theme_Dialog);
        // td.setContentView(R.layout.myprogress);
        // td.setCancelable(true);
        // td.setOnCancelListener(new OnCancelListener() {
        //
        // @Override
        // public void onCancel(DialogInterface dialog) {
        // // TODO Auto-generated method stub
        // if( Commdata.downdiceslist!=null)
        // {
        // setlist();
        // }
        // else{
        // finish();
        // }
        // }
        // });
        //
        //
        // td.setOnShowListener(new OnShowListener() {
        //
        // @Override
        // public void onShow(DialogInterface dialog) {
        // // TODO Auto-generated method stub
        // // df=true;
        // // surfview=(SurfaceView)findViewById(R.id.surfview);
        // }
        // });
        //
        // td.show();

        if (Commdata.downdiceslist == null) {
//            Intent intent = new Intent(Add_page.this, MyProgressDlg.class);
//            startActivityForResult(intent, 2);
        } else {
            setlist();
        }

        // LoadinfoTh lt=new LoadinfoTh();
        // lt.start();
    }

    // void CloseProgressDlg(){
    // progressdlg.dismiss();
    // }

    void add2dices(int select) {
        if (dwnlistn < 0)
            return;

        if (!finddc(Commdata.downdiceslist.get(select).DICE_ID)) {
            dicedb db = new dicedb();
            db.openDatabase();
            db.insertDice(Commdata.downdiceslist.get(select).DICE_ID, Commdata.downdiceslist.get(select).type,
                    Commdata.downdiceslist.get(select).icon, Commdata.downdiceslist.get(select).image, 6);
            db.close();

            dice_bean2 mdb2 = new dice_bean2();
            mdb2.DICE_ID = Commdata.downdiceslist.get(select).DICE_ID;
            Commdata.dices.add(mdb2);

//            List<NameValuePair> mp = new ArrayList<NameValuePair>();
//            mp.add(new BasicNameValuePair("DICE_ID", Commdata.downdiceslist.get(select).DICE_ID));
//            mp.add(new BasicNameValuePair("MD5ID", Commdata.MD5ID));
//            mp.add(new BasicNameValuePair("OPTION", "dc_dwn_cnt"));

//            thrgetinfo mthr = new thrgetinfo(mp, Commdata.pstaddr_count);
//            mthr.start();

            Commdata.refsdl = true;
            Toast.makeText(Add_page.this, R.string.msg_get_the_dice, 1500).show();
        } else {
            Toast.makeText(Add_page.this, R.string.msg_had_the_dice, 1500).show();
        }
    }

    void setlist() {
        try {
            dadapter = new DslistAdapter();
            dslst.setAdapter(dadapter);
        } catch (Exception e) {
            // TODO: handle exception
            return;
        }
    }

    boolean finddc(String DICE_ID) {

        for (dice_bean2 db2 : Commdata.dices) {
            if (db2.DICE_ID.equalsIgnoreCase(DICE_ID))
                return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.activity_slide_r2l, R.anim.activity_slide_l2r);
        }
        // return super.onKeyDown(keyCode, event);
        return false;
    }

    /*
     * class LoadinfoTh extends Thread {
     * 
     * @Override public void run() { // TODO Auto-generated method stub
     * super.run();
     * 
     * // Message mst=new Message(); // mst.what=2; //
     * mhandler.sendMessage(mst);
     * 
     * hget get = new hget(); String bstr =
     * get.getcontext(Commdata.getdc_downlist_addr);
     * 
     * // surfholder=surfview.getHolder(); // Paint mPaint = new Paint(); //
     * mPaint.setColor(Color.BLUE); // Canvas mc= surfholder.lockCanvas(); //
     * mc.drawRect(new RectF(30,10,130,65), mPaint); //
     * surfholder.unlockCanvasAndPost(mc);//解锁画布，提交画好的图像
     * 
     * Commdata.downdiceslist =
     * MyPullXMLReader.readXML(MyUtil.String2InputStream(bstr)); if
     * (Commdata.downdiceslist != null) { Message ms = new Message(); ms.what =
     * 1; mhandler.sendMessage(ms); } } }
     */
    /*
     * class LoadinfoAsy extends AsyncTask{
     * 
     * @Override protected Object doInBackground(Object... params) { // TODO
     * Auto-generated method stub //第二个执行方法,onPreExecute()执行完后执行 hget get=new
     * hget(); String bstr=
     * get.getcontext("http://10.130.43.99/test_cr_xml2.php?adb=8");
     * Commdata.downdiceslist
     * =MyPullXMLReader.readXML(MyUtil.String2InputStream(bstr)); if(
     * Commdata.downdiceslist!=null){ setlist(); } return null; }
     * 
     * @Override protected void onCancelled() { // TODO Auto-generated method
     * stub super.onCancelled(); }
     * 
     * @Override protected void onPostExecute(Object result) { // TODO
     * Auto-generated method stub
     * //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
     * //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕" mpDialog.cancel();
     * super.onPostExecute(result); }
     * 
     * @Override protected void onPreExecute() { // TODO Auto-generated method
     * stub //第一个执行方法 mpDialog.show(); super.onPreExecute(); }
     * 
     * @Override protected void onProgressUpdate(Object... values) { // TODO
     * Auto-generated method stub
     * //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
     * //但是这里取到的是一个数组,所以要用progesss[0]来取值 super.onProgressUpdate(values); } }
     */
}
