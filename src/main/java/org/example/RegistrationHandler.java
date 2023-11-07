package org.example;

import persistence.MyBatisConnectionFactory;
import persistence.dao.CategoryDAO;
import persistence.dao.ObtDAO;
import persistence.dao.QuestionDAO;
import persistence.dto.CategoryDTO;
import persistence.dto.InsertObtDTO;
import persistence.dto.InsertQuestionDTO;

import java.util.List;
import java.util.Scanner;

public class RegistrationHandler {
    private Scanner sc = new Scanner(System.in);

    public void run() {
        showCategory();

        for (; ; ) {
            String command = getCommand();
            if (command.equals("End")) {
                break;
            }

            if (command.equals("A") || command.equals("B") || command.equals("C") || command.equals("D") || command.equals("E") || command.equals("F") || command.equals("G")) {
                add(command);
                break;
            } else {
                System.out.println("잘못된 접근입니다");
            }

        }
    }

    private void showCategory() {
        CategoryDAO categoryDAO = new CategoryDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<CategoryDTO> categoryDTOS = categoryDAO.selectAll();
        System.out.println();
        for (CategoryDTO dto : categoryDTOS) {
            System.out.println(dto.getCategoryId() + ". " + dto.getCategoryName() + " (" + dto.getNum() + "개 문항)");
        }
    }

    private String getCommand() {
        System.out.print("문제 범주 선택 (이전 메뉴 이동 : 'End') : ");
        return sc.next();
    }

    private void add(String command) {
        sc.nextLine();
        System.out.print("문제 입력 > ");
        String questionText = sc.nextLine();
        System.out.print("1번 > ");
        String op1 = sc.nextLine();
        System.out.print("2번 > ");
        String op2 = sc.nextLine();
        System.out.print("3번 > ");
        String op3 = sc.nextLine();
        System.out.print("4번 > ");
        String op4 = sc.nextLine();
        System.out.print("정답 > ");
        String answer = sc.next();
        sc.nextLine();
        System.out.print("해설 > ");
        String commentary = sc.nextLine();
        System.out.print("저장하시겠습니까 (Yes or No) : ");
        String enter = sc.next();
        if (enter.equals("Yes")) {
            int row = addQuestion(command, questionText, answer, commentary);
            addOpt(row, 1, op1);
            addOpt(row, 2, op2);
            addOpt(row, 3, op3);
            addOpt(row, 4, op4);
        }
    }

    private int addQuestion(String categoryID, String questionText, String answer, String commentary) {
        QuestionDAO questionDAO = new QuestionDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        InsertQuestionDTO insertQuestionDTO = InsertQuestionDTO.builder()
                .categoryID(categoryID)
                .questionText(questionText)
                .answer(answer)
                .commentary(commentary).build();
        questionDAO.insertQuestion(insertQuestionDTO);

        return questionDAO.countRows();
    }


    private void addOpt(int row, int i, String opt) {
        ObtDAO obtDAO = new ObtDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        InsertObtDTO insertObtDTO = InsertObtDTO.builder()
                .questionID(row)
                .optNum(i)
                .optText(opt).build();
        obtDAO.insertObt(insertObtDTO);
    }
}
