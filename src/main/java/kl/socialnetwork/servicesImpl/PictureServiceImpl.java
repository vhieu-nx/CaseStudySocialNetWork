package kl.socialnetwork.servicesImpl;

import kl.socialnetwork.domain.entities.Picture;
import kl.socialnetwork.domain.entities.User;
import kl.socialnetwork.domain.models.serviceModels.PictureServiceModel;
import kl.socialnetwork.repositories.PictureRepository;
import kl.socialnetwork.repositories.RoleRepository;
import kl.socialnetwork.repositories.UserRepository;
import kl.socialnetwork.services.CloudinaryService;
import kl.socialnetwork.services.PictureService;
import kl.socialnetwork.validations.serviceValidation.services.CloudinaryValidationService;
import kl.socialnetwork.validations.serviceValidation.services.PictureValidationService;
import kl.socialnetwork.validations.serviceValidation.services.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static kl.socialnetwork.utils.constants.ResponseMessageConstants.SERVER_ERROR_MESSAGE;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final UserValidationService userValidation;
    private final PictureValidationService pictureValidation;
    private final CloudinaryValidationService cloudinaryValidation;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, UserRepository userRepository, RoleRepository roleRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper, UserValidationService userValidation, PictureValidationService pictureValidation, CloudinaryValidationService cloudinaryValidation) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.userValidation = userValidation;
        this.pictureValidation = pictureValidation;
        this.cloudinaryValidation = cloudinaryValidation;
    }

    @Override
    public boolean addPicture(String loggedInUserId, MultipartFile file) throws Exception {
        User user = this.userRepository.findById(loggedInUserId).orElse(null);

        if (!userValidation.isValid(user)) {
            throw new Exception(SERVER_ERROR_MESSAGE);
        }

        String cloudinaryPublicId = UUID.randomUUID().toString();
        Map uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);

        if (!cloudinaryValidation.isValid(uploadMap)) {
            throw new Exception(SERVER_ERROR_MESSAGE);
        }

        Picture picture = new Picture();
        picture.setImageUrl(uploadMap.get("url").toString());
        picture.setUser(user);
        picture.setTime(LocalDateTime.now());
        picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());

        return this.pictureRepository.save(picture) != null;
    }

    @Override
    public List<PictureServiceModel> getAllPicturesByUserId(String userId) {
        return null;
    }

    @Override
    public boolean deletePicture(String loggedInUserId, String photoToRemoveId) throws Exception {
        return false;
    }
}
