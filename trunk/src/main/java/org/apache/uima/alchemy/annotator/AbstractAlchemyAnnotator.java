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
package org.apache.uima.alchemy.annotator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.Validate;
import org.apache.uima.UimaContext;
import org.apache.uima.alchemy.annotator.exception.AlchemyCallFailedException;
import org.apache.uima.alchemy.digester.DigesterProvider;
import org.apache.uima.alchemy.digester.OutputDigester;
import org.apache.uima.alchemy.digester.ResultDigestingException;
import org.apache.uima.alchemy.digester.UnsupportedResultFormatException;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.utils.exception.MappingException;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.xml.sax.SAXException;

public abstract class AbstractAlchemyAnnotator extends JCasAnnotator_ImplBase {

  private static final String STATUS_OK = "OK";

  private URL alchemyService;

  protected String serviceParams;

  private String[] charsToReplace = { "<", ">", "\"", "'", "&" };

  private OutputDigester digester;

  private DigesterProvider digesterProvider;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {

    digesterProvider = createDigester();

    try {
      this.digester = digesterProvider.getDigester(String.valueOf(aContext
              .getConfigParameterValue("outputMode")));
    } catch (UnsupportedResultFormatException e1) {
      throw new ResourceInitializationException(e1);
    }

    try {
      this.alchemyService = createServiceURI();
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }

    StringBuffer serviceParamsBuf = new StringBuffer();
    serviceParamsBuf.append("&apikey=");
    serviceParamsBuf.append(aContext.getConfigParameterValue("apikey"));

    for (String param : this.getServiceParameters()) {
      serviceParamsBuf.append("&" + param + "=");
      serviceParamsBuf.append(aContext.getConfigParameterValue(param));
    }

    this.serviceParams = serviceParamsBuf.toString();
  }

  private void initializeRuntimeParameters(JCas aJCas) {
    // create parameters string
    StringBuffer serviceParamsBuf = new StringBuffer();
    serviceParamsBuf.append("&text=");
    serviceParamsBuf.append(aJCas.getDocumentText());
    this.serviceParams += (serviceParamsBuf.toString());
  }

  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // initialize service parameters
    initializeRuntimeParameters(aJCas);
    try {
      // open connection and send data
      URLConnection connection = this.alchemyService.openConnection();
      connection.setDoOutput(true);
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection
              .getOutputStream(), "UTF-8"));
      writer.write(this.serviceParams);
      String modifiedText = aJCas.getDocumentText();
      for (int i = 0; i < this.charsToReplace.length; i++) {
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
          mapResultsToTypeSystem(results, aJCas); // annotations from results
        } else {
          throw new AlchemyCallFailedException(results.getStatus());
        }

      } catch (Exception e) {
        throw new ResultDigestingException(e);
      } finally {
        bufByteIn.close();
      }
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    }

  }

  private InputStream parseOutput(URLConnection connection) throws ParserConfigurationException,
          IOException, SAXException, UnsupportedEncodingException {
    BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
    return in;
  }

  public void setDigesterProvider(DigesterProvider digesterProvider) {
    this.digesterProvider = digesterProvider;
  }

  public DigesterProvider getDigesterProvider() {
    return digesterProvider;
  }

  protected abstract DigesterProvider createDigester();

  protected abstract URL createServiceURI() throws MalformedURLException;

  protected abstract String[] getServiceParameters();

  protected abstract void mapResultsToTypeSystem(Results results, JCas aJCas)
          throws MappingException;

}