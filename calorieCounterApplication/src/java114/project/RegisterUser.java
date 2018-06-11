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
    name = "registerUser",
    urlPatterns = { "/registerUser" }
)

@MultipartConfig(
fileSizeThreshold = 1024 * 1024 * 2,
maxFileSize = 1024 * 1024 * 100,
maxRequestSize = 1024 * 1024 * 500
)

public class RegisterUser extends HttpServlet implements PropertiesLoader {

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
        String temporaryDirectoryName = System.getProperty("java.io.tmpdir");


        String firstName = request.getParameter("firstName");

        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String weight = request.getParameter("weight");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Properties properties = loadProperties("/project.properties");

        UserDirectory userDirectory = new UserDirectory(properties);

        String message = userDirectory.addUser(firstName, lastName, gender, birthdate,
                                               weight, username, password);

        System.out.println("MESSAGE: " + message);

        int userId = userDirectory.getUserId(username);
        System.out.println("USER ID: " + userId);
        File userFolder = new File(temporaryDirectoryName + userId);

        if (!userFolder.exists()) {

            userFolder.mkdir();
        }

        for (Part part : request.getParts()) {

            if (part.getName().equals("userPhoto")) {

                String fileName = getFileName(part);
                System.out.println("PART FILE NAME: " + fileName);
                String fileLocation = userFolder + File.separator + fileName;
                part.write(fileLocation);
                String result = userDirectory.addUserPhotoLocation(userId, fileLocation);
                System.out.println(result);

            }
        }

    }


    private String getFileName(Part part) {

        String contentDisplay = part.getHeader("content-disposition");
        String fileName = "";
        String[] contentItems = contentDisplay.split(";");

        for (String contentItem : contentItems) {

            if (contentItem.trim().startsWith("filename")) {

                fileName = contentItem.substring(contentItem.indexOf("=") + 2,
                            contentItem.length() - 1);
                return fileName;
            }

        }

        return fileName;
    }

}