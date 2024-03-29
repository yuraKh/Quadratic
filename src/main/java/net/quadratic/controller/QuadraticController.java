package net.quadratic.controller;

import net.quadratic.config.MessageSourceConfiguration;
import net.quadratic.dto.ParamsDto;
import net.quadratic.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuadraticController {

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private MessageSourceConfiguration message;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", message.getMessage("insert.data.message"));
        model.addAttribute("params", new ParamsDto());
        return "values";
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculate")
    public String calculate(@ModelAttribute("params") ParamsDto params, ModelMap model) {
        model.addAttribute("a", params.getA());
        model.addAttribute("b", params.getB());
        model.addAttribute("c", params.getC());
        model.addAttribute("message", paramsService.save(params));
        return "result";
    }
}
