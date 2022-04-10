package com.company.service;

import com.company.dto.Card;
import com.company.dto.ProfileCard;
import com.company.dto.Terminal;
import com.company.dto.TransactionDT0;
import com.company.db.Constant;
import com.company.enums.TransactionType;
import com.company.repository.CardRepository;
import com.company.repository.TerminalRepository;
import com.company.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionService {
CardRepository cardRepository ;
TerminalRepository terminalRepository;
TransactionRepository transactionRepository;
    public void create(String card_num, String terminal_cod, Long amaunt, LocalDateTime create_date, TransactionType type){
        Card card = cardRepository.getCard(card_num);
        if(!terminal_cod.equals("null")){
            Terminal terminal = terminalRepository.getTerminal(terminal_cod);
            if( terminal == null){
                System.out.println("bunday  terminal  yoq ");
                return;
            }
        }
        if(card == null){
            System.out.println("bunday card yoq ");
            return;
        }
        TransactionDT0 transactionDT0 = new TransactionDT0();
        transactionDT0.setAmaunt(amaunt);
        transactionDT0.setCard_num(card_num);
        transactionDT0.setTerminal_cod(terminal_cod);
        transactionDT0.setCreate_date(create_date);
        transactionDT0.setType(type);
        transactionRepository.createTransaction(transactionDT0);

    }

    public void list() {
        for (TransactionDT0 transactionDT0 : transactionRepository.list()) {
            System.out.println(transactionDT0);
        }
    }

    public void statics(LocalDate toDate, LocalDate fromDate) {
        if(fromDate == null){
            for (TransactionDT0 transactionDT0 : transactionRepository.getTranToDate(toDate)) {
                System.out.println(transactionDT0);
            }
            return;
        }
        for (TransactionDT0 transactionDT0 : transactionRepository.getTranDate(toDate, fromDate)) {
            System.out.println(transactionDT0);
        }



    }

    public void transaction(String card) {
        for (TransactionDT0 transactionDT0 : transactionRepository.getTranByCard(card)) {
            System.out.println(transactionDT0);
        }
    }

    public void refill(String cardnum, Long amunt) {
        ProfileCard profileCard = cardRepository.getProfileCard(cardnum);
        if(profileCard == null){
            System.out.println("bunday kartangiz yoq");
            return;
        }
        if(amunt <= 0){
            System.out.println("0 dan kotta summa kiriting ");
            return;
        }
        TransactionDT0 transactionDT0 = new TransactionDT0();
        transactionDT0.setAmaunt(amunt);
        transactionDT0.setCard_num(cardnum);
        transactionDT0.setCreate_date(LocalDateTime.now());
        transactionDT0.setType(TransactionType.REFILL);
        Card card = cardRepository.getCard(cardnum);
        transactionRepository.createTransaction(transactionDT0);
        cardRepository.update(cardnum, card.getBalance()+Double.valueOf(amunt));


    }

    public void transactionUser(String phone) {
        for (TransactionDT0 transactionDT0 : transactionRepository.getTranUser(phone)) {
            System.out.println(transactionDT0);
        }
    }

    public void makeTran(String cardNum, String terminalCod) {
        ProfileCard profileCard = cardRepository.getProfileCard(cardNum);
        Card card = cardRepository.getCard(cardNum);
        if (profileCard == null){
            System.out.println("Bunday karta yoq");
            return;
        }
        if (card.getBalance() < 1400 ){
            System.out.println("mablag yetarli emas");
            return;
        }
        TransactionDT0 transactionDT0 = new TransactionDT0();
        transactionDT0.setTerminal_cod(terminalCod);

        transactionDT0.setAmaunt(Constant.AMOUNT);

        transactionDT0.setCreate_date(LocalDateTime.now());
        transactionDT0.setType(TransactionType.PAYMENT);
        transactionDT0.setCard_num(cardNum);
        transactionRepository.createTransaction(transactionDT0);
        cardRepository.update(cardNum,card.getBalance() -1400);
    }

    public void setTerminalRepository(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
