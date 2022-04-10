package com.company.controller;

import com.company.dto.Terminal;
import com.company.repository.TerminalRepository;
import com.company.service.TerminalService;

import java.util.Scanner;

public class TerminalController {
    MainController mainController;
    TerminalService terminalService;
    TerminalRepository terminalRepository;
    private void updateTerMenu() {
        System.out.println("**** UPDATE ****");
        System.out.println("1. Adress");
        System.out.println("2. Status");
        System.out.println("0. Exit ");
    }

    public void updateTerminal() {
        while (true) {
            updateTerMenu();
            switch (mainController.getAction()) {
                case 1:
                    terminalService.updateAdress();
                    break;
                case 2:
                    terminalService.updateStatus();
                    break;
                case 0:
                    getTerminal();
                    break;
            }
        }
    }

    public void getTerminal() {
        while (true) {
            mainController.showTerminalMenu();
            switch (mainController.getAction()) {
                case 1:
                    terminalService.creatateTer();
                    break;
                case 2:
                    updateTerminal();
                    break;
                case 3:
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter Terminal id : ");
                    String cod = scanner.next();
                    terminalService.deleteTerminal(cod);
                    break;
                case 4:
                    for (Terminal terminal : terminalRepository.getTermList()) {
                        System.out.println(terminal);
                    }
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

    public void setTerminalRepository(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public void setTerminalService(TerminalService terminalService) {
        this.terminalService = terminalService;
    }
}
