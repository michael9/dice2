package alialicoo.com.dice;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class dice_view_bean {
    public ImageView div = null;
    private int randoem = 0;
    public dice_bean2 mbean2 = null;
    public int dice_w = 0;
    public int dice_h = 0;

    public void setdiceimage() {
        int mr = randoem % mbean2.getDice_count();
        div.setImageBitmap(mbean2.getimage(mr));
        div.setVisibility(0);
    }

    public int getRandoem() {
        return randoem;
    }

    public void setRandoem(int randoem) {
        this.randoem = randoem;
        if (div.isShown())
            setdiceimage();
    }

    public Bitmap getMdiceimage() {
        div.setDrawingCacheEnabled(true);
        return ImageCS.zoomBitmap(div.getDrawingCache(), 48);
    }

    private void setdicesize(int w, int h) {
        if (div != null) {
            RelativeLayout.LayoutParams mrl = (RelativeLayout.LayoutParams) div.getLayoutParams();
            mrl.width = w;
            mrl.height = h;
            div.setLayoutParams(mrl);
            div.invalidate();
        }
    }

}
