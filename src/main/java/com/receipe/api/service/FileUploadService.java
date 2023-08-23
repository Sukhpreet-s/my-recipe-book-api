package com.receipe.api.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private S3Service s3Service;
	@Value("${fileUpload.location}")
	private String IMAGES_DIR_PATH;
	@Value("${s3.uploadBaseURL}")
	private String s3UploadBaseURL;

	@Autowired
	public FileUploadService (S3Service s3Service) {
		this.s3Service = s3Service;
	}

	public List<String> uploadMultipleImages(MultipartFile[] images) throws Exception {
		List<String> imageUrls = new ArrayList<String>();
		File file = null;

		for (MultipartFile image: images) {
				String filename = UUID.randomUUID().toString();
				String extension = image.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
				String fullFilename = String.join(".", filename, extension);

				
				try {
					if(Files.notExists(Paths.get(IMAGES_DIR_PATH))) {
						Files.createDirectory(Paths.get(IMAGES_DIR_PATH));
					}
					file = new File(IMAGES_DIR_PATH+"\\"+fullFilename);
					image.transferTo(file);
					// image.transferTo(Paths.get(IMAGES_DIR_PATH, fullFilename));
					
				} catch (Exception e) {
					// TODO: Implement a logger here, log the exception instead of throwing it.
					throw new Exception(e);
				}

				// s3Service.putObject(fullFilename, IMAGES_DIR_PATH+"\\"+fullFilename);
				s3Service.putObject(fullFilename, file);

				imageUrls.add(s3UploadBaseURL+fullFilename);

				// Delete the saved file after uploading to S3.
				if(file.exists()) file.delete();
			}


		return imageUrls;
	}
	
}
