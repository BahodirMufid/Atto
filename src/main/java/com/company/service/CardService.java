package com.company.service;

import com.company.controller.MainController;
import com.company.dto.Card;
import com.company.dto.ProfileCard;
import com.company.dto.ProfileDto;
import com.company.enums.CardStatus;
import com.company.enums.ProfileCardStatus;
import com.company.enums.ProfileCardType;
import com.company.enums.TransactionType;
import com.company.repository.CardRepository;
import com.company.repository.ProfileRepository;
import com.company.repository.TransactionRepository;


import java.time.LocalDateTime;
import java.util.Scanner;

public class CardService {
MainController mainController;
CardRepository cardRepository;
TransactionService transactionService;
TransactionRepository transactionRepository;
    ProfileRepository profileRepository;
    public void updateStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" -- Update --");
        System.out.println("enter card number");
        String number = scanner.next();
        System.out.println("1. Active");
        System.out.println("2. NoActive");
        switch (mainController.getAction()) {
            case 1:
                cardRepository.updateCard(number, "ACTIVE");
                break;
            case 2:
                cardRepository.updateCard(number, "NO_ACTIVE");
                break;
        }
    }

    public void updateNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" -- Update --");
        System.out.println("enter card number");
        String old_number = scanner.next();
        System.out.println("New number");
        String new_number = scanner.next();
        cardRepository.updateCardNumber(old_number,new_number);


    }

    public void deleteCard(String number) {
        Card card = cardRepository.getCard(number);
        if(card == null){
            System.out.println("bunday card yoq");
            return;
        }
        cardRepository.deleteCard(number);
    }

    public void update(String card_number, Long balance) {
        Card card = cardRepository.getCard(card_number);
        if(card == null){
            System.out.println("Bunday card yoq ");
            return;
        }
        transactionService.create(card_number,"null",balance,LocalDateTime.now(), TransactionType.REFILL);
        cardRepository.update(card_number,balance + card.getBalance());
    }

    public void createCard(String number, String expdate) {

        Card CheckCard = cardRepository.getCard(number);
        if(CheckCard != null){
            System.out.println("bunday card bor ");
            return;
        }
        Card card = new Card();
        card.setCreate_date(LocalDateTime.now());
        card.setExpDate(expdate);
        card.setNumber(number);
        card.setStatus(CardStatus.ACTIVE);
        card.setBalance(0.0);
       cardRepository.createCard(card);


    }

    public void cardBalance(String number) {
        Card Checkcard = cardRepository.getCard(number);
        if(Checkcard == null){
            System.out.println("bunday card yoq");
            return;
        }
        System.out.println("Balance: "+ Checkcard.getBalance());

    }

    public void cardList(String phone) {
        ProfileDto profileByPhone = profileRepository.getProfileByPhone(phone);
        if(profileByPhone == null){
            System.out.println("Bunday phone yoq");
            return;
        }
        for (ProfileCard profileCard : transactionRepository.getTranByPhone(phone)) {
            if (profileCard.getType().equals(ProfileCardType.ACTIVE)){
                System.out.println(profileCard.getCardNum() + " " + profileCard.getExpDate() + " "
                        + profileCard.getBalance() + " " + profileCard.getPhoneNum());
            }

        }
    }

    public void createProfileCard(String phoneNum, String cardNum) {
        ProfileDto profileByPhone = profileRepository.getProfileByPhone(phoneNum);
        Card card = cardRepository.getCard(cardNum);
        if(profileByPhone == null){
            System.out.println("bunday nomer yoq ");
            return;
        }
        if(card == null){
            System.out.println("Bunday card yoq ");
            return;
        }

        ProfileCard profileCard = new ProfileCard();
        profileCard.setCardNum(cardNum);
        profileCard.setCreateDate(LocalDateTime.now());
        profileCard.setPhoneNum(phoneNum);
        profileCard.setStatus(ProfileCardStatus.VISIABLE);

        cardRepository.createProfileCard(profileCard);

    }

    public void changeType(String cardNum) {
        ProfileCard profileCard = cardRepository.getProfileCard(cardNum);
        if(profileCard == null){
            System.out.println("bunday kartangiz yoq ");
            return;
        }
        System.out.println("1. Active");
        System.out.println("2. No_Active");
        switch (mainController.getAction()){
            case 1:
                cardRepository.changeTypeProfileCard(cardNum,"ACTIVE");
                break;
            case 2:
                cardRepository.changeTypeProfileCard(cardNum,"NO_ACTIVE");
                break;
        }
    }

    public void changeStatus(String cardNum) {
        ProfileCard profileCard = cardRepository.getProfileCard(cardNum);
        if(profileCard == null){
            System.out.println("bunday kartangiz yoq ");
            return;
        }
        cardRepository.changeStatusProfilCard(cardNum);

    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
