/*
 * Title:        清清网系统 2014年10月11日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月11日
 */
package com.qing.common.util;  
  
import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;
  
  
public class MyQRCodeImage implements QRCodeImage {  
  
    BufferedImage bufImg;  
  
    public MyQRCodeImage(BufferedImage bufImg) {  
        this.bufImg = bufImg;  
    }  
  
    @Override  
    public int getHeight() {  
            return bufImg.getHeight();  
    }  
  
    @Override  
    public int getPixel(int x, int y) {  
            return bufImg.getRGB(x, y);  
    }  
  
    @Override  
    public int getWidth() {  
            return bufImg.getWidth();  
    }
}  