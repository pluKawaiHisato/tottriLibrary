package library.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import library.dto.BookDto;
import library.form.BookForm;
import library.service.ReserveService;

@Controller
@SessionAttributes("search")
public class ReserveController {

	@Autowired
	private ReserveService reserveService;

	@RequestMapping(value = "/reserveBook", method = RequestMethod.GET )
	public String booksearch(Model model){
		  BookForm form = new BookForm();

	        model.addAttribute("bookForm", form);
	        //model.addAttribute("message", "");
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
		BookDto dto = new BookDto();
		BeanUtils.copyProperties(form, dto);
		System.out.println(dto.getIsbn());
		if (Integer.toString(dto.getIsbn()) == null){
			dto.setIsbn(0);
		}
		if (Integer.toString(dto.getDocumentId()) == null){
			dto.setDocumentId(0);
		}
		if (Integer.toString(dto.getShelfId()) == null){
			dto.setShelfId(0);
		}
		model.addAttribute("search", dto);
		List<BookDto> bookList = reserveService.getSearchedBook();
		model.addAttribute("bookList", bookList);
		return "manageSearch";
	}
}
