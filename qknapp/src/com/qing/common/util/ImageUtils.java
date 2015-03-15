/*
 * Title:        清清网系统 2014年10月3日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月3日
 */
package com.qing.common.util;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

/**
 * 图片工具
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月3日
 */
public class ImageUtils {

	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	/**
	 * 把图片缩放剪裁到指定大小(默认弄成png格式)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param srcFilePath
	 * @param destFilePath
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static boolean resizer(String srcFilePath, String destFilePath, int newWidth, int newHeight) {
		File srcFile = new File(srcFilePath);
		boolean isSuccess = false;
		if (srcFile.exists()) {
			isSuccess = resizer(srcFile, destFilePath, newWidth, newHeight);
			return isSuccess;
		} else {
			logger.error("file does not exists! srcFilePath=" + srcFilePath);
			return isSuccess;
		}
	}

	/**
	 * 把图片缩放剪裁到指定大小(默认弄成png格式)
	 * 
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param srcFile
	 * @param destFilePath
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static boolean resizer(File srcFile, String destFilePath, int newWidth, int newHeight) {
		try {
			BufferedImage source = ImageIO.read(srcFile);
			ImageResizer resizer = new ImageResizer(source);
			resizer.setTargetSize(newWidth, newHeight);
			resizer.setUpscale(true);
			resizer.setCrop(true);
			resizer.setCropPosition(0);
			BufferedImage result = resizer.resize();
			ImageIO.write(result, "PNG", new File(destFilePath));
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 根据EXIF信息调整图片方向
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月14日
	 * @param srcFile
	 * @param destFilePath
	 * @return
	 */
	public static boolean rotatorByEXIF(File srcFile, String destFilePath) {
		if (!srcFile.exists()) {
			logger.error("srcFile does not exist. ImageUtils.rotator()");
			return false;
		}
		int degree = 0;
		int flip = 0;
		int orientation = 1;
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(srcFile);
			Directory directory = metadata.getDirectory(ExifIFD0Directory.class);
			if (directory != null) {
				orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		switch (orientation) {
		case 1:
			degree = 0;
			flip = 0;
			break;
		case 2:
			degree = 0;
			flip = -1;
			break;
		case 3:
			degree = 180;
			flip = 0;
			break;
		case 4:
			degree = 0;
			flip = 1;
			break;
		case 5:
			degree = 90;
			flip = -1;
			break;
		case 6:
			degree = 90;
			flip = 0;
			break;
		case 7:
			degree = 270;
			flip = -1;
			break;
		case 8:
			degree = 270;
			flip = 0;
			break;
		default:
			break;
		}
		if (degree != 0 || flip != 0) {
			try {
				transformer(degree, flip, srcFile, destFilePath);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		}
		return true;
	}

	/**
	 * 图像变换（包括旋转和翻转）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月14日
	 * @param degree 旋转角度
	 * @param flip 翻转方向（-1为以垂直线为轴翻转，1为水平线为轴翻转，0为不翻转）
	 * @param srcFile
	 * @param destPath
	 * @throws IOException
	 */
	public static void transformer(int degree, int flip, File srcFile, String destPath) throws IOException {
		BufferedImage source = ImageIO.read(srcFile);
		int w;
		int h;
		if ((degree / 90) % 2 == 1) {
			w = source.getHeight();
			h = source.getWidth();
		} else {
			w = source.getWidth();
			h = source.getHeight();
		}
		BufferedImage rotatedImage = new BufferedImage(w, h, source.getType());
		Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
		rotatedImage = gs.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		AffineTransform at = new AffineTransform();
		if (degree != 0) {
			at.rotate(Math.toRadians(degree), 0, 0);//旋转图象
			switch (degree) { //移动到画布正确的位置
			case 90:
				at.translate(0, -source.getHeight());
				break;
			case 180:
				at.translate(-source.getWidth(), -source.getHeight());
				break;
			case 270:
				at.translate(-source.getWidth(), 0);
				break;
			default:
				break;
			}
		}
		if (flip != 0) { //如果需要翻转
			if (flip == -1) { //垂直线镜面
				at.scale(-1, 1);
				at.translate(-w, 0);
			}
			if (flip == 1) { //水平线镜面
				at.scale(1, -1);
				at.translate(0, -h);
			}
		}
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(source, rotatedImage);
		ImageIO.write(rotatedImage, "PNG", new File(destPath));
	}

	public static void main(String[] args) {
		//		resizer("D:/Program Files (x86)/VertrigoServ/www/images/memberImage/500d09f197e23_200x200_3.gif",
		//				"D:/Program Files (x86)/VertrigoServ/www/images/memberImage/2.png", 75, 75);
		File file = new File("D:/Program Files (x86)/VertrigoServ/www/images/memberImage/test.JPG");
		rotatorByEXIF(file, "D:/Program Files (x86)/VertrigoServ/www/images/memberImage/1.png");
	}

}
