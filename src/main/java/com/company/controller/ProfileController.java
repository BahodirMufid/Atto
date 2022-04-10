package com.company.controller;

import com.company.dto.ProfileDto;
import com.company.repository.ProfileRepository;
import com.company.service.ProfileService;
import org.springframework.stereotype.Repository;


import java.util.Scanner;

public class ProfileController {
    ProfileService profileService;
    MainController mainController;
    ProfileRepository profileRepository;

    public void createProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name : ");
        String name = scanner.nextLine();
        System.out.print("Enter surname : ");
        String surname = scanner.nextLine();
        System.out.print("Enter phone : ");
        String phone = scanner.nextLine();
        System.out.print("enter password : ");
        String password = scanner.nextLine();
        profileService.createProfile(name, surname, phone, password);
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone : ");
        String phone = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();
        profileService.login(phone, password);
    }

    public void getProfile() {
        while (true) {
            showMenu();
            switch (mainController.getAction()) {
                case 1:
                    profileList();
                    break;
                case 2:
                    changeStatus();
                    break;
            }
        }

    }

    private void profileList() {
        Repository repository = null;
        for (ProfileDto profileDto : profileRepository.getProfileList()) {
            System.out.println(profileDto);
        }
    }

    private void changeStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number : ");
        String number = scanner.next();
        profileService.changeStatus(number);
    }

    private void showMenu() {
        System.out.println("1. Profile List");
        System.out.println("2. Change Status");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileRepository = profileRepository;
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;

    }
}
