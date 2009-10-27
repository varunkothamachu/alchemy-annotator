package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;

public interface AlchemyOutputDigester {

	public Results parseAlchemyXML(String xmlString) throws IOException, SAXException, URISyntaxException;
	
}
