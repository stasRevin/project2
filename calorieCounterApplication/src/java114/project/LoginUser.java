package java114.project;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.util.*;
import java114.userData.*;
import java114.utilities.*;


/**
 *
 *
 *@author    Stanislav Revin
 */
@WebServlet(
    name = "LoginUser",
    urlPatterns = { "/loginUser" }
)

public class LoginUser extends HttpServlet implements PropertiesLoader {

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

        HttpSession session = request.getSession();


        System.out.println("This session ID: " + session.getId());
        System.out.println("Authentication scheme: " + request.getAuthType());

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Properties properties = loadProperties("/project.properties");
        session.setAttribute("properties", properties);
        UserDirectory userDirectory = new UserDirectory(properties);

        User user = userDirectory.login(username, password);

        String photoFileLocation = user.getPhotoLocation();

        String url = null;
        String userId = "";

        if (Objects.nonNull(user)) {

            userId = user.getUserId();
            session.setAttribute("user", user);
            session.setAttribute("userId", userId);

            url = "/java114/userProfile";

        } else {

            System.out.println("Password and/or username were incorrect.");

        }


        LoginUser.setUserWorkoutActivity(userId, session);
        String photoPath = prepareUsersPhoto(photoFileLocation, userId);
        session.setAttribute("photoPath", photoPath);

        response.sendRedirect(url);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        doPost(request, response);

    }


    private String prepareUsersPhoto(String photoFileLocation, String userId) {

        String staticImage = "/Users/stanislavrevin/tomcat/webapps/java114/images/"
                + userId + "/";
        File imageDirectory = new File(staticImage);
        System.out.println("PHOTO Location: " + photoFileLocation);
        String imageFile = ""
                + photoFileLocation.substring(photoFileLocation.lastIndexOf("/"));
        String temporaryDirectory = System.getProperty("java.io.tmpdir");

        Path source = Paths.get(temporaryDirectory + userId + "/" + imageFile);
        Path target = Paths.get(staticImage + imageFile);

        SimpleVisitor simpleVisitor = new SimpleVisitor(target, source);

        if (!imageDirectory.exists()) {

            System.out.println("creating directory for images...");
            System.out.println(imageDirectory.mkdir());
        }

        try {

             Files.walkFileTree(source, simpleVisitor);

        } catch (IOException inputOutputException) {

            inputOutputException.printStackTrace();
        }

        System.out.println("IMAGES: " + imageFile);
        System.out.println("IMAGE DIRECTORY: " + imageDirectory);

        return "images/" + userId + imageFile;
    }


    public static void setUserWorkoutActivity(String userId, HttpSession session) {

        LoginUser loginUser = new LoginUser();
        Properties properties = loginUser.loadProperties("/project.properties");
        UserDirectory userDirectory = new UserDirectory(properties);

        List userWorkoutList
                = userDirectory.getWorkout(Integer.parseInt(userId));

        WorkoutList workoutList = new WorkoutList();

        workoutList.setWorkoutList(userWorkoutList);
        session.setAttribute("workoutList", workoutList);
    }

}