package com.company.controller;

import java.util.Scanner;

public class MainController {
    CardController cardController;
    TransactionController transactionController;
    TerminalController terminalController;
    ProfileController profileController;

    public void getMenu() {
        while (true) {
            loginMenu();
            switch (getAction()) {
                case 1:
                    profileController.createProfile();
                    break;
                case 2:
                    profileController.login();
                    break;
                default:
                    System.out.println("No matches in this Section ! ");
                    break;
            }
        }
    }

    public void getAdminMenu() {
        while (true) {
            showAdminMenu();
            switch (getAction()) {
                case 1 -> cardController.getCard();
                case 2 -> terminalController.getTerminal();
                case 3 -> profileController.getProfile();
                case 4 -> transactionController.tranList();
                case 5 -> cardController.companyCard();
                case 6 -> transactionController.statics();
                case 0 -> getMenu();
            }
        }
    }

    public void showTerminalMenu() {
        System.out.println("**** MENU ****");
        System.out.println("1. Create terminal");
        System.out.println("2. Update terminal");
        System.out.println("3. Delete");
        System.out.println("4. Terminal List");
        System.out.println("0. Exit");
    }

    public void getUserMenu() {
        while (true) {
            showUserMenu();
            switch (getAction()) {
                case 1 -> cardController.getUserCard();
                case 2 -> transactionController.getRefill();
                case 3 -> transactionController.tranListUser();
                case 4 -> transactionController.makeTran();
            }
        }
    }

    private void loginMenu() {
        System.out.println("**** ATTO ****");
        System.out.println("1. Registration");
        System.out.println("2. Login");

    }

    private void showAdminMenu() {
        System.out.println("**** ATTO ****");
        System.out.println("1. Card");
        System.out.println("2. Terminal");
        System.out.println("3. Profile");
        System.out.println("4. Transaction List");
        System.out.println("5. Company Card");
        System.out.println("6. Statistic");
        System.out.println("0. Exit");
    }

    public void showCardMenu() {
        System.out.println("1.Create Card");
        System.out.println("2.Update Card");
        System.out.println("3.Delete card");
        System.out.println("4.Card list");
        System.out.println("5.ReFill");
        System.out.println("0.Exit");
    }

    private void showUserMenu() {
        System.out.println("**** MENU ****");
        System.out.println("1. Card ");
        System.out.println("2. ReFill");
        System.out.println("3. Transaction");
        System.out.println("4. Make Payment");

    }

    public int getAction() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void setCardController(CardController cardController) {
        this.cardController = cardController;
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}
