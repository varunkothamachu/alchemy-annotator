package org.apache.uima.alchemy.annotator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.w3c.dom.Document;

public class AlchemyTextEntityExtractionAnnotator extends JCasAnnotator_ImplBase {
	
	private URL alchemyService;
	private String serviceParams;
	private String[] charsToReplace = {"<", ">", "\"", "'", "&"};
	
	
	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		try {
			this.alchemyService = createServiceURI();
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}

	private void initializeParameters(JCas aJCas) {
		// create service parameter string
	    StringBuffer serviceParamsBuf = new StringBuffer();
	    
	    // param api key
	    serviceParamsBuf.append("&apikey=");
	    serviceParamsBuf.append("");
	    
	    // param text to analyze
	    serviceParamsBuf.append("&text=");
	    serviceParamsBuf.append(aJCas.getDocumentText());
	    
	    // param url
//	    serviceParamsBuf.append("&url=");
//	    serviceParamsBuf.append(url);
	    
	    // param output mode (xml DEFAULT, json, rdf, rel-tag, rel-tag-raw)
	    serviceParamsBuf.append("&outputMode=");
	    serviceParamsBuf.append("xml");
	    
	    // param disambiguate (1 DEFAULT,0)
//	    serviceParamsBuf.append("&disambiguate=");
//	    serviceParamsBuf.append(disambiguate);
	    
	    // param linked data
//	    serviceParamsBuf.append("&linkedData=");
//	    serviceParamsBuf.append(linkedData);
	    
	    // param show source text (0 DEFAULT, 1)
//	    serviceParamsBuf.append("&showSourceText=");
//	    serviceParamsBuf.append(showSourceText);
	    
	    // param base url
//	    serviceParamsBuf.append("&baseUrl=");
//	    serviceParamsBuf.append(baseUrl);
	    
	    this.serviceParams = serviceParamsBuf.toString();
	}

	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//initialize service parameters 
		initializeParameters(aJCas);
		
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
	      
	      //parse output
	      // first get the XML result out of the returned XML
	      DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	      BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
	      Document feedDoc = docBuilder.parse(in);
	      String rdfXmlContent = feedDoc.getDocumentElement().getTextContent();

	    }
	    catch (Exception e) {
	    	
	    }

	  }

	private URL createServiceURI() throws MalformedURLException {
		return URI.create("http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities").toURL();
	}

}
