package org.magenta.test.task.service;

import java.util.List;

public interface CalculateService {
    List<Integer> crowflightFormula(String[] from, String[] to);
    List<Integer> distanceMatrix(String[] from, String[] to);
    List<Integer> allCalculations(String from, String to);
}
