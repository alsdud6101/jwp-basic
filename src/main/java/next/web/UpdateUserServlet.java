package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

//update.jsp 가 호출하는 클래
@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);
	
	//paramter로 값을 받아서 그대로 넣어줈 ㅜ있다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(req.getParameter("userId"),req.getParameter("password"),req.getParameter("name"),req.getParameter("email"));
		log.debug("User : {}",user);
		DataBase.addUser(user);
		resp.sendRedirect("/user/list");
	}
	
	
}
