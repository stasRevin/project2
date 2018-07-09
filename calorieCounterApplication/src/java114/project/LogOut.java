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
    name = "LogOut",
    urlPatterns = { "/logOut" }
)

public class LogOut extends HttpServlet {

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


        System.out.println("Invalidating session #" + session.getId());
        session.invalidate();


        String url = "/java114/logOutConfirmation";

        response.sendRedirect(url);

    }

}