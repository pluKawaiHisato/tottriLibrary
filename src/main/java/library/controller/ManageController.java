package library.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.dto.LibraryDto;
import library.dto.ManageDto;
import library.form.ManageForm;
import library.service.ManageService;

@Controller
public class ManageController {
	@Autowired
    private ManageService manageService;


	@RequestMapping(value = "manageLogin", method = RequestMethod.GET)
	public String manageLogin(Model model){
		ManageForm form = new ManageForm();
		model.addAttribute("ManageForm", form);
		return "manageLogin";
	}

	@RequestMapping(value = "/manageLogin", method = RequestMethod.POST)
	public String manageLogin(@Valid @ModelAttribute ManageForm form, BindingResult result, Model model){
		if (result.hasErrors()) {
		System.out.println(result.hasErrors());
			model.addAttribute("message", "ログインできませんでした");
			model.addAttribute("ManageForm", form);
			return "/manageLogin";
		} else {
			System.out.println(result.hasErrors());
			ManageDto dto = new ManageDto();
			BeanUtils.copyProperties(form, dto);
			manageService.getManager(dto);
		}
		return "redirect:home";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model){
		LibraryDto dto = new LibraryDto();
		//ManageService.getLibrary(libraryId);
		model.addAttribute("LibraryName", dto);
		return "home";
	}
}
