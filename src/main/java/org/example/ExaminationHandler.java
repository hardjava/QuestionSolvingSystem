package org.example;

import persistence.MyBatisConnectionFactory;
import persistence.dao.CategoryDAO;
import persistence.dao.ObtDAO;
import persistence.dao.QuestionDAO;
import persistence.dto.CategoryDTO;
import persistence.dto.QuestionDTO;

import java.util.*;

public class ExaminationHandler {
    Scanner sc = new Scanner(System.in);

    public void run() {
        showCategory();
        for (; ; ) {
            String command = getCommand();
            if (command.equals("End")) {
                break;
            }

            if (command.equals("A") || command.equals("B") || command.equals("C") || command.equals("D") || command.equals("E") || command.equals("F") || command.equals("G")) {
                List<QuestionDTO> questionLists = getQuestionList(command);
                Collections.shuffle(questionLists);
                examination(questionLists);
                break;
            }
            if (command.equals("H")) {
                List<QuestionDTO> questionLists = getAllQuestionList();
                Collections.shuffle(questionLists);
                examination(questionLists);
                break;
            }

            System.out.println("잘못된 접근입니다");
        }
    }

    private String getCommand() {
        System.out.print("문제 범주 선택 (이전 메뉴 이동 : 'End') : ");
        return sc.next();
    }

    private void showCategory() {
        CategoryDAO categoryDAO = new CategoryDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<CategoryDTO> categoryDTOS = categoryDAO.selectAll();
        System.out.println();
        for (CategoryDTO dto : categoryDTOS) {
            System.out.println(dto.getCategoryId() + ". " + dto.getCategoryName() + " (" + dto.getNum() + "개 문항)");
        }
        System.out.println("H. 전체 범주");
    }

    private List<QuestionDTO> getQuestionList(String category) {
        QuestionDAO questionDAO = new QuestionDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        Map<String, Object> params = new HashMap<>();
        params.put("categoryID", category);
        List<QuestionDTO> questions = questionDAO.getQuestion(params);
        return questions;
    }

    private List<QuestionDTO> getAllQuestionList() {
        QuestionDAO questionDAO = new QuestionDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<QuestionDTO> questions = questionDAO.getAllQuestion();
        return questions;
    }

    private void examination(List<QuestionDTO> questionLists) {
        int i;
        int collect = 0;
        System.out.println();
        String enter;
        List<Integer> wrongQuestionNum = new ArrayList<>();
        for (i = 0; i < questionLists.size(); i++) {
            System.out.println("문제 [" + (i + 1) + "] > " + questionLists.get(i).getQuestionText());
            Map<String, Object> params = new HashMap<>();
            params.put("questionID", questionLists.get(i).getQuestionID());
            new ObtDAO(MyBatisConnectionFactory.getSqlSessionFactory()).printObt(params);
            System.out.print("정답 (출제 종료 : 'End') : ");
            enter = sc.next();

            if (enter.equals("End")) {
                break;
            }

            System.out.print("결과 > ");
            if (enter.equals(questionLists.get(i).getAnswer())) {
                System.out.println("정답!");
                collect++;
            } else {
                System.out.println("오답!");
                wrongQuestionNum.add(i + 1);
            }
            System.out.println("답 > " + questionLists.get(i).getAnswer());
            System.out.println("해설 > " + questionLists.get(i).getCommentary() + "\n");
        }

        System.out.println("전체 문제 수 : " + i);
        System.out.println("정답 문제 수 : " + collect);
        System.out.println("틀린 문제 수 : " + (i - collect));
        System.out.println("틀린번호 : ");
        for (Integer integer : wrongQuestionNum) {
            System.out.println(integer + " <" + questionLists.get(integer - 1).getQuestionID() + ">");
        }
    }
}
