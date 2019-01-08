package ru.romanov.moduletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import ru.romanov.moduletwo.domain.Person;
import ru.romanov.moduletwo.serviece.LocaleService;
import ru.romanov.moduletwo.serviece.PersonService;
import ru.romanov.moduletwo.serviece.QuestionService;
import ru.romanov.moduletwo.serviece.ResultService;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Presenter {

    private PersonService personService;

    private QuestionService questionService;

    private ResultService resultService;

    private LocaleService localeService;

    private MessageSource ms;

    @Autowired
    public Presenter(PersonService personService, QuestionService questionService,
                     ResultService resultService, LocaleService localeService,
                     MessageSource messageSource) {
        this.personService = personService;
        this.questionService = questionService;
        this.resultService = resultService;
        this.localeService = localeService;
        this.ms = messageSource;
    }

    public void startTest() {
        Scanner scanner = new Scanner(System.in);

        Locale locale = Locale.getDefault();

        System.out.print(ms.getMessage("Choose_localization", null, locale));
        String localeStrCode = scanner.nextLine();
        int localeCode = Integer.parseInt(localeStrCode);

        switch (localeCode) {
            case 1:
                locale = new Locale("en");
                break;
            case 2:
                locale = new Locale("ru");
        }

        localeService.saveLocale(locale);

        System.out.println(ms.getMessage("Hello_in_our_test", null, locale));
        System.out.print(ms.getMessage("Enter_your_name", null, locale));
        Person person = new Person();
        person.setName(scanner.nextLine());
        System.out.print(ms.getMessage("Enter_your_surname", null, locale));
        person.setPatronName(scanner.nextLine());
        personService.savePerson(person);

        try {
            int questionsCount = questionService.getQuestionsCount();
            for (int i = 0; i < questionsCount; i++) {
                System.out.println(ms.getMessage("Question_number", new Object[]{i, questionService.getQuestionTextById(i)}, locale));
                Map<String, String> answersMap = questionService.getAnswersById(i);
                System.out.println("A: " + answersMap.get("A"));
                System.out.println("B: " + answersMap.get("B"));
                System.out.println("C: " + answersMap.get("C"));
                System.out.println("D: " + answersMap.get("D"));
                System.out.print(ms.getMessage("Enter_the_correct_answer_letter", null, locale));
                scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                while (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))) {
                    System.out.println("\n" + ms.getMessage("There_is_no_such_answer", null, locale));
                    System.out.print(ms.getMessage("Reenter_the_correct_answer_letter", null, locale));
                    answer = scanner.nextLine();
                }
                resultService.saveAnswer(i, answer);
            }

            int rightAnswersCount = resultService.getRightAnswersCount();

            System.out.println(ms.getMessage(
                    "Test_result", new Object[]{personService.getPerson().getName(), rightAnswersCount, questionsCount}, locale));

            if (rightAnswersCount != questionsCount) {
                System.out.println(ms.getMessage("Wrong_answers", null, locale) + resultService.getWrongAnswersString());
            } else {
                System.out.println(ms.getMessage("Congratulations", null, locale));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
