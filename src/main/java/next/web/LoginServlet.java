package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//resp 결과값 저장
	//rep 들오는 값 저장 되어 있음
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		User user = DataBase.findUserById(userId);
		if(user==null) {
			req.setAttribute("loginFailed", true);
			forward("/user/login.jsp",req,resp);
			return;
		}
		if(user.matchPassword(password)) {
			//session 어디와 어디가 연결되어있는지에 대한 정보를 가지고 있다. session 정보 저장
			HttpSession session = req.getSession();
			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
			resp.sendRedirect("/");
			//sendRedirect는 직접 경로 설정해줌 
		}else {
			req.setAttribute("loginFailed", true);
			forward("/user/login.jsp",req,resp);
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("/user/login.jsp",req,resp);
	}
	
	private void forward(String forwardUrl,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
		rd.forward(req, resp);
	}
	
	
}
