package java114.calorieCounter;

public class ActivityCalculator {

    public double calculateForMale(int heartRate, int weight, int age, int exerciseDurationTime) {

        double exerciseActivity
                = ((-55.0969 + (0.6309 * heartRate) + (0.090174 * weight)
                    + (0.2017 * age)) / 4.184) * exerciseDurationTime;

        return exerciseActivity;

    }

    public double calculateForFemale(int heartRate, int weight, int age, int exerciseDurationTime) {

       double exerciseActivity
            = ((-20.4022 + (0.4472 * heartRate) - (0.057288 * weight)
                + (0.074 * age)) / 4.184) * exerciseDurationTime;

        return exerciseActivity;
    }

}