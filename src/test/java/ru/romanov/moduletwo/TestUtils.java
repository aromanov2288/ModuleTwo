package ru.romanov.moduletwo;

import ru.romanov.moduletwo.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {

    public static List<Question> getPreparedQuestionList() {
        List<Question> questionList = new ArrayList<Question>();
        for (int i = 0; i < 3; i++) {
            Question question = new Question(i);
            question.setText("Текст " + i + " вопроса.");
            questionList.add(question);
            Map<String, String> answersMap = new HashMap<String, String>();
            answersMap.put("A", "Ответ А" + i);
            answersMap.put("B", "Ответ B" + i);
            answersMap.put("C", "Ответ C" + i);
            answersMap.put("D", "Ответ D" + i);
            question.setAnswers(answersMap);
            question.setRightAnswer("A");
        }
        return questionList;
    }

    public static Map<Integer, String> getPreparedAnswersMap() {
        Map<Integer, String> answersMap = new HashMap<Integer, String>();
        answersMap.put(0, "A");
        answersMap.put(1, "B");
        answersMap.put(2, "C");
        return answersMap;
    }
}
