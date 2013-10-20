package fr.eservice.todo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import fr.eservice.todo.TodoList.ORDER;

public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoList todoList;
	private DateFormat df;
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoList = new TodoList();
		df = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	enum ACTION {
		ADDTASK,
		DELTASK,
		COMPLETETASK,
		LISTTASK
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String _act = req.getParameter("action");
		if ( _act == null ) _act = "listtask";
		ACTION act = ACTION.valueOf( _act.toUpperCase() );
		switch( act ) {
		case ADDTASK:
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			
			Long target = null;
			String input_date = req.getParameter("target");
			if ( input_date == null ) input_date = "";
			input_date.trim();
			try {
				if ( !input_date.isEmpty() ) {
					target = df.parse( input_date ).getTime() / 1000L;
				}
				Task task = Task.create(title, description, target);
				todoList.addTask(task);
			} catch (ParseException e){
				showError( res, "La date que vous avez saisi n'est pas correcte." );
				return;
			} catch (ParameterException pe) {
				showError(res, "Erreur de saisi d'un paramètre.<br/>"+pe.getMessage());
				return;
			}
			((HttpServletResponse)res).sendRedirect("/index.html");
			break;
		case DELTASK:
			int taskRef = getValidTaskRef( res, req.getParameter("tid") );
			if ( taskRef < 0 ) return;
			todoList.removeTask(taskRef);
			((HttpServletResponse)res).sendRedirect("/index.html");
			break;
		case COMPLETETASK:
			taskRef = getValidTaskRef( res, req.getParameter("tid") );
			todoList.completeTask(taskRef);
			((HttpServletResponse)res).sendRedirect("/index.html");
			break;
		case LISTTASK:
			req.setAttribute("tasks", todoList.getTasks(ORDER.TARGET_DATE));
			req.setAttribute("dateFormatter", df);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/www/list_tasks.jsp");
	        dispatcher.forward(req, res);
	        return;
		default:
			res.getWriter().print("You must specify a valid action.");
			return;
		}
	}

	private int getValidTaskRef(ServletResponse res, String reference) {
		if ( reference == null ) reference = "";
		reference.trim();
		if ( reference.isEmpty() ) {
			showError(res, "Vous devez indiquer le numéro de la tache à effacer.");
			return -1;
		}
		int taskRef = 0;
		try {
			taskRef = Integer.parseInt(reference);				
		} catch (NumberFormatException e) {
			showError(res, "Le numéro de tâche doit être un entier valide");
			return -1;
		}
		return taskRef;
	}

	private void showError(ServletResponse res, String errorMessage) {
		res.setContentType("text/html");
		try {
			res.getWriter().print(
				"<html><head><title>Error</title></head><body>" +
				"<h1>Error</h1><b>" + errorMessage + "</b>" +
				"</body></html>"
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
