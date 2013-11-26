package alialicoo.com.dice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Commdata.dices.size()+1;
    }

    @Override
    public Object getItem(int position) {
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
        ImageView imageview;
        if (convertView == null) {
            imageview = new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(96, 96));
            imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageview.setPadding(2, 2, 2, 2);
        } else {
            imageview = (ImageView) convertView;
        }
        if(position>=Commdata.dices.size())
        {
            imageview.setImageResource(R.drawable.add_icon);
        }
        else
        {
        imageview.setImageBitmap(Commdata.dices.get(position).icon);
        }
        return imageview;
    }
}
