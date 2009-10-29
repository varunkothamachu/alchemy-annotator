package org.apache.uima.alchemy.annotator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.Validate;
import org.apache.uima.UimaContext;
import org.apache.uima.alchemy.annotator.exception.AlchemyCallFailedException;
import org.apache.uima.alchemy.annotator.exception.ResultDigestingException;
import org.apache.uima.alchemy.digester.AlchemyOutputDigester;
import org.apache.uima.alchemy.digester.EntityExtractionDigester;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.utils.Alchemy2TypeSystemMapper;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.xml.sax.SAXException;

public class AlchemyTextEntityExtractionAnnotator extends JCasAnnotator_ImplBase {

	private static final String STATUS_OK = "OK";
	private URL alchemyService;
	private String serviceParams;
	private String[] charsToReplace = {"<", ">", "\"", "'", "&"};
	private AlchemyOutputDigester digester;

	@Override
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		this.digester = new EntityExtractionDigester();

		try {
			this.alchemyService = createServiceURI();
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		StringBuffer serviceParamsBuf = new StringBuffer();
		serviceParamsBuf.append("&apikey=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("apikey"));
		// param output mode (default xml)
		serviceParamsBuf.append("&outputMode=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("outputMode"));
		// param disambiguate (1 DEFAULT,0)
		serviceParamsBuf.append("&disambiguate=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("disambiguate"));
		// param linked data
		serviceParamsBuf.append("&linkedData=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("linkedData"));
		// param show source text (0 DEFAULT, 1)
		serviceParamsBuf.append("&showSourceText=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("showSourceText"));
		// param base url
		serviceParamsBuf.append("&baseUrl=");
		serviceParamsBuf.append(aContext.getConfigParameterValue("baseUrl"));
		this.serviceParams = serviceParamsBuf.toString();
	}

	private void initializeRuntimeParameters(JCas aJCas) {
		// create service parameter string
		StringBuffer serviceParamsBuf = new StringBuffer();
		// param text to analyze
		serviceParamsBuf.append("&text=");
		serviceParamsBuf.append(aJCas.getDocumentText());
		this.serviceParams+=(serviceParamsBuf.toString());
	}

	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//initialize service parameters 
		initializeRuntimeParameters(aJCas);
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

			InputStream bufByteIn = parseOutput(connection);

			// map alchemy api results to UIMA type system
			try {
				Results results = this.digester.parseAlchemyXML(bufByteIn);
				Validate.notNull(results);
				Validate.notNull(results.getStatus());
				if (results.getStatus().equalsIgnoreCase(STATUS_OK)) {
					Alchemy2TypeSystemMapper.mapEntities(results,aJCas); //create annotations from results
				}
				else {
					throw new AlchemyCallFailedException(results.getStatus());
				}
				
			} catch (Exception e) {
				throw new ResultDigestingException(e);
			}
			finally{
				bufByteIn.close();
			}
		}
		catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}

	}

	private InputStream parseOutput(URLConnection connection)
	throws ParserConfigurationException, IOException, SAXException,
	UnsupportedEncodingException {
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
		return in;
	}

	private URL createServiceURI() throws MalformedURLException {
		return URI.create("http://access.alchemyapi.com/calls/text/TextGetRankedNamedEntities").toURL();
	}

}
