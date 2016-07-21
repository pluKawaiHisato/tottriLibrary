package library.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dto.DocumentDto;
import library.dto.SearchDto;
import library.entity.Search;
import library.mapper.ReserveMapper;

@Service
public class ReserveService {

	@Autowired
	private ReserveMapper reserveMapper;

	public List<SearchDto> getSearchedBook(SearchDto dto){
		System.out.println(dto.getShelfId());

		List<Search> searchedList = reserveMapper.getSearchedBook(dto);
		List<SearchDto> resultList = convertToDto(searchedList);
		System.out.println(dto.getShelfId());
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

	public List<SearchDto> getCheckBook(SearchDto dto) {

	    List<Search> checkList = reserveMapper.getCheckBook(dto);
	    List<SearchDto> resultList = convertToDtoCeack(checkList);
	    return resultList;
	}

	private List<SearchDto> convertToDtoCeack(List<Search> checkList) {
		List<SearchDto> resultList = new LinkedList<SearchDto>();
		for (Search entity : checkList) {
			SearchDto dto = new SearchDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}


}
