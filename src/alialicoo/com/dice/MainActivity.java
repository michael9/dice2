package alialicoo.com.dice;

import java.util.ArrayList;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.WindowManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

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
	}
}

public class MainActivity extends Activity {

	SensorManager mSensorManager;// 声明一个SensorManager
	Sensor accSensor = null;
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
		readDBTh rdbth = new readDBTh();
		rdbth.start();
		 int i;
	        int dicesize = (Commdata.screenHeight - 30) / 3;
	        for (i = 0; i < Commdata.maxdices; i++) {
	            dices_view[i] = new dice_view_bean();
	            dices_view[i].dice_w = dicesize;
	            dices_view[i].dice_h = dicesize;
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
