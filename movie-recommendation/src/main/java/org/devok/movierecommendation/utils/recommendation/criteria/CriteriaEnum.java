package org.devok.movierecommendation.utils.recommendation.criteria;


import java.util.List;
import java.util.Random;

public enum CriteriaEnum {
    GENRE(35, "Genre"),
    DIRECTOR(25, "Director"),
    CAST(15, "Cast"),
    RELEASE_DATE(10, "Release Date"),
    TRENDING(15, "Trending");

    private final int weight;
    private final String label;

    CriteriaEnum(int weight, String label) {
        this.weight = weight;
        this.label = label;
    }

    private int getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }

    private static final List<CriteriaEnum> VALUES =
            List.of(values());

    private static int sumWeights() {
        int sum = 0;
        for (CriteriaEnum value : VALUES) {
            sum += value.getWeight();
        }
        return sum;
    }

    private static final int TOTAL_WEIGHT = sumWeights();
    private static final Random RANDOM = new Random();

    public static CriteriaEnum randomType() {
        int randomNum = RANDOM.nextInt(TOTAL_WEIGHT);
        int currentWeightSum = 0;
        for (CriteriaEnum currentValue : VALUES) {
            if (randomNum >= currentValue.getWeight() + currentWeightSum) {
                currentWeightSum += currentValue.getWeight();
                continue;
            }
            return currentValue;
        }
        return CriteriaEnum.TRENDING;
    }
}
