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

import static org.junit.Assert.fail;

import org.apache.uima.alchemy.utils.TestUtils;
import org.junit.Test;

public class AlchemyTextAnnotatedEntityExtractionAnnotatorTest {

  @Test
  public void testAnnotator() {
    String doc = "Eight US soldiers die in attacks in south Afghanistan, making October the deadliest month for the US in the war there";
    String toc = "Eight [Country[US]] soldiers die in attacks in south [Country[Afghanistan,]] making October the deadliest month for the [Country[US]] in the war there";
    String xmlPath = "src/main/resources/TextAnnotatedEntityExtractionAEDescriptor.xml";
    try {
      TestUtils.executeAE(TestUtils.getAE(xmlPath), doc);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
