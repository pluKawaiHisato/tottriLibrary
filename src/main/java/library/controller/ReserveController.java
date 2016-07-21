package library.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import library.dto.DocumentDto;
import library.dto.SearchDto;
import library.form.BookForm;
import library.service.ReserveService;

@Controller
@SessionAttributes("search")
public class ReserveController {

	@Autowired
	private ReserveService reserveService;

	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        // 未入力のStringをnullに設定する
	        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    }

	@RequestMapping(value = "/reserveBook", method = RequestMethod.GET )
	public String booksearch(Model model){
		BookForm form = new BookForm();
		List<DocumentDto> documentName = reserveService.documentName();
		model.addAttribute("Document", documentName);
		model.addAttribute("bookForm", form);
	    return "reserveBook";
	}

//	@RequestMapping (value = "/reserveBook", method = RequestMethod.POST)
//	public String bookSearch (@ModelAttribute BookForm form, Model model){
//		BookDto dto = new BookDto();
//		BeanUtils.copyProperties(form, dto);
//		model.addAttribute("bookForm",  dto);
//		if (Integer.toString(dto.getIsbn()) == ""){
//			dto.setIsbn(0);
//		}
//		if (Integer.toString(dto.getDocumentId()) == ""){
//			dto.setDocumentId(0);
//		}
//		if (Integer.toString(dto.getShelfId()) == ""){
//			dto.setShelfId(0);
//		}
//		model.addAttribute("search", dto);
//
//		return "redirect:manageSearch";
//	}

	@RequestMapping(value = "/manageSearch", method = RequestMethod.GET )
	public String searchedBookList(@ModelAttribute BookForm form, Model model){
		SearchDto dto = new SearchDto();
		BeanUtils.copyProperties(form, dto);
		model.addAttribute("search", dto);
		List<SearchDto> searchedList = reserveService.getSearchedBook(dto);
		model.addAttribute("searchedList", searchedList);
		model.addAttribute("searchForm", form);
		return "manageSearch";
	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.GET )
	public String checkedList(@ModelAttribute SearchDto dto, Model model){
		List<SearchDto> checkedList = reserveService.getCheckBook(dto);
		model.addAttribute("checkedList", checkedList);
		return "manageConfilme";
	}
}
