package library.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dto.CheckDto;
import library.dto.DocumentDto;
import library.dto.ReserveDto;
import library.dto.SearchDto;
import library.entity.Reserve;
import library.entity.Search;
import library.form.SearchForm;
import library.mapper.ReserveMapper;

@Service
public class ReserveService {

	@Autowired
	private ReserveMapper reserveMapper;

	public List<SearchDto> getSearchedBook(SearchDto dto){
		List<Search> searchedList = reserveMapper.getSearchedBook(dto);
		List<SearchDto> resultList = convertToDto(searchedList);
		return resultList;
	}

	private List<SearchDto> convertToDto(List<Search> searchedList) {
		List<SearchDto> resultList = new LinkedList<SearchDto>();
		for (Search entity : searchedList) {
			SearchDto dto = new SearchDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	public List<DocumentDto> documentName() {
		List<DocumentDto> documentName = reserveMapper.documentName();
		return documentName;
	}
	public List<CheckDto> getCheckBook(SearchForm searchForm) {
		List<Search> checkList = new ArrayList<Search>();
		for (int i = 0; i < searchForm.getBookId().length ; i++){
			CheckDto dto = new CheckDto();
			dto.setBookId(searchForm.getBookId()[i]);
			checkList.addAll(reserveMapper.getCheckBook(dto));
		}

	    List<CheckDto> resultList = convertToDtoCeack(checkList);
	    return resultList;
	}

	private List<CheckDto> convertToDtoCeack(List<Search> checkList) {
		List<CheckDto> resultList = new LinkedList<CheckDto>();
		for (Search entity : checkList) {
			CheckDto dto = new CheckDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	public List<String> checkUser(ReserveDto dto) {
		List<String> userId = reserveMapper.checkUser(dto);
		return userId;
	}

	public  void reserveInsert(ReserveDto dto, List<CheckDto> checkedList){
		for(CheckDto check : checkedList){
		dto.setIsbn(check.getIsbn());
		dto.setBookId(check.getBookId());
		dto.setLibraryId(check.getLibraryId());
		reserveMapper.reserveInsert(dto);
		}

	}

	public List<ReserveDto> reservedBook(ReserveDto dto, List<CheckDto> checkedList) {
		List<Reserve> bookName = new ArrayList<Reserve>();
		for(CheckDto check : checkedList){
			dto.setIsbn(check.getIsbn());
			dto.setBookName(check.getBookName());
			bookName.addAll(reserveMapper.reservedBook(dto));
		}
		List<ReserveDto> resultList = convertToDtoReserved(bookName);
		return resultList;
	}

	private List<ReserveDto> convertToDtoReserved(List<Reserve> bookName) {
		List<ReserveDto> resultList = new LinkedList<ReserveDto>();
		for (Reserve entity : bookName) {
			ReserveDto dto = new ReserveDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

}
