package library.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dto.ManageDto;
import library.entity.Manage;
import library.mapper.ManageMapper;

@Service
public class ManageService {

	@Autowired
    private ManageMapper ManageMapper;

	 public ManageDto getManager(ManageDto dto) {
	        Manage entity = ManageMapper.getManager(dto);
	        BeanUtils.copyProperties(entity, dto);
	        return dto;
	    }

//	 public LibraryDto getLibrary(int LibraryId){
//		 LibraryDto dto = new LibraryDto();
//		 dto.setLibraryId(LibraryId);
//		 Library entity = ManageMapper.getLibrary(dto);
//		 BeanUtils.copyProperties(entity, dto);
//		 return dto;
//	 }

}
