package alialicoo.com.dice;

import java.util.ArrayList;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

import android.support.v4.widget.DrawerLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;



public class MainActivity extends Activity {
	
	class getPhoneinfoTh extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			PhoneInfo mph = new PhoneInfo();
			mph.getPhoneInfoParams(Commdata.AppContext);
		}
	}

	class readDBTh extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Commdata.copydbfile();
			Commdata.read_dice_db();
			handler.sendEmptyMessage(100);
		}
	}

	Handler handler = new Handler() {

	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        switch (msg.what) {
	        case 100:
	        	ini_ds();
	        	break;
	        
	        }
	    }
	};
	
	SensorManager mSensorManager;// 声明一个SensorManager
	Sensor accSensor = null;
//	SlidingDrawer sd = null;
	GridView mgv = null;
	DrawerLayout dl=null; 
	ListView menu_listview=null;
	 public static final String[] TITLES =
		    { "Henry IV (1)", "Henry V", "Henry VIII", "Richard II", "Richard III", "Merchant of Venice", "Othello", "King Lear" };
	dice_view_bean[] dices_view = new dice_view_bean[Commdata.maxdices];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Commdata.AppContext = this;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);
		accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	
		Commdata.InitVolleyQueue();
		Commdata.readSP();
		getPhoneinfoTh gpth = new getPhoneinfoTh();
		gpth.start();

		Commdata.dices = new ArrayList<dice_bean2>();
