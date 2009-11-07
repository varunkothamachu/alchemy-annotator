package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.domain.Disambiguated;
import org.apache.uima.alchemy.digester.domain.Entities;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;


public class RankedEntityExtractionDigester implements AlchemyOutputDigester{
	
	public Results parseAlchemyXML(InputStream stream) throws IOException, SAXException, URISyntaxException {
		Digester digester = new Digester();
		digester.setValidating( false );

		digester.addObjectCreate("results",EntitiesResults.class);
		digester.addBeanPropertySetter("results/status","status");
		digester.addBeanPropertySetter("results/language","language");
		digester.addObjectCreate("results/entities",Entities.class);
		digester.addObjectCreate("results/entities/entity",Entity.class);
		digester.addBeanPropertySetter("results/entities/entity/type","type");
		digester.addBeanPropertySetter("results/entities/entity/relevance","relevance");
		digester.addBeanPropertySetter("results/entities/entity/count","count");
		digester.addBeanPropertySetter("results/entities/entity/text","text");
		digester.addObjectCreate("results/entities/entity/disambiguated",Disambiguated.class);
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/name","name");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/subType","subType");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/website","website");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/geo","geo");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/dbpedia","dbpedia");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/yago","yago");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/opencyc","opencyc");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/umbel","umbel");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/freebase","freebase");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/ciaFactbook","ciaFactbook");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/census","census");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/geonames","geonames");
		digester.addBeanPropertySetter("results/entities/entity/disambiguated/musicBrainz","musicBrainz");
		digester.addSetNext("results/entities/entity/disambiguated","setDisambiguated");
		digester.addSetNext("results/entities/entity", "addEntity" );
		digester.addSetNext("results/entities","setEntities");
		
		return (Results)digester.parse(stream);
	}

}
