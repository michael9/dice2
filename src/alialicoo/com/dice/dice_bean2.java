package alialicoo.com.dice;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class dice_bean2 {

    public String DICE_ID;
    public Bitmap[] dsimgs = new Bitmap[3];
    public Bitmap[] dsimgs_cut;
    public int type = 0;
    public Bitmap icon;
//    public int rank;
//    public int use_count;
    private  int dice_count;
    public String DICEMD5;

    private int randoem = 0;
    private ImageView div = null;

    
    
    public int getDice_count() {
        return dice_count;
    }



    public void setDice_count(int dice_count) {
        this.dice_count = dice_count;
        dsimgs_cut=new Bitmap[dice_count];
        int i=0;
        for(i=0;i<dice_count;i++){
            dsimgs_cut[i]=cutimage(i);
        }
    }

    public Bitmap getimage(int i){
        return dsimgs_cut[i];
    }


    private Bitmap cutimage(int i) {
        Bitmap mb = null;
        try {
            int mw, mh;
            mw = dsimgs[0].getWidth();
            mh = dsimgs[0].getHeight();
            int w = mw / dice_count;
            int h = mh;
            mb = Bitmap.createBitmap(dsimgs[0], i * w, 0, w, h);
        } catch (Exception e) {
            return null;
        }
        return mb;
    }

}
