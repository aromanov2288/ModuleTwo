package ru.romanov.moduletwo.domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Map<Integer, String> answersMap = new HashMap<Integer, String>();

    public Map<Integer, String> getAnswersMap() {
        return answersMap;
    }

    public void setAnswersMap(Map<Integer, String> answersMap) {
        this.answersMap = answersMap;
    }
}
