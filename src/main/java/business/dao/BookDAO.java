package business.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import business.data.Book;
import business.factory.HibernateUtil;

public class BookDAO {
	
	public static Book getAllBooks() {
		List < Book > books = new ArrayList<Book>();
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
			// start a transaction
            transaction = session.beginTransaction();
            books = session.createQuery("from Book", Book.class).list();
            int i = 1;
            System.out.println("---------------------------------------------");
        	System.out.println("Fetch all books");
            for( Book bookIteration : books ) {
                System.out.println("Index : "+i+" - Title : "+bookIteration.getTitle()+" - Author : "+bookIteration.getAuthor().getFirstname()+" "+bookIteration.getAuthor().getLastname());
                i++;
            }
		System.out.println("BOOK 0 :"+books.get(0).getTitle());
		return books.get(0);
	}
	
	public static void getBooksByTitle(String title) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
            transaction = session.beginTransaction();
            List < Book > books = session.createQuery("from Book WHERE title='"+title+"'", Book.class).list();
            int i = 1;
            System.out.println("---------------------------------------------");
        	System.out.println("Fetch books by title");
            for( Book bookIteration : books ) {
                System.out.println("Index : "+i+" - Title : "+bookIteration.getTitle()+" - Author : "+bookIteration.getAuthor().getFirstname()+" "+bookIteration.getAuthor().getLastname());
                i++;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
	public static void getBooksByAuthor(String name) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
            transaction = session.beginTransaction();
            List < Book > books = session.createQuery("from Book a WHERE a.author.authorId = a.bookId AND a.author.firstname='"+name+"'", Book.class).list();
            int i = 1;
            System.out.println("---------------------------------------------");
        	System.out.println("Fetch books by author's firstname");
            for( Book bookIteration : books ) {
                System.out.println("Index : "+i+" - Title : "+bookIteration.getTitle()+" - Author : "+bookIteration.getAuthor().getFirstname()+" "+bookIteration.getAuthor().getLastname());
                i++;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

}
