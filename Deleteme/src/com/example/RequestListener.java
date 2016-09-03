package com.example;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RequestListener")
public class RequestListener extends HttpServlet
{
	private final static Logger logger = Logger.getLogger(RequestListener.class.getName());
	private static FileHandler fileHandler = null;
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException
	{
		logger.info("RequestListener.init() - Testing1");
		
		try
		{
			fileHandler = new FileHandler("D:/Program Files/eclipse-neon/workspace/Async/WebContent/WEB-INF/logs/loggerExample.log", false);
			logger.addHandler(fileHandler);
		}
		catch (SecurityException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void destroy()
	{
		fileHandler.close();
		logger.info("RequestListener.destroy() - Testing1");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.info("RequestListener.doGet() - Testing1");
		
		HttpSession httpSession = request.getSession();
		logger.info("RequestListener.doGet() - Session Id = [" + httpSession.getId() + "]");
		
		Thread thread = Thread.currentThread();
		long threadId = thread.getId();
		String threadName = thread.getName();
		logger.info("RequestListener.doGet() - id/name = [" + threadId + "/" + threadName + "]");
		logger.info("RequestListener.doGet() - threadString = [" + thread.toString() + "]");
		
		RequestManager requestManager = RequestManager.getInstance();
		requestManager.submitRequest(thread.toString());
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
