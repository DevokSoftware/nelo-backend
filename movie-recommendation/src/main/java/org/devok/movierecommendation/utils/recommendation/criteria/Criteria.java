package org.devok.movierecommendation.utils.recommendation.criteria;

import java.util.*;

public enum Criteria {
    GENRE(15),
    DIRECTOR(35),
    CAST(35),
    RELEASE_DATE(15);

    private final int weight;

    private Criteria(int weight) {
        this.weight = weight;
    }

    private int getWeight() {
        return weight;
    }

    private static final List<Criteria> VALUES =
            List.of(values());

    private static int sumWeights() {
        int sum = 0;
        for (Criteria value : VALUES)
            sum += value.getWeight();
        return sum;
    }

    private static final int SIZE = sumWeights();
    private static final Random RANDOM = new Random();

    public static Criteria randomType() {
        int randomNum = RANDOM.nextInt(SIZE);
        int currentWeightSum = 0;
        for (Criteria currentValue : VALUES) {
            if (randomNum >= currentValue.getWeight() + currentWeightSum) {
                currentWeightSum += currentValue.getWeight();
                continue;
            }
            return currentValue;
        }
        return null;
    }

}
