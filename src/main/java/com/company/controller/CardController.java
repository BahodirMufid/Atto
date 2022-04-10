package com.company.controller;

import com.company.dto.Card;
import com.company.repository.CardRepository;
import com.company.service.CardService;
import com.company.service.TransactionService;

import java.util.Scanner;

public class CardController {
    MainController mainController;
    CardService cardService;
    CardRepository cardRepository;
    TransactionService transactionService;

    public void updateCard() {

        while (true) {
            updateCardMenu();
            switch (mainController.getAction()) {
                case 1:
                    cardService.updateStatus();
                    break;
                case 2:
                    cardService.updateNumber();
                    break;
                case 0:
                    getCard();
                    break;
            }
        }
    }

    private void updateCardMenu() {
        System.out.println("**** UPDATE ****");
        System.out.println("1. Status");
        System.out.println("2. Number");
        System.out.println("0. Exit ");
    }

    public void getCard() {
        while (true) {
            mainController.showCardMenu();
            switch (mainController.getAction()) {
                case 1:
                    createCard();
                    break;
                case 2:
                    updateCard();
                    break;
                case 3:
                    deleteCard();
                    break;
                case 4:
                    for (Card card : cardRepository.getCardList()) {
                        System.out.println(card);
                    }
                    break;
                case 5:
                    refillCard();
                    break;
                case 0:
                    mainController.getAdminMenu();
                    break;
            }
        }
    }

    private void refillCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number :");
        String card_number = scanner.next();
        System.out.print("Enter amount :");
        Long balance = scanner.nextLong();

        cardService.update(card_number, balance);
    }

    private void deleteCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number : ");
        String number = scanner.next();
        cardService.deleteCard(number);
    }

    public void createCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number : ");
        String number = scanner.next();
        System.out.print("Enter expiration date : ");
        String date = scanner.next();
        cardService.createCard(number, date);

    }

    public void getUserCard() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenuUser();
            switch (mainController.getAction()) {
                case 1:
                    addCardUser();
                    break;
                case 2:
                    System.out.print("Enter phone number : ");
                    cardService.cardList(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter card number : ");
                    cardService.changeType(scanner.next());
                    break;
                case 4:
                    System.out.print("Enter card number : ");
                    cardService.changeStatus(scanner.next());
                    break;
                case 0:
                    mainController.getUserMenu();
                    break;
            }
        }


    }

    private void addCardUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number : ");
        String cardNum = scanner.next();
        System.out.print("Enter Phone number : ");
        String phoneNum = scanner.next();

        cardService.createProfileCard(phoneNum,cardNum);


    }

    private void showMenuUser() {
        System.out.println("1. Add Card ");
        System.out.println("2. Card List ");
        System.out.println("3. Card Change Status");
        System.out.println("4. Delete Card");
        System.out.println("0. Exit");

    }

    public void companyCard() {
        while (true) {
            System.out.println("1. Transaction");
            System.out.println("2. Balance");
            System.out.println("0. Exit");
            switch (mainController.getAction()) {
                case 1:
                    transactionService.transaction("88");
                    break;
                case 2:
                    cardService.cardBalance("88");
                    break;
                case 0:
                    mainController.getAdminMenu();
                    break;
            }
        }


    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
