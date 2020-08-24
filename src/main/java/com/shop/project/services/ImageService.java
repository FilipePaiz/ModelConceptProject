package com.shop.project.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.project.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
		String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if(!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Image is not jpg or png");
		}
		
		try {
			BufferedImage img = ImageIO.read(multipartFile.getInputStream());
			
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileException("Error while reading the file");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE,  null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage image, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, extension, os);
			
			return new ByteArrayInputStream(os.toByteArray());
		}catch(IOException e) {
			throw new FileException("Error while reading the file");
		}
	}
	
	public BufferedImage cropASquare(BufferedImage image) {
		int min =(image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();
		
		return Scalr.crop(image, (image.getWidth()/2 - (min/2)), (image.getHeight()/2 - (min/2)), min, min);
	}
	
	public BufferedImage resize(BufferedImage image, int size) {
		return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
	}
}
