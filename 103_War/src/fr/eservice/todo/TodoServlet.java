package fr.eservice.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import fr.eservice.todo.TodoList.ORDER;

public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoList todoList;
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoList = new TodoList();
	}
	
	enum ACTION {
		ADDTASK,
		DELTASK,
		COMPLETETASK,
		LISTTASK
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Complete this code ...
		String _act = req.getParameter("action");
		if ( _act == null ) _act = "listtask";
		ACTION act = ACTION.valueOf( _act.toUpperCase() );
		switch( act ) {
		case ADDTASK:
			// TODO
			break;
		case DELTASK:
			// TODO
			break;
		case COMPLETETASK:
			// TODO
			break;
		case LISTTASK:
			// TODO Complete this code if needed ...
			req.setAttribute("tasks", todoList.getTasks(ORDER.TARGET_DATE));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/www/list_tasks.jsp");
	        dispatcher.forward(req, res);
	        return;
		default:
			res.getWriter().print("You must specify a valid action.");
			return;
		}
	}
	
	
}
