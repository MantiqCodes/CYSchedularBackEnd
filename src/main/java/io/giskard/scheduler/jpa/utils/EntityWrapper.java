package io.giskard.scheduler.jpa.utils;

import org.springframework.http.HttpStatus;

public class EntityWrapper<T>
{


	  private 	T t;
	  private HttpStatus httpStatus;
	  private String message;


	  
	  public T getT()
	  		{
	    			return t;
	  		}

	  		public void setT(T t)
	  		{
	    			this.t = t;
	  		}

	  		public HttpStatus getHttpStatus()
	  		{
	    			return httpStatus;
	  		}

	  		public void setHttpStatus(HttpStatus httpStatus)
	  		{
	    			this.httpStatus = httpStatus;
	  		}

	  		public String getMessage()
	  		{
	    			return message;
	  		}

	  		public void setMessage(String message)
	  		{
	    			this.message = message;
	  		}


}
