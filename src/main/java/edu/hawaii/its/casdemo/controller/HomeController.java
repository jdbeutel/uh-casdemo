package edu.hawaii.its.casdemo.controller;

import edu.hawaii.its.casdemo.access.User;
import edu.hawaii.its.casdemo.action.ActionRecorder;
import edu.hawaii.its.casdemo.security.UserContextService;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ActionRecorder actionRecorder;

    @Autowired
    private UserContextService userContextService;

    @PreAuthorize("hasRole('ROLE_UH')")
    @RequestMapping(value = { "/home" }, method = { RequestMethod.GET})
    public String home(Locale locale, Model model) {

        logger.info("Entered home...");
        
        User user = userContextService.getCurrentUser();
        logger.info("current user    : " + user);
        actionRecorder.publish("employee.view.home", user.getUhuuid());
        model.addAttribute("currentUser", user);
        
        logger.info("Leaving home.");

        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Locale locale, Model model) {
        return "about";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Locale locale, Model model) {
        return "admin";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Locale locale, Model model) {
        return "contact";
    }
    
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(Locale locale, Model model) {
        return "feedback";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(Locale locale, Model model) {
        String message = "Me and my Arrow / Straighter than Narrow";
        model.addAttribute("message", message);

        return "message";
    }

    public void setUserContextService(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    public void setActionRecorder(ActionRecorder actionRecorder) {
        this.actionRecorder = actionRecorder;
    }
}
