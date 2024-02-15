package fr.btn.sdbm_web.jaas;

import fr.btn.sdbm_web.service.PremierBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;

@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    @Inject
    FacesContext facesContext;
    @Inject
    ExternalContext externalContext;
    @Inject
    private PremierBean premierBean;
    private String originalURI;

    @PostConstruct
    public void init() {
        originalURI = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

        originalURI = originalURI == null ? premierBean.getFacesPath() + "index.html" : originalURI;
        originalURI = originalURI.substring(originalURI.indexOf("/faces"));

        if (originalQuery != null) {
            originalURI += "?" + originalQuery;
        }
    }

    public String login() {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) externalContext.getRequest();
            httpRequest.login(username, password);

            return originalURI;
        } catch (ServletException servletException ) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une erreur s'est produite : Login failed", null));

            return premierBean.getFacesPath() + "login/error.xhtml";
        }

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
}
