package org.apache.uima.alchemy.digester;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.domain.CategorizationResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;

public class TextCategorizationDigester implements AlchemyOutputDigester {

  public Results parseAlchemyXML(InputStream stream) throws IOException, SAXException,
          URISyntaxException {
    Digester digester = new Digester();
    digester.setValidating(false);

    digester.addObjectCreate("results", CategorizationResults.class);
    digester.addBeanPropertySetter("results/status", "status");
    digester.addBeanPropertySetter("results/url", "url");
    digester.addBeanPropertySetter("results/category", "category");
    digester.addBeanPropertySetter("results/score", "score");
    return (Results) digester.parse(stream);
  }

}
