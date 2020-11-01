package business;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import business.dao.BookDAO;

@WebService
@SOAPBinding(style=Style.RPC, use=Use.LITERAL)
public interface ILibrary {

	@WebMethod
	BookDAO getBookDAO();
}
