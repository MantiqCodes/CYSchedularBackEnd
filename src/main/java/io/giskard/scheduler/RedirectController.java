package io.giskard.scheduler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
//@RequestMapping("/")
public class RedirectController {
    
    @GetMapping("")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("GSK_SHD", "v1.0");
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
}