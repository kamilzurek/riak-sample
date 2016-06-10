package ztbd.riak.domain;

public class SearchBookDto {

	private String title;

	private Long id;

	private String isbn;

	public SearchBookDto(String title, Long id, String isbn) {
		this.title = title;
		this.id = id;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
