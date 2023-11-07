package org.example;

import java.util.Scanner;

public class QuestionSolvingApp {
    private Scanner sc = new Scanner(System.in);

    public void run() {
        for (; ; ) {
            int command = getCommand();
            if (command == 3) {
                break;
            }

            switch (command) {
                case 1:
                    new RegistrationHandler().run();
                    break;
                case 2:
                    new ExaminationHandler().run();
                    break;
                default:
                    System.out.println("Please enter (1) ~ (3)");
            }
        }
    }

    private int getCommand() {
        System.out.println("\n1. 문제 등록, 2. 문제 출제, 3. 종료");
        System.out.print("입력 : ");
        return sc.nextInt();
    }
}