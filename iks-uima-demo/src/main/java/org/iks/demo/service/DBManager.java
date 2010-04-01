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
package org.iks.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Mysql document-concept DB manager
 * 
 * @version $Id$
 */
public class DBManager {

  private static final String EMPTY = "";

  private static final String USER = "root";

  private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";

  private static final String URL = "jdbc:mysql://localhost:3306/iks_demo";

  private static final String PASSWORD = "";

  private Connection prepareConnection() throws ClassNotFoundException, SQLException {
    Class.forName(MYSQL_JDBC_DRIVER);
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public void saveDocument(String text) throws ClassNotFoundException, SQLException {

    if (getDoc(text) != null && getDoc(text).equals(EMPTY)) {

      Connection con = prepareConnection();
      try {
        Statement stmt = con.createStatement();
        stmt.executeUpdate(new StringBuilder("insert into doc(text) values('").append(text).append(
                "')").toString());
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        con.close();
      }
    }
  }

  public void saveConcept(String documentText, String dbPediaVal) throws ClassNotFoundException,
          SQLException {

    if (getConcept(dbPediaVal) != null && getConcept(dbPediaVal).equals(EMPTY)) {

      // save concept
      Connection con = prepareConnection();
      String concept = EMPTY;
      try {
        concept = dbPediaVal.substring(dbPediaVal.lastIndexOf("/") + 1);
      } catch (Exception e1) {
        concept = dbPediaVal;
      }

      try {
        Statement stmt = con.createStatement();
        stmt.executeUpdate(new StringBuilder("insert into concept(value, url) values('").append(
                concept).append("','").append(dbPediaVal).append("')").toString());
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        con.close();
      }

    }

    String conceptId = getConcept(dbPediaVal);
    String docId = getDoc(documentText);

    if (!isDocConceptPresent(docId, conceptId)) {
      // bind concept to doc
      Connection secondCon = prepareConnection();
      try {
        Statement stmt2 = secondCon.createStatement();
        stmt2.executeUpdate(new StringBuilder("insert into conceptdoc(concept, doc) values('")
                .append(conceptId).append("',").append(docId).append(")").toString());
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        secondCon.close();
      }
    }
  }

  private String getDoc(String documentText) throws ClassNotFoundException, SQLException {
    Connection con = prepareConnection();
    String doc = EMPTY;

    try {
      Statement stmt = con.createStatement();
      String sql = "select id from doc where text = '" + documentText + "'";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        doc = rs.getString("id");
      }
      rs.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }
    return doc;
  }

  private String getConcept(String dbPediaVal) throws ClassNotFoundException, SQLException {
    Connection con = prepareConnection();
    String concept = EMPTY;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(new StringBuilder("select id from concept where url = '")
              .append(dbPediaVal).append("'").toString());
      while (rs.next()) {
        concept = rs.getString("id");
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }
    return concept;
  }

  private boolean isDocConceptPresent(String docId, String dbPediaId)
          throws ClassNotFoundException, SQLException {
    Connection con = prepareConnection();
    boolean result = false;

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(new StringBuilder("select * from conceptdoc where doc = ")
              .append(docId).append(" and concept = ").append(dbPediaId).toString());
      while (rs.next()) {
        result = true;
        break;
      }
      rs.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      con.close();
    }
    return result;
  }

}
