package org.apache.uima.alchemy.annotator;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

public class AlchemySimpleAnnotator extends JCasAnnotator_ImplBase {
	
	public void process(CAS aCas) throws AnalysisEngineProcessException {

	    try {
	      // open connection and send data
	      URLConnection connection = this.alchemyService.openConnection();
	      connection.setDoOutput(true);
	      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection
	              .getOutputStream(), "UTF-8"));
	      writer.write(this.serviceParams);
	      String modifiedText = aCas.getDocumentText();
	      for(int i = 0; i < this.charsToReplace.length; i++) {
	        modifiedText = modifiedText.replaceAll(this.charsToReplace[i], "");
	      }
	      modifiedText = modifiedText.replaceAll("\n", " ");
	      modifiedText = modifiedText.replaceAll("\r", " ");
	     
	      writer.write(modifiedText);
	      writer.flush();
	      writer.close();

	    }
	    catch (Exception e) {
	    	
	    }

	  }

}
