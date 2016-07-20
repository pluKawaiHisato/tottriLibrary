package library.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dto.BookDto;
import library.entity.Book;
import library.mapper.ReserveMapper;

@Service
public class ReserveService {

	@Autowired
	private ReserveMapper reserveMapper;

	public List<BookDto> getSearchedBook(){
		List<Book> bookList = reserveMapper.getSearchedBook();
		List<BookDto> resultList = convertToDto(bookList);
		return resultList;
	}

	private List<BookDto> convertToDto(List<Book> bookList) {
		List<BookDto> resultList = new LinkedList<BookDto>();
		for (Book entity : bookList) {
			BookDto dto = new BookDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

}
