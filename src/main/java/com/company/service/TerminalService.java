package com.company.service;


import com.company.controller.MainController;
import com.company.dto.Terminal;
import com.company.enums.TermStatus;
import com.company.repository.TerminalRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TerminalService {
    MainController mainController;
   TerminalRepository terminalRepository;
    public void updateStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" -- Update --");
        System.out.println("enter terminal cod");
        String cod = scanner.next();
        System.out.println("1. Active");
        System.out.println("2. NoActive");
        switch (mainController.getAction()) {
            case 1:
                terminalRepository.updateTerm(cod, "ACTIVE");
                break;
            case 2:
                terminalRepository.updateTerm(cod, "NO_ACTIVE");
                break;
        }
    }

    public void updateAdress() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" -- Update --");
        System.out.println("enter  cod");
        String cod = scanner.next();
        System.out.println("enter adress");
        String adress = scanner.next();
        terminalRepository.updateTermAdress(cod,adress);

    }

    public void creatateTer() {
        Scanner  scanner = new Scanner(System.in);
        Terminal term = new Terminal();

        System.out.println("Enter adress");
        String  adress = scanner.nextLine();
        System.out.println("Enter cod");
        String  cod = scanner.nextLine();
        term.setAdress(adress);
        term.setCod(cod);
        term.setStatus(TermStatus.ACTIVE);
        term.setCreat_date(LocalDateTime.now());
        terminalRepository.createTerm(term);
    }

    public void deleteTerminal(String cod){
        Terminal terminal = terminalRepository.getTerminal(cod);
        if(terminal == null){
            System.out.println("Bu terminal yoq");
            return;
        }
        terminalRepository.deleteTerminal(cod);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setTerminalRepository(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }
}
