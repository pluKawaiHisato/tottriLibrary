package library.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class BookForm {
	private int bookId;
	private String bookKanaByte;
	private String bookName;
	private String authorKanaByte;
	private String authorName;
	private String publisher;
	private Date rentalTime;
	@Size(max = 13 , message = "ISBNは13桁以内で入力してください")
	@Min(value =1, message = "ISBNは1以上で入力してください")
	private String isbn;
	@Size(max = 3 , message = "棚番号は3桁以内で入力してください")
	@Min(value=1, message = "棚番号は1以上で入力してください" )
	private String shelfId;
	private String documentId;
	private int libraryId;
	private int status;

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookKanaByte() {
		return bookKanaByte;
	}
	public void setBookKanaByte(String bookKanaByte) {
		this.bookKanaByte = bookKanaByte;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorKanaByte() {
		return authorKanaByte;
	}
	public void setAuthorKanaByte(String authorKanaByte) {
		this.authorKanaByte = authorKanaByte;
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
	public Date getRentalTime() {
		return rentalTime;
	}
	public void setRentalTime(Date rentalTime) {
		this.rentalTime = rentalTime;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}


	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


}