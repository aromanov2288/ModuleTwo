package ru.romanov.moduletwo.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanov.moduletwo.dao.QuestionDAO;

import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDAO dao;

    @Autowired
    public QuestionServiceImpl(QuestionDAO dao) {
        this.dao = dao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuestionsCount() throws Exception {
        return dao.getQuestionsCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestionTextById(int id) throws Exception {
        return dao.getQuestionById(id).getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getAnswersById(int id) throws Exception {
        return dao.getQuestionById(id).getAnswers();
    }
}
