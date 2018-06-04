package java114.calorieCounter;

public class ExerciseActivityCalculatorLauncher {

    public static void main(String[] arguments) {

        ActivityCalculator activityCalculator = new ActivityCalculator();

        int heartRate = Integer.parseInt(arguments[1]);
        int weight = Integer.parseInt(arguments[2]);
        int age = Integer.parseInt(arguments[3]);
        int exerciseDurationTime = Integer.parseInt(arguments[4]);


        if (arguments[0].equals("male")) {

            System.out.printf("You burned: %.2f calories.",
                    activityCalculator.calculateForMale(heartRate,
                                                          weight,
                                                          age,
                                                          exerciseDurationTime));
            System.out.println();

        } else if (arguments[0].equals("female")) {

            System.out.printf("You burned: %.2f calories",
                    activityCalculator.calculateForFemale(heartRate,
                                                            weight,
                                                            age,
                                                            exerciseDurationTime));
            System.out.println();

        }


    }


}