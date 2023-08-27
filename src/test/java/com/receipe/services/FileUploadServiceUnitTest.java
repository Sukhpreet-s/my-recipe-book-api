package com.receipe.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import com.receipe.api.service.FileUploadService;
import com.receipe.api.service.S3Service;

public class FileUploadServiceUnitTest {

	private S3Service s3Service;

	private FileUploadService fileUploadService;

	private final String tempImgFolderPath = System.getProperty("user.dir")+"\\temp";

	@BeforeEach                                         
	void setUp() {
			s3Service = mock(S3Service.class);
			fileUploadService = new FileUploadService(s3Service);
			ReflectionTestUtils.setField(fileUploadService, "IMAGES_DIR_PATH", tempImgFolderPath);
			
	}

	@Test
	void isUploadMultipleImagesReturningSameNumberOfUrlsAsNumberOfFiles() throws Exception{
		// Input
		String firstImgName = "first-img.jpg";
		String secondImgName = "second-img.PNG";
		FileInputStream firstImgIS = new FileInputStream(getImgPath(firstImgName));
		FileInputStream secondImgIS = new FileInputStream(getImgPath(secondImgName));
		
 		MockMultipartFile[] images = {
			new MockMultipartFile("images", firstImgName, MediaType.IMAGE_JPEG_VALUE, firstImgIS),
			new MockMultipartFile("images", secondImgName, MediaType.IMAGE_PNG_VALUE, secondImgIS)
		};

		int returnNumberOfUrls = fileUploadService.uploadMultipleImages(images).size();

		//Expected
		int expectedNumberOfUrls = 2;

		// Assert
		assertEquals(returnNumberOfUrls, expectedNumberOfUrls);
	}
	
	@Test
	void isUploadedImages_HaveCorrectBaseUrl() throws Exception {
		// NOTE: This is not a real url.
		String testS3BaseUrl = "https://testS3BaseUrl.com/";

		// Make FileUploadService use the test url instead of the actual url.
		ReflectionTestUtils.setField(fileUploadService, "s3UploadBaseURL", testS3BaseUrl);

		// Test Input
		String imgName = "first-img.jpg";
		FileInputStream imgIS = new FileInputStream(getImgPath(imgName));
		
 		MockMultipartFile[] images = {
			new MockMultipartFile("images", imgName, MediaType.IMAGE_JPEG_VALUE, imgIS)
		};

		// Execute
		String outputImgUrl = fileUploadService.uploadMultipleImages(images).get(0);

		// Assert
		assertTrue(outputImgUrl.startsWith(testS3BaseUrl));

	}

	@Test
	void isUploadedImages_HaveCorrectExtension() throws Exception {
		// Input
		String firstImgName = "first-img.jpg";
		String secondImgName = "second-img.PNG";
		FileInputStream firstImgIS = new FileInputStream(getImgPath(firstImgName));
		FileInputStream secondImgIS = new FileInputStream(getImgPath(secondImgName));
		
 		MockMultipartFile[] images = {
			new MockMultipartFile("images", firstImgName, MediaType.IMAGE_JPEG_VALUE, firstImgIS),
			new MockMultipartFile("images", secondImgName, MediaType.IMAGE_PNG_VALUE, secondImgIS)
		};

		// Execute
		String splitByLastDotRegex = "\\.(?=[^\\.]+$)";
		List<String> outputImgUrlsExtensions = fileUploadService.uploadMultipleImages(images).stream()
			.map(url -> url.split(splitByLastDotRegex)[1])
			.collect(Collectors.toList());

		// Assert
		List<String> expectedExtensions = Arrays.asList("jpg", "PNG");

		assertTrue(
			outputImgUrlsExtensions.size()==expectedExtensions.size() && // Size matches
			outputImgUrlsExtensions.containsAll(expectedExtensions) &&   // Values in expected are present in output
			expectedExtensions.containsAll(outputImgUrlsExtensions));    // Values in output are present in expected
	}

	@Test
	void isUploadedImages_DeletedFromLocalFolder() throws Exception {
		// Test Input
		String imgName = "first-img.jpg";
		FileInputStream imgIS = new FileInputStream(getImgPath(imgName));
		
 		MockMultipartFile[] images = {
			new MockMultipartFile("images", imgName, MediaType.IMAGE_JPEG_VALUE, imgIS)
		};

		// Execute
		fileUploadService.uploadMultipleImages(images);

		// Assert
		assertTrue(Files.list(Paths.get(tempImgFolderPath)).findFirst().isPresent() == false);
	}

	/**
	 * Generate full absolute path for the given image name. 
	 * @param imgName name of the image file.
	 * @return String absolute path to the given image.
	 */
	private String getImgPath(String imgName) {
		return System.getProperty("user.dir") + "/src/test/resources/test-img/" + imgName;
	}

}
