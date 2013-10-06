package fr.eservice.todo;

public class ParameterException extends Exception {
	private static final long serialVersionUID = 1L;

	public String parameterName;
	
	public ParameterException(String parameter, String message) {
		super(message);
		this.parameterName = parameter;
	}
	
}
