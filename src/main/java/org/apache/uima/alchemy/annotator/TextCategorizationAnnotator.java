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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.uima.alchemy.digester.DigesterProvider;
import org.apache.uima.alchemy.digester.categorization.CategorizationDigesterProvider;
import org.apache.uima.alchemy.digester.domain.CategorizationResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.utils.Alchemy2TypeSystemMapper;
import org.apache.uima.alchemy.utils.exception.MappingException;
import org.apache.uima.jcas.JCas;

public class TextCategorizationAnnotator extends AbstractAlchemyAnnotator {

  protected URL createServiceURI() throws MalformedURLException {
    return URI.create("http://access.alchemyapi.com/calls/text/TextGetCategory").toURL();
  }

  protected String[] getServiceParameters() {
    String[] parameters = new String[] { "outputMode", "baseUrl", "url" };
    return parameters;
  }

  protected void mapResultsToTypeSystem(Results results, JCas aJCas) throws MappingException {
    Alchemy2TypeSystemMapper.mapCategorizationEntity((CategorizationResults) results, aJCas);
  }

  protected DigesterProvider createDigester() {
    return new CategorizationDigesterProvider();
  }

  protected void initializeRuntimeParameters(JCas aJCas) {
    // create parameters string
    StringBuffer serviceParamsBuf = new StringBuffer();
    serviceParamsBuf.append("&text=");
    String modifiedText = cleanText(aJCas);
    serviceParamsBuf.append(modifiedText);
    this.serviceParams += (serviceParamsBuf.toString());
  }

}