//		readDBTh rdbth = new readDBTh();
//		rdbth.start();
		
		Commdata.copydbfile();
		Commdata.read_dice_db();
		
		  int i;
	        int dicesize = (Commdata.screenHeight - 30) / 3;
	        for (i = 0; i < Commdata.maxdices; i++) {
	            dices_view[i] = new dice_view_bean();
	            dices_view[i].dice_w = dicesize;
	            dices_view[i].dice_h = dicesize;
	        }
		findViews();
		ini_ds();
		setgrid();
	}

	void ini_ds() {
		int i;
		for (i = 0; i < Commdata.maxdices; i++) {
			dices_view[i].div.setVisibility(8);
		}
		if (Commdata.dices.size() > 0)
			if (Commdata.dices.get(0) != null) {
				dices_view[0].mbean2 = Commdata.dices.get(0);
				dices_view[0].setRandoem(4);
				dices_view[0].setdiceimage();
			}
	}

	private void findViews() {
	dl=(DrawerLayout)findViewById(R.id.drawer_layout);

	 // Set the adapter for the list view
//	 menu_listview.setAdapter(new ArrayAdapter<String>(this,
//            R.layout.drawer_list_item, mPlanetTitles));
  
    
		// main_ll = (LinearLayout) findViewById(R.id.main_ll);
		// main_ll.setDrawingCacheEnabled(true);
		// tplayout = (RelativeLayout) findViewById(R.id.tplayout);
		// tplayout.setDrawingCacheEnabled(true);
		// dplayout = (RelativeLayout) findViewById(R.id.dplayout);
		mgv = (GridView) findViewById(R.id.dice_slist);
//		sd = (SlidingDrawer) findViewById(R.id.sd_dices);
//		sd.open();
		// helpbutton = (ImageButton) findViewById(R.id.helpbutton);
		// camerashare = (ImageView) findViewById(R.id.camerashare);
		// tableashare = (ImageView) findViewById(R.id.tableshare);
		// mIV = (ImageView) findViewById(R.id.iv);
		// mIV.setBackgroundResource(R.anim.dice_anim);
		// animator = (AnimationDrawable) mIV.getBackground();
		// mIV.setVisibility(8);
		// usrerat = (RatingBar) findViewById(R.id.usrerat);
		dices_view[0].div = (ImageView) findViewById(R.id.div1);
		dices_view[1].div = (ImageView) findViewById(R.id.div2);
		dices_view[2].div = (ImageView) findViewById(R.id.div3);
		dices_view[3].div = (ImageView) findViewById(R.id.div4);
		dices_view[4].div = (ImageView) findViewById(R.id.div5);
		dices_view[5].div = (ImageView) findViewById(R.id.div6);
		dices_view[6].div = (ImageView) findViewById(R.id.div7);
		dices_view[7].div = (ImageView) findViewById(R.id.div8);
		dices_view[8].div = (ImageView) findViewById(R.id.div9);
		// mainmenu_btn = (ImageButton) findViewById(R.id.mainmenu_btn);
		// addsc = (TextView) findViewById(R.id.addsc);
		// Ad
		setCommondListener();
	}

	private void setCommondListener() {
		
		  dices_view[0].div.setOnLongClickListener(new Uilclicked());
		  dices_view[1].div.setOnLongClickListener(new Uilclicked());
		  dices_view[2].div.setOnLongClickListener(new Uilclicked());
		  dices_view[3].div.setOnLongClickListener(new Uilclicked());
		  dices_view[4].div.setOnLongClickListener(new Uilclicked());
		  dices_view[5].div.setOnLongClickListener(new Uilclicked());
		  dices_view[6].div.setOnLongClickListener(new Uilclicked());
		  dices_view[7].div.setOnLongClickListener(new Uilclicked());
		  dices_view[8].div.setOnLongClickListener(new Uilclicked());
		  
		  dices_view[0].div.setOnClickListener(new UIclicked());
		  dices_view[1].div.setOnClickListener(new UIclicked());
		  dices_view[2].div.setOnClickListener(new UIclicked());
		 dices_view[3].div.setOnClickListener(new UIclicked());
		 dices_view[4].div.setOnClickListener(new UIclicked());
		  dices_view[5].div.setOnClickListener(new UIclicked());
		  dices_view[6].div.setOnClickListener(new UIclicked());
		  dices_view[7].div.setOnClickListener(new UIclicked());
		  dices_view[8].div.setOnClickListener(new UIclicked());
		
		  mgv.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				  dl.closeDrawers();
			  };
		});
		  /*
		  mgv.setOnItemLongClickListener(new OnItemLongClickListener() {
		  
		  @Override public boolean onItemLongClick(AdapterView<?> arg0, View
		  arg1, int arg2, long arg3) { // TODO Auto-generated method stub if
		  (arg2 < Commdata.dices.size()) { delseln = arg2; Intent intent = new
		  Intent(Dice.this, CMSGDlg.class); intent.putExtra("msg_id",
		  R.string.msg_give_up_the_dice); startActivityForResult(intent, 3); }
		  return false; } });
		  
		  tplayout.setOnClickListener(new UIclicked());
		  helpbutton.setOnClickListener(new UIclicked()); //
		  camerashare.setOnClickListener(new UIclicked()); //
		  tableashare.setOnClickListener(new UIclicked());
		  sd.setOnDrawerOpenListener(new OnDrawerOpenListener() {
		  
		  @Override public void onDrawerOpened() { // TODO Auto-generated
		  method stub mgv.startLayoutAnimation(); } });
		  mainmenu_btn.setOnClickListener(new UIclicked()); // Ad //
		  adviewbar.setOnClickListener(new adclick());
		 */
	}

    void setgrid() {
//        Commdata.refsdl = false;
//        Commdata.read_dice_db();
        mgv.setAdapter(new ImageAdapter(this));
    }
	/******************************************************************************/
    class Uilclicked implements OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub
            switch (view.getId()) {
            case R.id.div1:
                dices_view[0].div.setVisibility(8);
                break;
            case R.id.div2:
                dices_view[1].div.setVisibility(8);
                break;
            case R.id.div3:
                dices_view[2].div.setVisibility(8);
                break;
            case R.id.div4:
                dices_view[3].div.setVisibility(8);
                break;
            case R.id.div5:
                dices_view[4].div.setVisibility(8);
                break;
            case R.id.div6:
                dices_view[5].div.setVisibility(8);
                break;
            case R.id.div7:
                dices_view[6].div.setVisibility(8);
                break;
            case R.id.div8:
                dices_view[7].div.setVisibility(8);
                break;
            case R.id.div9:
                dices_view[8].div.setVisibility(8);
                break;

            default:
                break;
            }

            return true;
        }
    }
    
    class UIclicked implements OnClickListener {
        @Override
        public void onClick(View v) {
         
        }
    };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
