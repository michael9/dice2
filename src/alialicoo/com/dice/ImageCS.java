package alialicoo.com.dice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class ImageCS {

    public static String sFileFullPath = null;
    public static String sFileFullPath_t=null;

    public static Bitmap zoomBitmap(Bitmap bitmap, int w) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scale = (float) w / width;
        matrix.postScale(scale, scale);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int r) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(r, width / 2, height / 2);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    public static void deal_img(String filefullpath) {
        try {

            Bitmap mainbit = BitmapFactory.decodeFile(filefullpath);
            int width = mainbit.getWidth();
            int height = mainbit.getHeight();
            Matrix matrix = new Matrix();

            if (height > width)
                mainbit = rotateBitmap(mainbit, -90);

            if (width > 800)
                mainbit = zoomBitmap(mainbit, 800);

            bitmap2file(mainbit, filefullpath);

            mainbit.recycle();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static Bitmap add_dice_img(String filefullpath, Bitmap[] bms) {
        Bitmap dbit = null;
        try {
            int i = 0;
            int x = 3;
            int y = 0;
            Paint bitmappaint = new Paint();
            Bitmap mainbit = BitmapFactory.decodeFile(filefullpath);
            Bitmap bar = BitmapFactory.decodeResource(Commdata.AppContext.getResources(), R.drawable.bar01);
            Bitmap zy = BitmapFactory.decodeResource(Commdata.AppContext.getResources(), R.drawable.zy);
            dbit = Bitmap.createBitmap(mainbit.getWidth(), mainbit.getHeight(), Config.RGB_565);
            // y=mainbit.getHeight()-55;
            Canvas cv = new Canvas(dbit);
            cv.drawBitmap(mainbit, 0, 0, bitmappaint);
            cv.drawBitmap(bar, 0, 0, bitmappaint);
            cv.drawBitmap(zy, 320, 180, bitmappaint);
            for (i = 0; i < 9; i++) {
                if (bms[i] != null) {
                    cv.drawBitmap(bms[i], x, 6, bitmappaint);
                    x += 50;
                }
            }
            cv.save(Canvas.ALL_SAVE_FLAG);// 保存
            cv.restore();// 存储

        } catch (Exception e) {
            // TODO: handle exception

        }
        return dbit;
    }

    public static void bitmap2file(Bitmap imb, String filefullpath) {
        try {
            File myCaptureFile = new File(filefullpath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            imb.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
   
    
    
    // 获得圆角图片
    /*
     * public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
     * { int w = bitmap.getWidth(); int h = bitmap.getHeight(); Bitmap output =
     * Bitmap.createBitmap(w, h, Config.ARGB_8888); Canvas canvas = new
     * Canvas(output); final int color = 0xff424242; final Paint paint = new
     * Paint(); final Rect rect = new Rect(0, 0, w, h); final RectF rectF = new
     * RectF(rect); paint.setAntiAlias(true); canvas.drawARGB(0, 0, 0, 0);
     * paint.setColor(color); canvas.drawRoundRect(rectF, roundPx, roundPx,
     * paint); paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
     * canvas.drawBitmap(bitmap, rect, rect, paint); return output; }
     */
    // 获得带倒影的图片
    /*
     * public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
     * final int reflectionGap = 4; int w = bitmap.getWidth(); int h =
     * bitmap.getHeight();
     * 
     * Matrix matrix = new Matrix(); matrix.preScale(1, -1);
     * 
     * Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w, h / 2,
     * matrix, false);
     * 
     * Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
     * Config.ARGB_8888);
     * 
     * Canvas canvas = new Canvas(bitmapWithReflection);
     * canvas.drawBitmap(bitmap, 0, 0, null); Paint deafalutPaint = new Paint();
     * canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);
     * 
     * canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);
     * 
     * Paint paint = new Paint(); LinearGradient shader = new LinearGradient(0,
     * bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap,
     * 0x70ffffff, 0x00ffffff, TileMode.CLAMP); paint.setShader(shader); // Set
     * the Transfer mode to be porter duff and destination in
     * paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // Draw a
     * rectangle using the paint with our linear gradient canvas.drawRect(0, h,
     * w, bitmapWithReflection.getHeight() + reflectionGap, paint);
     * 
     * return bitmapWithReflection; }
     */
}
