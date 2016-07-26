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

import library.dto.DocumentDto;
import library.dto.ReserveDto;
import library.dto.SearchDto;
import library.form.BookForm;
import library.form.ReserveForm;
import library.form.SearchForm;
import library.service.ReserveService;

@Controller

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

	@RequestMapping(value = "/manageSearch", method = RequestMethod.GET )
	public String searchedBookList(@ModelAttribute BookForm form, Model model){
		SearchDto dto = new SearchDto();
		BeanUtils.copyProperties(form, dto);
		if(dto.getBookName() == null && dto.getAuthorName() == null && dto.getPublisher() == null && dto.getIsbn() == null
			 && dto.getShelfId() == null && dto.getDocumentId().equals("null")){
			List<DocumentDto> documentName = reserveService.documentName();
			model.addAttribute("Document", documentName);
			model.addAttribute("errorMessage", "検索条件を入力してください");
			return "reserveBook";
		}else{
			List<SearchDto> searchedList = reserveService.getSearchedBook(dto);
			model.addAttribute("searchedList", searchedList);
			SearchForm searchForm = new SearchForm();
			model.addAttribute("bookId",searchForm);
			return "manageSearch";
		}

	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.GET )
	public String checkedList(@ModelAttribute SearchForm searchForm, Model model){

		List<SearchDto> checkedList = reserveService.getCheckBook(searchForm);
		SearchDto.setSearchDto(checkedList);
		model.addAttribute("checkedList", checkedList);
		//ReserveForm reserveForm  = new ReserveForm();
		//model.addAttribute("userId", reserveForm);
		return "manageConfilme";
	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.POST)
	public String reserve(@ModelAttribute ReserveForm reserveForm, Model model){
		List<SearchDto> checkedList = SearchDto.getSearchDto();

		ReserveDto dto = new ReserveDto();
		BeanUtils.copyProperties(reserveForm, dto);

		reserveService.reserveInsert(dto, checkedList);

		return "redirect:reserveBook";
	}





}
