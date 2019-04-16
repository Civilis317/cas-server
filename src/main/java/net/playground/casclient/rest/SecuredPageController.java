package net.playground.casclient.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/secured")
public class SecuredPageController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(SecuredPageController.class);

    @GetMapping
    public ModelAndView index(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            modelMap.put("username", ((UserDetails) auth.getPrincipal()).getUsername());
        }
        return new ModelAndView("secure/index");
    }
}
