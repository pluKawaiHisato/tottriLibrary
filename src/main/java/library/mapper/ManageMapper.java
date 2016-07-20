package library.mapper;

import library.dto.ManageDto;
import library.entity.Manage;

public interface ManageMapper {
	Manage getManager(ManageDto dto);

	//Library getLibrary(LibraryDto dto);
}


