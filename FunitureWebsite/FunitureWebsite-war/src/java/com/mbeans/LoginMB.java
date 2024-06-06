package com.mbeans;

import com.entitybeans.Users;
import com.sessionbeans.UsersFacadeLocal;

import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    @EJB
    private UsersFacadeLocal usersFacade;

    private String username;
    private String password;

    public LoginMB() {
    }


    public String checkLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        // Check if it's admin login
        if ("admin".equals(username) && "123".equals(password)) {
            try {
                context.getExternalContext().getSessionMap().put("loggedInUsername", username);
                context.getExternalContext().redirect("/FunitureWebsite-war/faces/admin/customer.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            // Check if it's user login
            Users user = usersFacade.checkUsers(username, password);
    if (user != null) {
        context.getExternalContext().getSessionMap().put("loggedInUsername", username);
        try {
            context.getExternalContext().redirect("/FunitureWebsite-war/faces/home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
                return null;
            } else {
                // Invalid credentials
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", "Invalid username or password"));
                return "login";
            }
        }
    }
 
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/FunitureWebsite-war/faces/home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoggedInUsername() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUsername");
        
    }

    public boolean isLoggedIn() {
        return getLoggedInUsername() != null;
    }
     public boolean isLoggedIn1() {
        return getLoggedInUsername() != null;
    }
     public String getLoggedInUsername1() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUsername1");
    }
}
