package org.apache.uima.alchemy.digester;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.apache.uima.alchemy.digester.domain.Results;

public class RankedEntityExtractionDigesterTest extends TestCase {

  public void testParseAlchemyXML() {
    try {
      String xmlString = "<results><status>REQUEST_STATUS</status><language>DOCUMENT_LANGUAGE</language>"
              + "<url>REQUESTED_URL</url><text>DOCUMENT_TEXT</text><entities><entity><type>DETECTED_TYPE</type>"
              + "<relevance>DETECTED_RELEVANCE</relevance><count>DETECTED_COUNT</count><text>DETECTED_ENTITY</text>"
              + "<disambiguated><name>DISAMBIGUATED_ENTITY</name><subType>ENTITY_SUBTYPE</subType><website>WEBSITE</website>"
              + "<geo>LATITUDE LONGITUDE</geo><dbpedia>LINKED_DATA_DBPEDIA</dbpedia><yago>LINKED_DATA_YAGO</yago>"
              + "<opencyc>LINKED_DATA_OPENCYC</opencyc><umbel>LINKED_DATA_UMBEL</umbel><freebase>LINKED_DATA_FREEBASE</freebase>"
              + "<ciaFactbook>LINKED_DATA_FACTBOOK</ciaFactbook><census>LINKED_DATA_CENSUS</census><geonames>LINKED_DATA_GEONAMES</geonames>"
              + "<musicBrainz>LINKED_DATA_MUSICBRAINZ</musicBrainz></disambiguated><quotations><quotation>ENTITY_QUOTATION</quotation>"
              + "</quotations></entity></entities></results>";
      RankedEntityExtractionDigester digester = new RankedEntityExtractionDigester();
      Results results = digester.parseAlchemyXML(new ByteArrayInputStream(xmlString.getBytes()));
      assertTrue(results != null);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
