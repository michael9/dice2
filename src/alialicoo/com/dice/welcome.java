package alialicoo.com.dice;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class welcome extends Activity {

	int n=3;
	
	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 90:
				welcome.this.finish();
				break;

			}
		}
	};
	Timer tt=new Timer();
	TimerTask task = new TimerTask() {   
		public void run() {   
		 n--;
		 if(n==0)
		 {
			 handler.sendEmptyMessage(90);
		 }
		}   
		}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		tt.schedule(task,0, 1000);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tt.cancel();
	}
}