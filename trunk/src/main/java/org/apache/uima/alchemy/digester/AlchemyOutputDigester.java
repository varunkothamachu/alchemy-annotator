package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;

public interface AlchemyOutputDigester {

	public Results parseAlchemyXML(InputStream xmlReader) throws IOException, SAXException, URISyntaxException;
	
}
