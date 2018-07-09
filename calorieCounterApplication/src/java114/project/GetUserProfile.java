package java114.project;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 *
 *
 *@author    Stanislav Revin
 */
@WebServlet(
    name = "GetUserProfile",
    urlPatterns = { "/getUserProfile" }
)

public class GetUserProfile extends HttpServlet {

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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/userProfile");
            dispatcher.forward(request, response);

    }



}