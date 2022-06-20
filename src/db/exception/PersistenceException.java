package db.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersistenceException extends Exception {
	
	private List<String> errorMessage;
	private List<String> SQLState;
	private List<Integer> errorCode;
	private int caret;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersistenceException(SQLException sqle) {
		errorMessage = new ArrayList<String>();
		SQLState = new ArrayList<String>();
		errorCode = new ArrayList<Integer>();
		caret=0;
		while (sqle != null) {
	        errorMessage.add(sqle.getMessage());
	        SQLState.add(sqle.getSQLState());
	        errorCode.add(sqle.getErrorCode());
	        sqle = sqle.getNextException();
	        caret++;
	      }
	}
	
	public PersistenceException(String message) {
		errorMessage = new ArrayList<String>();
		errorMessage.add(message);
	}

	public void showMessages() {
		for(int i = 0;i<caret;i++) {
			System.out.println("Error("+errorCode.get(i)+") message: " + errorMessage.get(i)+". Produces state: " + SQLState.get(i));
		}
	}
}
