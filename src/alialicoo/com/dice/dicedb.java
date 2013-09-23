package alialicoo.com.dice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class dicedb {

    private SQLiteDatabase cmdb = null;

    public int openDatabase() {

        String dbf = Commdata.dbpath + Commdata.dbname;
        // cmdb = SQLiteDatabase.openOrCreateDatabase(dbf, null);
        cmdb = SQLiteDatabase.openDatabase(dbf, null, SQLiteDatabase.OPEN_READWRITE);
        if (null != cmdb) {
            // createTable();
            return 0;
        }
        return -1;
    }

    private void createTable() {
        if (null == cmdb) {
            return;
        }
        String DICE_TABLE = "create table if not exists DICE_TABLE(" + "DICE_ID varchar(50)," + "TYEP INTEGER ,"
                + "W INTEGER ," + "H INTEGER ," + "icon BLOB ," + "bmp1 BLOB ," + "bmp2 BLOB ," + "bmp3 BLOB ,"
                + "RANK INTEGER," + "COUNT INTEGER)";
        cmdb.execSQL(DICE_TABLE);
    }

    public void insertDice(String DICE_ID,int TYEP,
            Bitmap icon,Bitmap bmp1,int DICECOUNT) {
        try {
            ContentValues values = new ContentValues();
            values.put("DICE_ID", DICE_ID);
            values.put("TYPE", TYEP);
            values.put("icon", getIconData(icon));
            values.put("bmp1", getIconData(bmp1));
            values.put("DICECOUNT", DICECOUNT);
            cmdb.insert("DICE_TABLE", null, values);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public void delDice(String DICE_ID){
        try{
            cmdb.delete("DICE_TABLE", "DICE_ID=?", new String[]{DICE_ID});
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public void updatecount(String DICE_ID){
        try {
            
           String sql=" UPDATE DICE_TABLE SET USECOUNT = USECOUNT+1  WHERE DICE_ID='"+
                   DICE_ID+"'";
            cmdb.execSQL(sql);
            
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void loadAll(List<dice_bean2> dl) {
//        Cursor cur = cmdb.query("DICE_TABLE", new String[] {"*"}, "DICE_ID='1'", null, null, null, null);
        Cursor cur = cmdb.query("DICE_TABLE", new String[] {"*"}, null, null, null, null, null);        
        fulldicebean(cur, dl);
    }

    public void close() {
        cmdb.close();
    }

    private byte[] getIconData(Bitmap bitmap) {
        // int size = bitmap.getWidth()*bitmap.getHeight()*4;
        // ByteArrayOutputStream out = new ByteArrayOutputStream(size);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

        } catch (IOException e) {
            return null;
        }
        return out.toByteArray();
    }

    public void fulldicebean(Cursor c, List<dice_bean2> dl) {
        int n = c.getCount();
        int i = 0;
        byte[] data;
        dl.clear();
        for (i = 0; i < n; i++) {
            c.moveToPosition(i);
            dice_bean2 md = new dice_bean2();
            try {
                md.DICE_ID = c.getString(c.getColumnIndex("DICE_ID"));
                md.type = c.getInt(c.getColumnIndex("TYPE"));
                data = c.getBlob(c.getColumnIndex("icon"));
                md.icon = BitmapFactory.decodeByteArray(data, 0, data.length);
                data = null;
                data = c.getBlob(c.getColumnIndex("bmp1"));
                md.dsimgs[0] = BitmapFactory.decodeByteArray(data, 0, data.length);
//                md.rank = c.getInt(c.getColumnIndex("RANK"));
//                md.use_count = c.getInt(c.getColumnIndex("USECOUNT"));
                md.setDice_count(c.getInt(c.getColumnIndex("DICECOUNT")));
                md.DICEMD5=c.getString(c.getColumnIndex("DICEMD5"));
                dl.add(md);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private Bitmap getIconFromCursor(Cursor c, String key, int n) {
        try {
            c.moveToPosition(n);
            byte[] data = c.getBlob(c.getColumnIndex(key));
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (Exception e) {
            return null;
        }
    }

}
