package ru.romanov.moduletwo.dao;

import org.springframework.stereotype.Repository;
import ru.romanov.moduletwo.domain.Result;

import java.util.Map;

@Repository
public class ResultDAOImpl implements ResultDAO {

    private Result result = new Result();

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAnswer(int id, String answer) {
        Map<Integer, String> answersMap = result.getAnswersMap();
        answersMap.put(id, answer);
        result.setAnswersMap(answersMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, String> getAnswers() {
        return result.getAnswersMap();
    }


}
