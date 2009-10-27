package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.domain.Entities;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;


public class EntityExtractionDigester implements AlchemyOutputDigester{
	
	public Results parseAlchemyXML(String xmlString) throws IOException, SAXException, URISyntaxException {
		Digester digester = new Digester();
		digester.setValidating( false );

		digester.addObjectCreate("results",Results.class);
		digester.addBeanPropertySetter("results/status","status");
		digester.addBeanPropertySetter("results/language","language");
		digester.addObjectCreate("results/entities",Entities.class);
		digester.addObjectCreate("results/entities/entity",Entity.class);
		digester.addBeanPropertySetter("results/entities/entity/type","type");
		digester.addBeanPropertySetter("results/entities/entity/relevance","relevance");
		digester.addBeanPropertySetter("results/entities/entity/count","count");
		digester.addBeanPropertySetter("results/entities/entity/text","text");
		//TODO try to get the disambiguation results
		try {
			
		}
		catch (Exception e) {
			
		}
		digester.addSetNext("results/entities/entity", "addEntity" );
		digester.addBeanPropertySetter("results/entities","entities");
		return (Results)digester.parse(new StringReader(xmlString));
	}

}
