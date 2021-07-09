package kl.socialnetwork.servicesImpl;

import kl.socialnetwork.services.CloudinaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SERVER_ERROR_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudinaryServiceTests {
    private static final String TEST_UUID = "test_uuid";

    @Autowired
    private CloudinaryService cloudinaryService;

    @Test
    public void uploadImage_whenMultipartFileAndUuidAreValid_uploadImage() throws Exception {
        String TEST_IMAGE_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\favi.png";

        Path path = Paths.get(TEST_IMAGE_FILE_PATH);
        String name = "file.png";
        String originalFileName = "softuniLogo.PNG";
        String contentType = "image/png";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
            throw new Exception(SERVER_ERROR_MESSAGE);
        }

        MultipartFile pictureFile = new MockMultipartFile(name,
                originalFileName, contentType, content);

        // Act
        Map uploadMap = cloudinaryService.uploadImage(pictureFile, TEST_UUID);

        // Assert
        assertNotNull(uploadMap);
        assertNotNull(uploadMap.get("url"));
        assertEquals(TEST_UUID, uploadMap.get("public_id"));
    }

}
