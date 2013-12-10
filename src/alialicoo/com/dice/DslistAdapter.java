package alialicoo.com.dice;

import android.content.Context;
import android.media.ToneGenerator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DslistAdapter  extends BaseAdapter {

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Commdata.downdiceslist.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater mInflater=LayoutInflater.from(Commdata.AppContext) ;
        View item = mInflater.inflate(R.layout.dslst_layout, null);
//        ImageView img = (ImageView)item.findViewById(R.id.) 
        
     
        if(position%2!=0)
        {
            item.setBackgroundResource(R.drawable.b03);
        }
        else
        {
            if(Commdata.listflag)
            {
            item.setBackgroundResource(R.drawable.b04);
            }
            else
            {
                item.setBackgroundResource(R.drawable.b02);
            }
            Commdata.listflag=  !Commdata.listflag;           
        }
        
        TextView dcid = (TextView)item.findViewById(R.id.dcid);
        TextView dctype = (TextView)item.findViewById(R.id.dctype);
        TextView author = (TextView)item.findViewById(R.id.author);
        TextView dc_name = (TextView)item.findViewById(R.id.dc_name);
        RatingBar rb=(RatingBar)item.findViewById(R.id.dcrb);
        ImageView dcicon=(ImageView)item.findViewById(R.id.dcicon);
        ImageView dcimg=(ImageView)item.findViewById(R.id.dcimg);
    
//        Button btn_info=(Button)item.findViewById(R.id.btn_info);
//        Button btn_dwn=(Button)item.findViewById(R.id.btn_dwn);
        
        dcid.setText(Commdata.downdiceslist.get(position).DICE_ID);
        dc_name.setText(Commdata.downdiceslist.get(position).ds_name);
        author.setText(Commdata.downdiceslist.get(position).author);
        
        switch (Commdata.downdiceslist.get(position).type) {
        case 0:
            dctype.setText("普通");
            break;

        default:
            dctype.setText("其他");
            break;
        }
        
        rb.setRating(Commdata.downdiceslist.get(position).rank);
        dcicon.setImageBitmap(Commdata.downdiceslist.get(position).icon);
        dcimg.setImageBitmap(Commdata.downdiceslist.get(position).image);
//        btn_info.setOnClickListener(new ItemviewButtonListener(position));
//        btn_dwn.setOnClickListener(new ItemviewButtonListener(position));
        return item;
    }
    /*
    class ItemviewButtonListener implements OnClickListener {
        private int position;

        ItemviewButtonListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String str="";
            switch (v.getId()) {
            case R.id.btn_info:
                str=position+" 详情 "+Commdate.downdiceslist.get(position).DICE_ID;
                break;
            case R.id.btn_dwn:
                str=position+" 下载 "+Commdate.downdiceslist.get(position).DICE_ID;
                break;

            default:
                break;
            }
         
            Toast.makeText(Commdate.mc, str, Toast.LENGTH_LONG).show();
        }
    }
*/
}
