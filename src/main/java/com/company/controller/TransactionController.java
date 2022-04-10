package com.company.controller;

import com.company.enums.TransactionType;
import com.company.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TransactionController {
TransactionService transactionService;
    MainController mainController;
    public void create(String o_type){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number : ");
        String card_num = scanner.next();
        System.out.print("Enter terminal code : ");
        String terminal_cod = scanner.next();
        System.out.print("Enter amount : ");
        Long amaunt = scanner.nextLong();
        LocalDateTime create_date = LocalDateTime.now();
        TransactionType type = TransactionType.valueOf(o_type);
        transactionService.create(card_num,terminal_cod,amaunt,create_date,type);
        System.out.println("***** Success *****");

    }

    public void tranList() {
        transactionService.list();
    }

    public void statics() {
        Scanner scanner =  new Scanner(System.in);
        while (true){

            System.out.println("1. Bugungi");
            System.out.println("2. Kunlik to'lovlar");
            System.out.println("3. Oraliq to'lovlar");

            switch (mainController.getAction()){
                case 1:
                    LocalDate toDate = LocalDate.now();
                    LocalDate fromDate = null;
                    transactionService.statics(toDate,fromDate);                    break;
                case 2:

                    System.out.println("Enter date");
                    String date = scanner.next();
                    transactionService.statics(LocalDate.parse(date),null);
                    break;
                case 3:
                    System.out.println("Enter to date");
                    String todata = scanner.next();
                    System.out.println("Enter from date");
                    String fromdate = scanner.next();
                    transactionService.statics(LocalDate.parse(todata),LocalDate.parse(fromdate));
                    break;
            }
        }
    }

    public void getRefill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cardNum");
        String cardnum = scanner.next();
        System.out.println("Enter amaunt");
        Long amunt = scanner.nextLong();
        transactionService.refill(cardnum,amunt);

    }

    public void tranListUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter phone number");
        String phone = scanner.next();
        transactionService.transactionUser(phone);

    }

    public void makeTran() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card number");
        String cardNum = scanner.next();
        System.out.println("Enter terminal ccod");
        String  terminalCod= scanner.next();
        transactionService.makeTran(cardNum,terminalCod);
    }
}
