package com.receipe.api.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3Service {

	private final S3Client s3Client;

	@Value("${s3.bucketName}")
	private String BUCKET_NAME;
	@Value("${s3.subdirectory}")
	private String s3Subdirecoty;

	@Autowired
	public S3Service (S3Client s3Client) {
		this.s3Client = s3Client;
	}

	public void putObject(String objectKey, File file) {
		try {
				PutObjectRequest putOb = PutObjectRequest.builder()
						.bucket(BUCKET_NAME)
						.key(createFullS3ObjectKey(objectKey))
						.build();
				s3Client.putObject(putOb, RequestBody.fromFile(file));
				System.out.println("Successfully placed " + objectKey +" into bucket "+BUCKET_NAME);

		} catch (S3Exception e) {
				System.err.println(e.getMessage());
		}
	}

	/**
	 * Creates a full path (subfolder) to save the object
	 * @param objectKey File name
	 * @return String complete path with the file name
	 */
	private String createFullS3ObjectKey(String objectKey) {
		return s3Subdirecoty+"/"+objectKey;
	}
	
}
