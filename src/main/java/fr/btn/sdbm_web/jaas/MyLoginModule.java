package fr.btn.sdbm_web.jaas;

import fr.btn.sdbm_web.metier.Utilisateur;
import fr.btn.sdbm_web.service.UtilisateurBean;
import jakarta.inject.Inject;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLoginModule implements LoginModule {
    @Inject
    private UtilisateurBean utilisateurBean;
    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private List<String> userGroups;
    private Map options;
    private Map sharedState;
    private boolean debug = false;
    private static final Logger logger = Logger.getLogger(MyLoginModule.class.getName());

    private String username = null;
    private String password = null;

    private boolean isAuthenticated = false;
    private boolean commitSucceeded = false;

    public MyLoginModule() {
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
        this.options = options;
        this.sharedState = sharedState;
        this.userGroups = new ArrayList<>();

        if ("true".equalsIgnoreCase((String) options.get("debug"))) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
            debug = true;
        }

        if(utilisateurBean == null)
            utilisateurBean = new UtilisateurBean();
    }

    @Override
    public boolean login() throws LoginException {
        if (handler == null) {
            throw new LoginException("Error: no CallbackHandler available to recieve authentication information from the user");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {

            handler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            if (debug) {
                logger.log(Level.INFO, "Username: {0}", username);
                logger.log(Level.INFO, "Password: {0}", password);
            }
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new LoginException("Data specified had null values");
            }

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNomLogin(username);
            utilisateur.setPassword(password);
            utilisateurBean.setCurrentUtilisateur(utilisateur);


            if (utilisateurBean.isValid()) {
                userGroups.add(utilisateurBean.getCurrentUtilisateur().getRole());
                isAuthenticated = true;
                return true;
            }

            throw new LoginException("Authentication failed");

        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (!isAuthenticated) {
            return false;
        } else {

            userPrincipal = new UserPrincipal(username);
            subject.getPrincipals().add(userPrincipal);

            if (userGroups != null && !userGroups.isEmpty()) {
                for (String groupName : userGroups) {
                    rolePrincipal = new RolePrincipal(groupName);
                    subject.getPrincipals().add(rolePrincipal);
                }
            }

            commitSucceeded = true;

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (!isAuthenticated)
            return false;
        if (isAuthenticated && !commitSucceeded) {
            isAuthenticated = false;
            username = null;
            password = null;
            userPrincipal = null;

        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        isAuthenticated = commitSucceeded;
        subject.getPrincipals().clear();
        utilisateurBean.setCurrentUtilisateur(new Utilisateur());
        return true;
    }
}
