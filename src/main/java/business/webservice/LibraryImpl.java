package business.webservice;

import javax.jws.WebService;

import business.ILibrary;
import business.dao.BookDAO;

@WebService(endpointInterface = "business.ILibrary")
public class LibraryImpl implements ILibrary {

	public BookDAO getBookDAO() {
		BookDAO bookDAO = new BookDAO();
		return bookDAO;
		
	}
}
