package db.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception {
	
	private String[] errorMessage;
	private String[] SQLState;
	private int[] errorCode;
	private int caret;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersistenceException(SQLException sqle) {
		caret=0;
		while (sqle != null) {
	        errorMessage[caret] = sqle.getMessage();
	        SQLState[caret] = sqle.getSQLState();
	        errorCode[caret] = sqle.getErrorCode();
	        sqle = sqle.getNextException();
	        caret++;
	      }
	}
	
	public PersistenceException(String message) {
		errorMessage[0] = message;
	}

	public void showMessages() {
		for(int i = 0;i<caret;i++) {
			System.out.println("Error("+errorCode[i]+") message: " + errorMessage[i]+". Produces state: " + SQLState[i]);
		}
	}
}
