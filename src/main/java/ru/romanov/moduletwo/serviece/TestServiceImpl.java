package ru.romanov.moduletwo.serviece;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {

    private LocaleService localeService;
    private MessageSource ms;
    private QuestionService questionService;
    private ResultService resultService;
    private PersonService personService;

    public TestServiceImpl(LocaleService localeService, MessageSource ms,
                           QuestionService questionService, ResultService resultService, PersonService personService) {
        this.localeService = localeService;
        this.ms = ms;
        this.questionService = questionService;
        this.resultService = resultService;
        this.personService = personService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String startTest() {
        Locale locale = localeService.getLocale();
        try {
            int questionsCount = questionService.getQuestionsCount();
            for (int i = 0; i < questionsCount; i++) {
                System.out.println(ms.getMessage("Question_number",
                        new Object[]{i, questionService.getQuestionTextById(i)}, locale));
                Map<String, String> answersMap = questionService.getAnswersById(i);
                System.out.println("A: " + answersMap.get("A"));
                System.out.println("B: " + answersMap.get("B"));
                System.out.println("C: " + answersMap.get("C"));
                System.out.println("D: " + answersMap.get("D"));
                System.out.print(ms.getMessage("Enter_the_correct_answer_letter", null, locale));
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                while (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))) {
                    System.out.println("\n" + ms.getMessage("There_is_no_such_answer", null, locale));
                    System.out.print(ms.getMessage("Reenter_the_correct_answer_letter", null, locale));
                    answer = scanner.nextLine();
                }
                resultService.saveAnswer(i, answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms.getMessage("Test_is_finished", null, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTestResult() {
        Locale locale = localeService.getLocale();
        try {
            int questionsCount = questionService.getQuestionsCount();
            int rightAnswersCount = resultService.getRightAnswersCount();

            System.out.println(ms.getMessage(
                    "Test_result", new Object[]{personService.getActivePerson().getUserName(),
                            rightAnswersCount, questionsCount}, locale));

            if (rightAnswersCount != questionsCount) {
                System.out.println(ms.getMessage("Wrong_answers", null, locale) + resultService.getWrongAnswersString());
            } else {
                System.out.println(ms.getMessage("Congratulations", null, locale));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms.getMessage("Thanks_for_participating", null, locale);
    }
}
