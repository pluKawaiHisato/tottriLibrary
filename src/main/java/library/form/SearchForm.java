package library.form;

public class SearchForm {
	private int bookId;
	private String isbn;
	private String bookName;
	private String authorName;
	private String shelfId;
	private String documentName;
	private String statusName;
	private String libraryName;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
}
