package tech.stabnashiamunashe.DoctorPatientPortal.Security.Service;

import tech.stabnashiamunashe.DoctorPatientPortal.Security.Models.CustomOAuth2User;
import tech.stabnashiamunashe.DoctorPatientPortal.Security.Repositories.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public MyOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return new CustomOAuth2User(super.loadUser(userRequest));
    }

//    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        CustomOAuth2User oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
//        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
//
//        if (user == null) {
//            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
//        }
//
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
//    }
//
//    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//        User user = new User();
//
//        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
//        user.setProviderId(oAuth2UserInfo.getId());
//        user.setUsername(oAuth2UserInfo.getName());
//        user.setEmail(oAuth2UserInfo.getEmail());
//        user.setImageUrl(oAuth2UserInfo.getImageUrl());
//
//        return userRepository.save(user);
//    }

}