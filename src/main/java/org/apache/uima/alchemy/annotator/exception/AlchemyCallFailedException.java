package org.apache.uima.alchemy.annotator.exception;

public class AlchemyCallFailedException extends Exception {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  public AlchemyCallFailedException(String status) {
    super(status);
  }

}
