package org.apache.uima.alchemy.annotator;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

public class AlchemySimpleAnnotator extends JCasAnnotator_ImplBase {
	
	private URL alchemyService;
	private char[] serviceParams;
	private String[] charsToReplace = {"<", ">", "\"", "'", "&"};

	public void process(JCas aJCas) throws AnalysisEngineProcessException {

	    try {
	      // open connection and send data
	      URLConnection connection = this.alchemyService.openConnection();
	      connection.setDoOutput(true);
	      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
	      writer.write(this.serviceParams);
	      String modifiedText = aJCas.getDocumentText();
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
