package alialicoo.com.dice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CMSGDlg extends Activity {

    TextView msgtxt;
    Button btn_ok, btn_cancel;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);// 背景变昏暗
        setContentView(R.layout.exitdlg);
        
//        String msg=getIntent().getStringExtra("msg");
        int msg_id=getIntent().getIntExtra("msg_id", -1);
                
        msgtxt=(TextView)findViewById(R.id.msgtxt);
        if(msg_id>0)
            msgtxt.setText(msg_id);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok.setOnClickListener(uiclick);
        btn_cancel.setOnClickListener(uiclick);

    }

    private void exit() {
        this.setResult(flag);
        finish();
    }

    private OnClickListener uiclick = new OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
            case R.id.btn_ok:
                flag = 1;
                break;
            case R.id.btn_cancel:
                flag = 0;
                break;
            default:
                flag = 0;
                break;
            }
            exit();
        }
    };
}
