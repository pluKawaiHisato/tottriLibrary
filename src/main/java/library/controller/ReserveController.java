package library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.dto.CheckDto;
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
	public String searchedBookList(@Valid @ModelAttribute BookForm form, BindingResult result, Model model){
		SearchDto dto = new SearchDto();
		BeanUtils.copyProperties(form, dto);
		if (result.hasErrors()) {
			List<DocumentDto> documentName = reserveService.documentName();
			model.addAttribute("Document", documentName);
			return "reserveBook";
		}
		if(dto.getBookName() == null && dto.getAuthorName() == null && dto.getPublisher() == null && dto.getIsbn() == null
			 && dto.getShelfId() == null && dto.getDocumentId().equals("null")){
			List<DocumentDto> documentName = reserveService.documentName();
			model.addAttribute("Document", documentName);
			model.addAttribute("errorMessage", "検索条件を入力してください");
			return "reserveBook";
		}else {
			List<SearchDto> searchedList = reserveService.getSearchedBook(dto);
			SearchDto.setSearchDto(searchedList);
			model.addAttribute("searchedList", searchedList);
			return "manageSearch";
		}
	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.GET )
	public String checkedList(@ModelAttribute SearchForm searchForm, Model model){
		if(searchForm.getBookId() == null){
			model.addAttribute("errorMessage", "予約する書籍にチェックを入れてください");
			List<SearchDto> searchedList = SearchDto.getSearchDto();
			model.addAttribute("searchedList", searchedList);
			return "manageSearch";
		}else {
			List<CheckDto> checkedList = reserveService.getCheckBook(searchForm);
			CheckDto.setCheckDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			return "manageConfilme";
		}
	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.POST)
	public String reserve(@Valid @ModelAttribute ReserveForm reserveForm, BindingResult result,CheckDto checkDto,
			Model model){
		List<CheckDto> checkedList = CheckDto.getCheckDto();


		ReserveDto dto = new ReserveDto();
		BeanUtils.copyProperties(reserveForm, dto);

		if(dto.getUserId() == null){
			CheckDto.setCheckDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			model.addAttribute("errorMessage", "ユーザーIDを入力してください");
			return "manageConfilme";
		}
		if (result.hasErrors()) {
			CheckDto.setCheckDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			return "manageConfilme";
		}
		List<String> reserveUser = reserveService.checkUser(dto);

		 if(reserveUser.size() == 0){
			CheckDto.setCheckDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			model.addAttribute("errorMessage", "存在しないユーザーIDです");
			return "manageConfilme";
		}else{
			List<ReserveDto> reservedBook = reserveService.reservedBook(dto,checkedList);
			System.out.println(reservedBook.size());

			if(reservedBook.size() != 0){
				CheckDto.setCheckDto(checkedList);
				model.addAttribute("checkedList", checkedList);
				model.addAttribute("errorMessage", "すでに予約されている図書が含まれています");
				model.addAttribute("message", "予約されている図書");
				model.addAttribute("reserveBook", reservedBook);

				return "manageConfilme";
			}else{
				reserveService.reserveInsert(dto, checkedList);
				return "redirect:reserveBook";
			}
		}
	}
	@RequestMapping(value = "/manageConfilme",params = "cancel", method = RequestMethod.POST)
	public String cancel(@ModelAttribute SearchDto dto, Model model){
		List<SearchDto> searchedList = SearchDto.getSearchDto();
		List<CheckDto> checkedList = CheckDto.getCheckDto();
 		model.addAttribute("searchedList", searchedList);
 		model.addAttribute("checkedList", checkedList);
		return "manageSearch";
	}
}