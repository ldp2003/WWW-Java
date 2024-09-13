package controllers;

import entities.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.AccountService;
import services.LogService;
import services.RoleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name = "ControllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {

    private AccountService accountService;
    private RoleService roleService;
    private LogService logService;
    private Logger logger;
    @Override
    public void init() throws ServletException {
        logger = Logger.getLogger(ControllerServlet.class.getName());
        accountService = new AccountService();
        roleService = new RoleService();
        logService = new LogService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        switch (action.toLowerCase()) {
            // Account
            case "login": {
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");
                Account account = accountService.checkLogin(phone.trim(), password);
                if (account != null) {
                    session.setAttribute("account", account); // Set account into session
                    session.setAttribute("roleName", roleService.getRoleName(account.getAccountId()));
                    if(accountService.checkAdmin(account)){
                        resp.sendRedirect("dashboard.jsp");
                    } else {
                        resp.sendRedirect("userprofile.jsp");
                    }
                } else {
                    req.setAttribute("message", "Login failed");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                }
                break;
            }
            case "logout": {
                Account account = (Account) session.getAttribute("account");
                //logService.updateLog(account.getAccountId());
                session.removeAttribute("account");
                resp.sendRedirect("index.jsp");
                break;
            }
            case "show all account": {
                session.setAttribute("accounts", accountService.getAllAccount());
                resp.sendRedirect("table-account.jsp");
                break;
            }
            case "show accounts by role": {
                String roleName = req.getParameter("roleName");
                session.setAttribute("accounts", accountService.getAccountByRole(roleName));
                resp.sendRedirect("table-account.jsp");
                break;
            }
            default: {
                req.getRequestDispatcher("/page-not-found.jsp").forward(req, resp);
            }
        }
    }

}
