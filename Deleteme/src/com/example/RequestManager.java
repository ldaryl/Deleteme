package com.example;

import java.util.logging.Logger;

public class RequestManager
{
	private final static Logger logger = Logger.getLogger(RequestManager.class.getName());
	
	private static volatile RequestManager requestManager = null;

	public static RequestManager getInstance()
	{
		if(requestManager == null)
		{
			synchronized(RequestManager.class)
			{
				if(requestManager == null)
				{
					requestManager = new RequestManager();
				}
			}
		}
		return requestManager;
	}

	private RequestManager()
	{
		logger.info("RequestManager.RequestManager() - Test1");
	}
	
	public String submitRequest(String request)
	{
		logger.info("RequestManager.submitRequest() - request = [" + request + "]");
		
		return null;
	}
}