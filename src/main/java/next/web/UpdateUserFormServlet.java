package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import next.model.User;
@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;//servlet 는  ID 값이 필요하다. (구분하기 위한 id)

	
	//req로 list.jsp에서 넘겨주는 userId값을 받는다 . 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = DataBase.findUserById(req.getParameter("userId"));
		if(!UserSessionUtils.isSameUser(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수저할수 없습니다.");
		}
		req.setAttribute("user",user);
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
		rd.forward(req,resp);
	}
	

	
	
}














