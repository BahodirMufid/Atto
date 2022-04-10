package com.company;

import com.company.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   private static MainController mainController;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MainController mainController = (MainController) context.getBean("mainController");
        mainController.getMenu();

    }


    public  void setMainController(MainController mainController) {
        Main.mainController = mainController;
    }


}
