package com.company.service;

import com.company.controller.MainController;
import com.company.dto.ProfileDto;
import com.company.enums.ProfileStatus;
import com.company.enums.ProfileType;
import com.company.repository.ProfileRepository;

import java.time.LocalDateTime;

public class ProfileService {
    MainController mainController;
    ProfileRepository profileRepository;


    public void createProfile(String name, String surname, String phone, String password){
        if(name.length() < 2){
            System.out.println("Name ni to'liq yozing");
            return;
        }
        if(surname.length() < 4){
            System.out.println("Surname ni to'liq yozing");
            return;
        }
        if(!phone.startsWith("+998") || phone.length() < 13){
            System.out.println("Nomer xato yozilgan");
            return;
        }
        ProfileDto profileByPhone = profileRepository.getProfileByPhone(phone);
        if(profileByPhone != null){
            System.out.println("Bunday profil bor");
            return;
        }
        if(password.length() < 8){
            System.out.println("parolni uzunligi 8 ta belgidan kichik");
            return;
        }
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(name);
        profileDto.setSurname(surname);
        profileDto.setPhone(phone);
        profileDto.setPassword(password);
        profileDto.setCreate_date(LocalDateTime.now());
        profileDto.setType(ProfileType.USER);
        profileDto.setStatus(ProfileStatus.GOOD);
        profileRepository.createProfile(profileDto);
    }

    public void login(String phone,String password){
        ProfileDto profile = profileRepository.getProfileByPhone(phone);

        if(phone.equals("2")){
            mainController.getUserMenu();
        }

        if(phone.equals("1")){
            mainController.getAdminMenu();
        }
        if (profile == null){
            System.out.println("bunday phone yoq");
            return;
        }
        if(!profile.getPassword().equals(password)){
            System.out.println("parol xato");
            return;
        }
        if (profile.getType().equals(ProfileType.ADMIN)){
            mainController.getAdminMenu();
        }
        mainController.getUserMenu();

    }

    public void changeStatus(String number) {
        ProfileDto profile = profileRepository.getProfileByPhone(number);
        if(profile == null){
            System.out.println("bunday profile yoq");
            return;
        }
        System.out.println("1. Active");
        System.out.println("2. Block");
        switch (mainController.getAction()) {
            case 1 -> {
                profile.setStatus(ProfileStatus.ACTIVE);
                profileRepository.updateStatus(profile);
            }
            case 2 -> {
                profile.setStatus(ProfileStatus.BLOCK);
                profileRepository.updateStatus(profile);
            }
        }

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
