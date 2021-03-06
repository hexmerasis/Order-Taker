package com.ordertaker.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ordertaker.service.MenuService;

public class MenuServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5463883064943946331L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/com/ordertaker/resource/applicationContext.xml");
		MenuService menuService = (MenuService) applicationContext.getBean("menuService");
		//HttpSession session = request.getSession();
		String page = "";
		String action = request.getParameter("action");
		
		try {
			if("createmenu".equals(action)) {
				if("all".equals(request.getParameter("param"))) {
					menuService.getDishes(request);
				}
				else {
					menuService.getDishesByDay(request);
				}
				page = "/pages/DisplayDishes.jsp";
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}
