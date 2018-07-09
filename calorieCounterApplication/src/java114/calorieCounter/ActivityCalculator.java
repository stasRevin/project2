package java114.calorieCounter;

public class ActivityCalculator {

    public long calculateForMale(int heartRate, int weight, int age, int exerciseDurationTime) {

        long result = 0;

        double exerciseActivity
                = ((-55.0969 + (0.6309 * heartRate) + (0.090174 * weight)
                    + (0.2017 * age)) / 4.184) * exerciseDurationTime;

        result = Math.round(exerciseActivity);
        return result;

    }

    public long calculateForFemale(int heartRate, int weight, int age, int exerciseDurationTime) {

       long result = 0;

       double exerciseActivity
            = ((-20.4022 + (0.4472 * heartRate) - (0.057288 * weight)
                + (0.074 * age)) / 4.184) * exerciseDurationTime;
       result = Math.round(exerciseActivity);

       return result;
    }

}