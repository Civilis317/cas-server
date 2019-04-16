package net.playground.casclient.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("Incoming request for index...");
        return new ModelAndView("index");
    }

}
