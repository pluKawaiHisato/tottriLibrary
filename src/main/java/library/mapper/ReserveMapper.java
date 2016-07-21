package library.mapper;

import java.util.List;

import library.dto.DocumentDto;
import library.dto.SearchDto;
import library.entity.Search;

public interface ReserveMapper {

	List<Search> getSearchedBook(SearchDto dto);

	List<DocumentDto> documentName();

	List<Search> getCheckBook(SearchDto dto);
}
