package java114.project;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Properties properties = loadProperties("/project.properties");
        UserDirectory userDirectory = new UserDirectory(properties);

        User user = userDirectory.login(username, password);
        String photoFileLoaction = user.getPhotoLocation();


        String url = null;

        if (Objects.nonNull(user)) {

            System.out.println("username: " + user.getUsername());
            System.out.println("firstName: " + user.getFirstName());
            System.out.println("lastName: " + user.getLastName());
            System.out.println("gender: " + user.getGender());
            System.out.println("birthdate: " + user.getBirthdate());
            System.out.println("weight: " + user.getWeight());

            session.setAttribute("user", user);

            url = "/userProfile.jsp";

        } else {

            System.out.println("Password and/or username were incorrect.");

        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);


    }

}