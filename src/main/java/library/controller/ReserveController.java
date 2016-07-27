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
		}else if(dto.getIsbn().matches("-.*") || dto.getShelfId().matches("-.*")){
			List<DocumentDto> documentName = reserveService.documentName();
			model.addAttribute("Document", documentName);
			model.addAttribute("errorMessage", "ISBNと棚番号に\"-\"は使用できません");
			return "reserveBook";
		} else {
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
			List<SearchDto> checkedList = reserveService.getCheckBook(searchForm);
			SearchDto.setSearchDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			return "manageConfilme";
		}
	}

	@RequestMapping(value = "/manageConfilme", method = RequestMethod.POST)
	public String reserve(@ModelAttribute ReserveForm reserveForm, SearchDto searchDto, Model model){
		List<SearchDto> checkedList = SearchDto.getSearchDto();


		ReserveDto dto = new ReserveDto();
		BeanUtils.copyProperties(reserveForm, dto);


		if(dto.getUserId() == null){
			SearchDto.setSearchDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			model.addAttribute("errorMessage", "ユーザーIDを入力してください");
			return "manageConfilme";
		}
		List<String> reserveUser = reserveService.checkUser(dto);

		 if(reserveUser.size() == 0){
			SearchDto.setSearchDto(checkedList);
			model.addAttribute("checkedList", checkedList);
			model.addAttribute("errorMessage", "存在しないユーザーIDです");
			return "manageConfilme";
		}else{
			List<ReserveDto> reservedBook = reserveService.reservedBook(dto,checkedList);
			System.out.println(reservedBook.size());

			if(reservedBook.size() != 0){
				SearchDto.setSearchDto(checkedList);
				model.addAttribute("checkedList", checkedList);
				model.addAttribute("errorMessage", "すでに予約された図書が含まれています");
				model.addAttribute("reserveBook", reservedBook);

				return "manageConfilme";
			}else{
				reserveService.reserveInsert(dto, checkedList);
				return "redirect:reserveBook";
			}
		}
	}

}
