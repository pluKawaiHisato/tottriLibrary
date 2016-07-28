package library.mapper;

import java.util.List;

import library.dto.CheckDto;
import library.dto.DocumentDto;
import library.dto.ReserveDto;
import library.dto.SearchDto;
import library.entity.Reserve;
import library.entity.Search;

public interface ReserveMapper {

	List<Search> getSearchedBook(SearchDto dto);

	List<DocumentDto> documentName();

	List<Search> getCheckBook(CheckDto dto);

	List<String> checkUser(ReserveDto dto);

	void reserveInsert(ReserveDto dto);

	List<Reserve> reservedBook(ReserveDto dto);

}
