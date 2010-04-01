/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.iks.demo.annotator;

import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.iks.demo.service.DBManager;
import org.iks.demo.service.exception.ServiceException;

/**
 * CAS Consumer needed to store DBpedia data to a mysql db
 * 
 * @version $Id$
 */
public class DBPediaDataCASConsumer extends JCasAnnotator_ImplBase {

  private DBManager dbManager;

  private final String[] charsToReplace = { "<", ">", "\"", "'", "&", "#", "@" };

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    this.dbManager = new DBManager();
    super.initialize(aContext);
  }

  public void process(JCas cas) throws AnalysisEngineProcessException {
    String modifiedText = cas.getDocumentText();
    for (int i = 0; i < this.charsToReplace.length; i++) {
      modifiedText = modifiedText.replaceAll(this.charsToReplace[i], "");
    }
    try {
      store(modifiedText);
    } catch (ServiceException e) {
      e.printStackTrace();
      throw new AnalysisEngineProcessException(e);
    }
    Iterator<FSIndex<FeatureStructure>> indexesIterator = cas.getFSIndexRepository().getIndexes();
    while (indexesIterator.hasNext()) {
      FSIndex<FeatureStructure> index = indexesIterator.next();
      for (FeatureStructure fs : index) {
        try {
          extractLinkedData(cas, fs);
        } catch (ServiceException e) {
          e.printStackTrace();
          throw new AnalysisEngineProcessException(e);
        }
      }
    }
  }

  /**
   * save the document
   * @param documentText
   * @throws ServiceException
   */
  private void store(String documentText) throws ServiceException {
    try {
      dbManager.saveDocument(documentText);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException(e);
    }
  }

  /**
   * gets DBpedia feature from the passed feature structure and bind it to the doc
   * 
   * @param cas
   * @param fs
   * @throws ServiceException
   */
  private void extractLinkedData(JCas cas, FeatureStructure fs) throws ServiceException {
    Type type = fs.getType();
    Feature featureByBaseName = type.getFeatureByBaseName("dbpedia");
    if (featureByBaseName != null) {

      String dbPediaVal = fs.getFeatureValueAsString(featureByBaseName);
      if (dbPediaVal != null) {
        try {
          String modifiedText = cas.getDocumentText();
          for (int i = 0; i < this.charsToReplace.length; i++) {
            modifiedText = modifiedText.replaceAll(this.charsToReplace[i], "");
          }
          dbManager.saveConcept(modifiedText, dbPediaVal);
        } catch (Exception e) {
          e.printStackTrace();
          throw new ServiceException(e);
        }
      }
    } else {
      System.out.println(type.getName() + " dbpedia feature null");
    }
  }

}
