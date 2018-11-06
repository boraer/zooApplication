package org.zoo.data.dataservice.exception;

public class AnimalDataServiceException extends Throwable {

	private String message;

	public AnimalDataServiceException(String message,Throwable cause)
	{
		super(message, cause);			
	    this.message = message;
		
	}
	
	public AnimalDataServiceException(String message)
	{
		super(message);			
	    this.message = message;
		
	}
	

}
