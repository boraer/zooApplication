package org.zoo.data.dataservice.exception;

public class RoomDataServiceException extends Throwable {

	private String message;

	public RoomDataServiceException(String message,Throwable cause)
	{
		super(message, cause);			
	    this.message = message;
		
	}
	
	public RoomDataServiceException(String message)
	{
		super(message);			
	    this.message = message;
		
	}
	
}
