package sdek.supplier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class Default {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getIndexApp(Model model) {
        return "index";
    }
}
