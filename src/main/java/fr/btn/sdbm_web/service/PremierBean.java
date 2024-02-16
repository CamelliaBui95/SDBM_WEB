package fr.btn.sdbm_web.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;

@Named
@SessionScoped
public class PremierBean implements Serializable {

    @Inject
    private ExternalContext externalContext;

    private String facesPath;

    @PostConstruct
    public void init() {
        facesPath = externalContext.getApplicationContextPath();
        facesPath += "/faces/";
    }

    public String getFacesPath() {
        return facesPath;
    }

    public void setFacesPath(String facesPath) {
        this.facesPath = facesPath;
    }

    public String logOut() {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) externalContext.getRequest();
            httpRequest.logout();

            return "/faces/login/login.xhtml";
        } catch (ServletException servletException ) {
            servletException.printStackTrace();
            return "/faces/login/error.xhtml";
        }
    }
}
