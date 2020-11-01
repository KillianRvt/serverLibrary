package business.endpoint;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import business.dao.BookDAO;
import business.data.Author;
import business.data.Book;
import business.factory.HibernateUtil;
import business.webservice.LibraryImpl;

public class LibraryPublisher {
	
	

	public static void main(String[] args) {

		String URL = "http://localhost:9998/ws/library";
		Endpoint.publish(URL, new LibraryImpl());
		
		// Initisation des objets 
		Author author = new Author("John", "Doe");
		Author author2 = new Author("Auteur", "Cool");
		Book book = new Book(author, "Ramesh", 1);
		Book book2 = new Book(author2, "Toto", 1);
		
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book objects
            session.save(book);
            session.save(book2);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // Method to fetch books 
        BookDAO.getAllBooks();
        //getBooksByTitle("Toto");
        //getBooksByAuthor("Auteur");

        // Method to fetch authors 
        //getAllAuthors(transaction);
        
        //insertAuthor("Malo","Dupont");
        //getAllAuthors(transaction);
	}
	
	private static void getAllAuthors(Transaction transaction) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Author > authors = session.createQuery("from Author", Author.class).list();
            int i = 1;
            System.out.println("---------------------------------------------");
        	System.out.println("Fetch all authors");
            for( Author authorIteration : authors ) {
                System.out.println("Index : "+i+" - Firstname : "+authorIteration.getFirstname()+" - Lastname : "+authorIteration.getLastname());
                i++;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
	private static void insertAuthor(String firstname, String lastname) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            Author author = new Author(firstname,lastname);
            // save the author object
            session.save(author);
            // commit transaction
            transaction.commit();
			
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

}
