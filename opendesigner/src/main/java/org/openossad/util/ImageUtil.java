/*
 * Copyright (c) 2007 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the GNU Lesser General Public License, Version 2.1. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.gnu.org/licenses/lgpl-2.1.txt. The Original Code is Pentaho 
 * Data Integration.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the GNU Lesser Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/
package org.openossad.util;
/*
 * Copyright (c) 2007 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the GNU Lesser General Public License, Version 2.1. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.gnu.org/licenses/lgpl-2.1.txt. The Original Code is Pentaho 
 * Data Integration.  The Initial Developer is Pentaho Corporation.
 *
 * Software distributed under the GNU Lesser Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.
*/

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.openossad.util.vfs.OpenDESIGNERVFS;

import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

public class ImageUtil
{
    private static FileObject base;
    
    static
    {
    	try
    	{
    		base = VFS.getManager().resolveFile(System.getProperty("user.dir"));
    	}
    	catch(FileSystemException e)
    	{
    		e.printStackTrace();
    		base = null;
    	}
    }
    
    
    public static ImageIcon createImageIcon(String location) {
        String file = ImageUtil.class.getResource(location).getPath();
        return new ImageIcon(file,"");

	}
    public static java.awt.Image createImage(String location) {
        return createImageIcon(location).getImage();

    }
    public static java.awt.Image createImageIconWithVFS(String location) {
    	try {
			return new ImageIcon(VFS.getManager().resolveFile(base,location).getURL(),"").getImage();
		} catch (FileSystemException e) {
			throw new RuntimeException("Unable to load image with name ["+location+"]", e);
		}
	}
    public static String createImagePath(String location) {
    	try {
			return VFS.getManager().resolveFile(base,location).getURL().toString();
		} catch (FileSystemException e) {
			throw new RuntimeException("Unable to load image with name ["+location+"]", e);
		}
		
	}
    
    public static Image getImageAsResource(String location) {
    	Display display = new Display();
    	// assume the classloader for the active thread
    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
    	URL res = cl.getResource(location);
		if (res != null) {
			try {
				java.io.InputStream s = res.openStream();
				if (s != null) {
					return new Image(display,s);
				}
			} catch (IOException e) {
				//do nothing. just move on to trying to load via file system
			}
		}
		try {
			FileObject imageFileObject = VFS.getManager().resolveFile(base,location);
			return new Image(display,OpenDESIGNERVFS.getInputStream(imageFileObject));
		} catch (FileSystemException e) {
			throw new RuntimeException("Unable to load image with name ["+location+"]", e);
		}
    }
    
	
    
    public static Image makeImageTransparent(Display display, Image tempImage, RGB transparentColor)
    {
        ImageData imageData = tempImage.getImageData();
        int pixelIndex = imageData.palette.getPixel(transparentColor);
        imageData.transparentPixel = pixelIndex;
        Image image = new Image(display, imageData);
        tempImage.dispose();       
        return image;
    }
    
    
    
    public static Image getImage(Display display,String location)
    {	
    	// TODO: find other instances of getImage (plugin, steps) and transition them to new genericListModelHibernate_1 through an laf manager
    	try
    	{
    		return new Image(display, OpenDESIGNERVFS.getInputStream(location));
    		
    	}
    	catch(Exception e)
    	{
    		try
    		{
    			return new Image(display,ImageUtil.class.getClassLoader().getResourceAsStream(location));
    		}
    		catch(Exception npe)
    		{
    			throw new RuntimeException("Unable to load image with name ["+location+"]", e);
    		}
    	}

    }
    
    public static ImageData convertToSWT(BufferedImage bufferedImage) {
        if (bufferedImage.getColorModel() instanceof DirectColorModel) {
          DirectColorModel colorModel = (DirectColorModel) bufferedImage
              .getColorModel();
          PaletteData palette = new PaletteData(colorModel.getRedMask(),
              colorModel.getGreenMask(), colorModel.getBlueMask());
          ImageData data = new ImageData(bufferedImage.getWidth(),
              bufferedImage.getHeight(), colorModel.getPixelSize(),
              palette);
          WritableRaster raster = bufferedImage.getRaster();
          int[] pixelArray = new int[3];
          for (int y = 0; y < data.height; y++) {
            for (int x = 0; x < data.width; x++) {
              raster.getPixel(x, y, pixelArray);
              int pixel = palette.getPixel(new RGB(pixelArray[0],
                  pixelArray[1], pixelArray[2]));
              data.setPixel(x, y, pixel);
            }
          }
          return data;
        } else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
          IndexColorModel colorModel = (IndexColorModel) bufferedImage
              .getColorModel();
          int size = colorModel.getMapSize();
          byte[] reds = new byte[size];
          byte[] greens = new byte[size];
          byte[] blues = new byte[size];
          colorModel.getReds(reds);
          colorModel.getGreens(greens);
          colorModel.getBlues(blues);
          RGB[] rgbs = new RGB[size];
          for (int i = 0; i < rgbs.length; i++) {
            rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF,
                blues[i] & 0xFF);
          }
          PaletteData palette = new PaletteData(rgbs);
          ImageData data = new ImageData(bufferedImage.getWidth(),
              bufferedImage.getHeight(), colorModel.getPixelSize(),
              palette);
          data.transparentPixel = colorModel.getTransparentPixel();
          WritableRaster raster = bufferedImage.getRaster();
          int[] pixelArray = new int[1];
          for (int y = 0; y < data.height; y++) {
            for (int x = 0; x < data.width; x++) {
              raster.getPixel(x, y, pixelArray);
              data.setPixel(x, y, pixelArray[0]);
            }
          }
          return data;
        }
        return null;
      }
    
    static BufferedImage convertToAWT(ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        if (palette.isDirect) {
          colorModel = new DirectColorModel(data.depth, palette.redMask,
              palette.greenMask, palette.blueMask);
          BufferedImage bufferedImage = new BufferedImage(colorModel,
              colorModel.createCompatibleWritableRaster(data.width,
                  data.height), false, null);
          WritableRaster raster = bufferedImage.getRaster();
          int[] pixelArray = new int[3];
          for (int y = 0; y < data.height; y++) {
            for (int x = 0; x < data.width; x++) {
              int pixel = data.getPixel(x, y);
              RGB rgb = palette.getRGB(pixel);
              pixelArray[0] = rgb.red;
              pixelArray[1] = rgb.green;
              pixelArray[2] = rgb.blue;
              raster.setPixels(x, y, 1, 1, pixelArray);
            }
          }
          return bufferedImage;
        } else {
          RGB[] rgbs = palette.getRGBs();
          byte[] red = new byte[rgbs.length];
          byte[] green = new byte[rgbs.length];
          byte[] blue = new byte[rgbs.length];
          for (int i = 0; i < rgbs.length; i++) {
            RGB rgb = rgbs[i];
            red[i] = (byte) rgb.red;
            green[i] = (byte) rgb.green;
            blue[i] = (byte) rgb.blue;
          }
          if (data.transparentPixel != -1) {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                green, blue, data.transparentPixel);
          } else {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                green, blue);
          }
          BufferedImage bufferedImage = new BufferedImage(colorModel,
              colorModel.createCompatibleWritableRaster(data.width,
                  data.height), false, null);
          WritableRaster raster = bufferedImage.getRaster();
          int[] pixelArray = new int[1];
          for (int y = 0; y < data.height; y++) {
            for (int x = 0; x < data.width; x++) {
              int pixel = data.getPixel(x, y);
              pixelArray[0] = pixel;
              raster.setPixel(x, y, pixelArray);
            }
          }
          return bufferedImage;
        }
      }

}
