package java114.project;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;

import java.util.*;
import java114.userData.*;
import java114.utilities.*;
import java114.calorieCounter.*;


/**
 *
 *
 *@author    Stanislav Revin
 */
@WebServlet(
    name = "LogExercise",
    urlPatterns = { "/logExercise" }
)

public class LogExercise extends HttpServlet {

    /**
     *  Handles HTTP POST requests.
     *
     *@param  request               the HttpRequest
     *@param  response              the HttpResponse
     *@exception  ServletException  if there is a general
     *                              servlet exception
     *@exception  IOException       if there is a general
     *                              I/O exception
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Properties properties = (Properties) session.getAttribute("properties");
        UserDirectory userDirectory = new UserDirectory(properties);

        String id = (String) session.getAttribute("userId");
        System.out.println("Servlet ID: " + id);

        String typeInput = request.getParameter("type");
        String dateInput = request.getParameter("date");
        String timeInput = request.getParameter("time");
        String heartRateInput = request.getParameter("heartRate");

        DataConverter dataConverter = new DataConverter();
        User user = (User) session.getAttribute("user");

        int heartRate = dataConverter.convertToInt(heartRateInput);
        int weight = dataConverter.convertToInt(user.getWeight());
        Date birthdate = dataConverter.convertToDate(user.getBirthdate());
        int exerciseDurationTime = dataConverter.convertToInt(timeInput);
        int caloriesBurned = 0;

        int age = calculateAge(birthdate);
        System.out.println("AGE: " + age);

        ActivityCalculator activityCalculator = new ActivityCalculator();

        String gender = user.getGender();

        if (gender.equals("f") ) {

            caloriesBurned = (int)
                    activityCalculator.calculateForFemale(heartRate, weight, age,
                                                  exerciseDurationTime);

        } else if (gender.equals("m")) {

            caloriesBurned = (int)
                    activityCalculator.calculateForMale(heartRate, weight, age,
                                                  exerciseDurationTime);

        }


        int userId = userDirectory.getUserId("kesha");
        System.out.println("userID of kesha: " + userId);
        String message = userDirectory.logWorkout(id, dateInput, typeInput,
                                                  timeInput, heartRateInput,
                                                  caloriesBurned);

        System.out.println("EXERCISE LOG: " + message);

        String url = "/userProfile";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


    private int calculateAge(Date birthdateInput) {

        int age = 0;
        System.out.println("Birthdate Date: " + birthdateInput);
        LocalDate birthdate = birthdateInput.toLocalDate();
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

        System.out.println("LocalDate birthdate: " + birthdate);
        System.out.println("localDate: " + localDate);


        if (birthdateInput != null && localDate != null) {

            age = Period.between(birthdate, localDate).getYears();
            System.out.println("calculating between, age is " + age);

        }

        System.out.println("AGE IN calculateAge: " + age);
        return age;

    }

}