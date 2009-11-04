package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;


public class AnnotatedEntityExtractionDigester implements AlchemyOutputDigester{
	
	public Results parseAlchemyXML(InputStream stream) throws IOException, SAXException, URISyntaxException {
		Digester digester = new Digester();
		digester.setValidating( false );

		digester.addObjectCreate("results",AnnotatedResults.class);
		digester.addBeanPropertySetter("results/status","status");
		digester.addBeanPropertySetter("results/language","language");
		digester.addBeanPropertySetter("results/text","text");
		digester.addBeanPropertySetter("results/annotatedText","annotatedText");
		return (Results)digester.parse(stream);
	}

}
