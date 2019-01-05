package ru.romanov.moduletwo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.romanov.moduletwo.config.YamlProps;
import ru.romanov.moduletwo.domain.Question;
import ru.romanov.moduletwo.serviece.LocaleService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

    private LocaleService localeService;

    private MessageSource ms;

    private String fileName;

    private List<Question> questionsList = null;

    @Autowired
    public QuestionDAOImpl(LocaleService localeService, MessageSource ms, YamlProps yamlProps) {
        this.localeService = localeService;
        this.ms = ms;
        this.fileName = yamlProps.getFileName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuestionsCount() throws Exception {
        if (questionsList == null) {
            readQuestionsFile();
        }
        return questionsList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Question getQuestionById(int id) throws Exception {
        if (questionsList == null) {
            readQuestionsFile();
        }
        return questionsList.get(id);
    }

    private void readQuestionsFile() throws Exception {
        String[] subFileName = fileName.split("\\.");
        String fileNameWithLocale = System.getProperty("user.dir") + "/src/main/resources/"
                + subFileName[0] + ms.getMessage("Locale", null, localeService.getLocale()) + "." + subFileName[1];
        try {
            File file = new File(fileNameWithLocale);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] subStr = null;
            questionsList = new ArrayList<Question>();
            while (line != null) {
                subStr = line.split(",");
                int currentId = Integer.parseInt(subStr[0]);
                Question question = new Question(currentId);
                question.setText(subStr[1]);
                Map<String, String> answersMap = new HashMap<String, String>();
                answersMap.put("A", subStr[2]);
                answersMap.put("B", subStr[3]);
                answersMap.put("C", subStr[4]);
                answersMap.put("D", subStr[5]);
                question.setAnswers(answersMap);
                question.setRightAnswer(subStr[6]);
                questionsList.add(question);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new Exception(ms.getMessage("Could_not_find_file", new Object[]{fileNameWithLocale}, localeService.getLocale()));
        } catch (IOException e) {
            throw new Exception(ms.getMessage("Could_not_read_file", new Object[]{fileNameWithLocale}, localeService.getLocale()));
        }
    }
}
