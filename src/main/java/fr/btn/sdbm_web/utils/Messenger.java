package fr.btn.sdbm_web.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class Messenger {
    private Messenger() {

    }

    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
