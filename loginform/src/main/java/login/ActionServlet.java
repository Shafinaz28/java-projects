package login;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/action")
public class ActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Map<String, String> users = new HashMap<>();

    public ActionServlet() {
        super();
        users.put("user1", "password1"); // Sample users
        users.put("user2", "password2");
    }

    protected void doGet(HttpRequest request, HttpResponse response) throws ServletException, IOException {
        response.getWriter().write("This servlet only supports POST requests. Please use POST.");
    }

    protected void doPost(HttpRequest request, HttpResponse response) throws ServletException, IOException {
        String action = ((Object) request).getParameter("action");

        switch (action) {
            case "login":
                handleLogin(request, response);
                break;
            case "register":
                handleRegister(request, response);
                break;
            case "forgotpassword":
                handleForgotPassword(request, response);
                break;
            default:
                response.getWriter().write("Invalid action");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            response.getWriter().write("Login successful for user: " + username);
        } else {
            response.getWriter().write("Invalid credentials");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username)) {
            response.getWriter().write("Username already exists");
        } else {
            users.put(username, password);
            response.getWriter().write("User registered successfully: " + username);
        }
    }

    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");

        if (users.containsKey(username)) {
            response.getWriter().write("Password reset instructions sent to registered email for user: " + username);
        } else {
            response.getWriter().write("Username not found");
        }
    }
}
