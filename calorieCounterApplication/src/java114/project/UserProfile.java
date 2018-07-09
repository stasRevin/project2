package java114.project;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java114.userData.*;
import java114.utilities.*;

/**
 *
 *
 *@author    Stanislav Revin
 */
@WebServlet(
    name = "UserProfile",
    urlPatterns = { "/userProfile" }
)

public class UserProfile extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               the HttpRequest
     *@param  response              the HttpResponse
     *@exception  ServletException  if there is a general
     *                              servlet exception
     *@exception  IOException       if there is a general
     *                              I/O exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        String url = "/userProfile.jsp";


        if (session.getAttribute("userId") == null) {

            response.sendRedirect("");
        }

        String userId = (String) session.getAttribute("userId");

        LoginUser.setUserWorkoutActivity(userId, session);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        doGet(request, response);

    }

}