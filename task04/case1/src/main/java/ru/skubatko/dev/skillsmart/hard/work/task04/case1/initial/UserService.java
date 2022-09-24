package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial;

import ru.skubatko.dev.skillsmart.hard.work.task01.case2.dependency.MessageService;

import lombok.RequiredArgsConstructor;
import ru.vtb.msa.alpp.comn.view.entity.ApplicationEntity;
import ru.vtb.msa.alpp.comn.view.exceptions.AppBadRequestException;
import ru.vtb.msa.alpp.comn.view.exceptions.AppNotFoundException;
import ru.vtb.msa.alpp.comn.view.exceptions.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final MessageService messageService;

    public UserDetailsDTO findByParams(String xEmployeePIN) {
        UserDetailsEntity userDetails = userRepository.findByUserCode(xEmployeePIN);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        if (Objects.nonNull(userDetails)) {
            userDetailsDTO = UserMapper.INSTANCE.toDto(userDetails);
        } else {
            List<Long> favAppList = new ArrayList<>();
            userDetailsDTO.setUserCode(xEmployeePIN);
            userDetailsDTO.setFavouriteApplications(favAppList);
        }
        return userDetailsDTO;
    }

    public UserDetailsDTO create(String xEmployeePIN, Long applicationId) {
        UserDetailsDTO userDetailsDTO;
        UserDetailsEntity userDetails = userRepository.findByUserCode(xEmployeePIN);
        userDetails = Objects.nonNull(userDetails) ?
            userDetails :
            new UserDetailsEntity(xEmployeePIN, new ArrayList<>());
        List<ApplicationEntity> favouriteApps = userDetails.getFavouriteApplications();
        ApplicationEntity app = getApplication(applicationId);

        checkIfUserNotFound(xEmployeePIN, userDetails);
        checkIfUserAlreadyHasApp(userDetails, app);

        favouriteApps.add(app);
        userRepository.save(userDetails);

        userDetailsDTO = UserMapper.INSTANCE.toDto(userDetails);
        return userDetailsDTO;
    }

    public void delete(String xEmployeePIN, Long applicationId) {
        UserDetailsEntity userDetails = userRepository.findByUserCode(xEmployeePIN);

        List<ApplicationEntity> favouriteApps = userDetails.getFavouriteApplications();

        ApplicationEntity app = getApplication(applicationId);

        checkIfUserNotFound(xEmployeePIN, userDetails);
        checkIfUserHasNotFavouriteApplication(app, userDetails);

        favouriteApps.remove(app);
        userRepository.save(userDetails);
    }

    private void checkIfUserNotFound(
        String xEmployeePIN,
        UserDetailsEntity userDetails
    ) {
        if (Objects.isNull(userDetails)) {
            throw new AppNotFoundException(
                Errors.USER_NOT_FOUND,
                messageService.getLocalizedMessage("user.not_found.alpp-comn-view-5", xEmployeePIN)
            );
        }
    }

    private ApplicationEntity getApplication(
        Long applicationId
    ) {
        return applicationRepository.findByApplicationId(applicationId)
            .orElseThrow(() -> new AppNotFoundException(
                Errors.APPLICATION_NOT_FOUND,
                messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                    messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString()))
            );
    }

    private void checkIfUserAlreadyHasApp(
        UserDetailsEntity userDetails,
        ApplicationEntity app
    ) {
        if (
            Objects.nonNull(userDetails.getFavouriteApplications()) &&
                userDetails.getFavouriteApplications().contains(app)
        ) {
            long applicationId = Objects.nonNull(app.getApplicationId()) ? app.getApplicationId() : 0L;
            throw new AppBadRequestException(
                Errors.USER_ALREADY_HAS_FAVOURITE_APP,
                messageService.getLocalizedMessage(
                    "application.favourites.already_added_for_user.alpp-comn-view-4",
                    Long.toString(applicationId),
                    userDetails.getUserCode()
                )
            );
        }
    }

    private void checkIfUserHasNotFavouriteApplication(
        ApplicationEntity app,
        UserDetailsEntity userDetails
    ) {
        if (!userDetails.getFavouriteApplications().contains(app)) {
            long applicationId = Objects.nonNull(app.getApplicationId()) ? app.getApplicationId() : 0L;
            throw new AppNotFoundException(
                Errors.FAVOURITE_APP_FOR_USER_NOT_FOUND,
                messageService.getLocalizedMessage(
                    "application.favourites.not_found_for_user.alpp-comn-view-3",
                    Long.toString(applicationId),
                    userDetails.getUserCode()
                )
            );
        }
    }
}
