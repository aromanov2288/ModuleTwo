package ru.romanov.moduletwo.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.romanov.moduletwo.dao.QuestionDAO;
import ru.romanov.moduletwo.dao.ResultDAO;
import ru.romanov.moduletwo.domain.Question;

import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {

    private LocaleService localeService;
    private MessageSource ms;
    private ResultDAO resultDAO;
    private QuestionDAO questionDAO;

    @Autowired
    public ResultServiceImpl(LocaleService localeService, MessageSource ms, ResultDAO resultDao, QuestionDAO questionDao) {
        this.localeService = localeService;
        this.ms = ms;
        this.resultDAO = resultDao;
        this.questionDAO = questionDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAnswer(int id, String answer) {
        resultDAO.saveAnswer(id, answer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRightAnswersCount() throws Exception {
        int rightAnswersCount = 0;
        Map<Integer, String> answersMap = resultDAO.getAnswers();
        int questionsCount = questionDAO.getQuestionsCount();
        for (int i = 0; i < questionsCount; i++) {
            Question question = questionDAO.getQuestionById(i);
            if (question.getRightAnswer().equals(answersMap.get(i))) {
                rightAnswersCount++;
            }
        }
        return rightAnswersCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWrongAnswersString() throws Exception {
        String wrongAnswersString = "";
        Map<Integer, String> answersMap = resultDAO.getAnswers();
        int questionCount = questionDAO.getQuestionsCount();
        for (int i = 0; i < questionCount; i++) {
            Question question = questionDAO.getQuestionById(i);
            if (!question.getRightAnswer().equals(answersMap.get(i))) {
                wrongAnswersString = wrongAnswersString + i + "(" + answersMap.get(i)
                        + " " + ms.getMessage("Instead", null, localeService.getLocale()) + " "
                        + question.getRightAnswer() + ") ";
            }
        }
        return wrongAnswersString;
    }
}
