package alialicoo.com.dice;

import android.graphics.Bitmap;

public class ListDownbean {

    public String DICE_ID;
    public String author;
    public String ds_name;
    private String icon_addr;
    private String image_addr;
    public int type = 0;  
    public int rank; 
    public Bitmap icon=null;
    public Bitmap image=null;
    
    public void setIcon_addr(String icon_addr) {
        this.icon_addr = icon_addr;
//        hgimage hi=new hgimage();
//        icon=hi.getimg(icon_addr);
//        hi=null;
    }
    
    public void setImage_addr(String image_addr) {
        this.image_addr = image_addr;
//        hgimage hi=new hgimage();
//        image=hi.getimg(image_addr);
//        hi=null;
    }
      
    
}
