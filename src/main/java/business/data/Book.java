package business.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private int bookId;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "authorId")
	private Author author;

    @Column(name = "title")
    private String title;

    @Column(name = "nbrPages")
    private int nbrPages;

	public Book() {
		super();
	}

	public Book(String title, int nbrPages) {
		super();
		this.title = title;
		this.nbrPages = nbrPages;
	}

	public Book(Author author, String title, int nbrPages) {
		super();
		this.author = author;
		this.title = title;
		this.nbrPages = nbrPages;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNbrPages() {
		return nbrPages;
	}

	public void setNbrPages(int nbrPages) {
		this.nbrPages = nbrPages;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
	
	

}
