/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.wat4method.superherospringmvc.model.Power;
import sg.wat4method.superherospringmvc.model.Super;
import sg.wat4method.superherospringmvc.service.DataIntegrityViolationException;
import sg.wat4method.superherospringmvc.service.DataNotFoundException;
import sg.wat4method.superherospringmvc.service.SuperService;

/**
 *
 * @author Jun
 */
@Controller
public class BridgeController {

    SuperService service;

    @Inject
    public BridgeController(SuperService service) {
        this.service = service;
    }

    //RequestMappings for create/display/edit/delete power links
    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request, Model model) {
        try {
            Power power = new Power();
            power.setName(request.getParameter("name"));
            Power returnPower = service.createPower(power);
            model.addAttribute("returnPower", returnPower);
            return "redirect:displayPowersPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayPowerDetails", method = RequestMethod.GET)
    public String displayPowerDetails(HttpServletRequest request, Model model) {
        try {
            String powerIdParameter = request.getParameter("powerId");
            int powerId = Integer.parseInt(powerIdParameter);

            List<Super> superList = service.getSupersByPowerId(powerId);
            model.addAttribute("superList", superList);

            Power power = service.getPowerById(powerId);
            model.addAttribute("power", power);
            return "powerDetails";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = {"/displayPowersPage"}, method = RequestMethod.GET)
    public String displayPowersPage(Model model) {
        try {
            List<Power> powerList = service.getAllPowers();
            model.addAttribute("powerList", powerList);
            return "powers";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayEditPowerForm", method = RequestMethod.GET)
    public String displayEditPowerForm(HttpServletRequest request, Model model) {
        try {
            String powerIdParameter = request.getParameter("powerId");
            int powerId = Integer.parseInt(powerIdParameter);
            Power power = service.getPowerById(powerId);
            model.addAttribute("power", power);
            return "editPowers";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(@Valid @ModelAttribute("power") Power power, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "editPowers";
            }
            service.editPower(power);
            return "redirect:displayPowersPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        try {
            String powerIdParameter = request.getParameter("powerId");
            int powerId = Integer.parseInt(powerIdParameter);
            service.deletePower(powerId);
            return "redirect:displayPowersPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

}
