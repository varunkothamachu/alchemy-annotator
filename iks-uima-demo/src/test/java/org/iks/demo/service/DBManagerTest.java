package org.iks.demo.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class DBManagerTest {

  private DBManager dbManager = new DBManager();

  @Test
  public void testSaveDocument() {
    try {
      dbManager
              .saveDocument("String value of the custom field. Thus this is a String for Date and other single valued fields, List of Strings for Multi selects and CustomFieldParams full of Strings for Cascading selects");
    } catch (Exception e) {
      fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void testSaveConcept() {
    try {
      dbManager.saveConcept("String value of the custom field. Thus this is a String for Date and other single valued fields, List of Strings for Multi selects and CustomFieldParams full of Strings for Cascading selects", "http://dbpedia.org/VelocityContext");
    } catch (Exception e) {
      fail(e.getLocalizedMessage());
    }
  }

}
