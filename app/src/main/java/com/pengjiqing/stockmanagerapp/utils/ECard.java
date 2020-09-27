package com.pengjiqing.stockmanagerapp.utils;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 */
public class ECard {
	private int width;
	private int height;
	private int type;
	public static int BARCODE=1,TWOCODE=2;
	private Context context;

	/**
	 */
	public ECard(int width,int height,int type,Context context){
		this.width=width;
		this.height=height;
		this.type=type;
		this.context=context;
	}

	/**
	 */
	public Bitmap CreateOneDCode(String content) throws WriterException, UnsupportedEncodingException{
		BarcodeFormat format=null;
		Bitmap bitmap=null;

		boolean is=false;
		if (type==1) {
			format=BarcodeFormat.CODE_128;
		}else if(type==2){
			format=BarcodeFormat.QR_CODE;
		}
		for (int i = 0; i < content.length()&&!is; i++) {
			char s=content.charAt(i);
			if ((19968 <= s && s < 40623)) {
				is=true;
				break;
			}
		}
		String info=null;
		if (is&&type==2) {
			info=new String(content.getBytes("UTF-8"),"ISO-8859-1");
			bitmap=getBitmap(info, format, width, height);
		}else if (!is) {
			bitmap=getBitmap(content, format, width, height);
		}else if (is&&type==1) {
			Toast.makeText(context, "无法生成二维码",Toast.LENGTH_LONG).show();
		}
		return bitmap;
	}

	public Bitmap getBitmap(String info, BarcodeFormat format ,int width1,int height1) throws WriterException{
		BitMatrix matrix=new MultiFormatWriter().encode(info, format, width1, height1);
		int width=matrix.getWidth();
		int height=matrix.getHeight();
		int[] pex=new int[width*height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix.get(j, i)) {
					pex[i*width+j]=0xff000000;
				}
			}
		}

		Bitmap bitmap=Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pex, 0,width, 0, 0, width, height);

		return bitmap;
	}
}
