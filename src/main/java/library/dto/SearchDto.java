package library.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("session")
public class SearchDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private static List<SearchDto> searchDto;

	private int bookId;
	private String isbn;
	private String bookName;
	private String authorName;
	private String publisher;
	private String shelfId;
	private String documentName;
	private String statusName;
	private int libraryId;
	private String libraryName;
	private String documentId;

	public static  List<SearchDto> getSearchDto() {
		if (searchDto == null) {
			searchDto = new ArrayList<SearchDto>();
		}
		return searchDto;
	}
	public static void setSearchDto(List<SearchDto> DtoList) {
		searchDto = DtoList;
	}

	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public int getLibraryId() {
		return libraryId;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}


}
