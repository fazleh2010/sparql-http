/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.core.mysql;

import citec.core.termbase.TermInfo;
import citec.core.termbase.Termbase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author elahi SELECT * FROM `en_A_B_term` WHERE 1 DROP table `en_A_B_term`;
 */
public class MySQLAccess implements DataBaseConst {

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySQLAccess() throws Exception {
        this.connectDataBaseUnix();
    }

    //command line location to /opt/lampp/bin/mysql -u root -p
    public void connectDataBase() throws Exception {

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/test";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfull!!");
        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
        }
        /*finally {
            close();
        }*/

    }

    //command line location to /opt/lampp/bin/mysql -u root -p
    //jdbc:mariadb://localhost:3306/revmgt?localSocket=/var/run/mysqld/mysqld.sock
    public void connectDataBaseUnix() throws Exception {

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/test?localSocket=/var/run/mysqld/mysqld.sock";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfull!!");
        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
        }
        /*finally {
            close();
        }*/

    }

    public void createTermTable(String tableName) throws Exception {

        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE " + tableName + " "
                    + "(id VARCHAR(255) not NULL, "
                    + " term VARCHAR(4000), "
                    + " originalUrl VARCHAR(4000), "
                    + " alternativeUrl VARCHAR(255), "
                    + " reliabilityCode VARCHAR(255), "
                    + " administrativeStatus VARCHAR(255), "
                    + " subjectField VARCHAR(255), "
                    + " subjectDescription VARCHAR(255), "
                    + " reference VARCHAR(255), "
                    + " language VARCHAR(255), "
                    + " pair VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
        }
    }


    public void deleteTable(String tableName) throws Exception {

        try {
            Statement stmt = conn.createStatement();
            String sql = "Drop table " + tableName;
            stmt.executeUpdate(sql);
            System.out.println("delete table in given database...");

        } catch (Exception e) {
            System.out.println("delete table not possible!!...");
        }

    }

    public void insertDataTermTable(String tableName, Termbase languageTerms, Integer limit) {
        String language = "";
        String pair = "";
        try {
            Integer index = 1;

            for (String url : languageTerms.getTerms().keySet()) {
                TermInfo termInfo = languageTerms.getTerms().get(url);
                index = index + 1;

                if (!(limit == -1)) {
                    if (index > limit) {
                        break;
                    }
                }

                String id = index.toString();
                String query = " insert into " + tableName
                        + " (id, term, originalUrl, alternativeUrl, reliabilityCode, "
                        + " administrativeStatus, subjectField, subjectDescription, reference,"
                        + " language, pair)"
                        + " values (?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, id);
                preparedStmt.setString(2, termInfo.getTermOrg());
                preparedStmt.setString(3, termInfo.getTermUrl());
                preparedStmt.setString(4, termInfo.getAlternativeUrl());
                preparedStmt.setString(5, termInfo.getReliabilityCode());
                preparedStmt.setString(6, termInfo.getAdministrativeStatus());
                preparedStmt.setString(7, termInfo.getSubjectId());
                preparedStmt.setString(8, termInfo.getSubjectDescription());
                preparedStmt.setString(9, termInfo.getReferenceID());
                preparedStmt.setString(10, language);
                preparedStmt.setString(11, pair);
                preparedStmt.execute();
            }

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    /*private static String path = "src/main/resources/";
    private static String myTermbase = "iate/";
    private static String myTermTable = "tbx2rdf_iate_en_A_B";
    private static String linkTermbase = "atc/";
    private static String otherTermTable = "tbx2rdf_atc_en_A_B";
    private static Integer limitOfTerms = 200;
    private static String matchedTermTable = "tbx2rdf_atc_en_A_B";*/
 /*while (rs.next()) {
            //String language = rs.getString("language");
            String term = rs.getString("term");
            String orginalUrl = rs.getString("originalUrl");
            if (otherTermTable.getTerms().containsKey(term)){
                TermInfo othertermInfo=otherTermTable.getTerms().get(term);
                System.out.println(term+ " "+orginalUrl+" "+othertermInfo.getTermUrl());
            }
        }*/
 /*
    String query=" SELECT tbx2rdf_iate_en_A_B.term, tbx2rdf_iate_en_A_B.originalUrl"
                + " FROM tbx2rdf_iate_en_A_B"
                + " INNER JOIN tbx2rdf_atc_en_A_B ON tbx2rdf_iate_en_A_B.term = tbx2rdf_atc_en_A_B.term";
        
     */
    
    
    public Integer insertDataTermTable(String myTermTable, Termbase otherTermTable, String matchedTermTable) throws SQLException, Exception {
        Integer index=0;
        createLinkingTable(matchedTermTable);
        
        Statement stmt = conn.createStatement();
        String query = " SELECT term, originalUrl"
                + " FROM " + myTermTable;

        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {
                //String language = rs.getString("language");
                String term = rs.getString("term");
                String orginalUrl = rs.getString("originalUrl");
                TermInfo myterm=new TermInfo(term,orginalUrl);
                //System.out.println(term + " " + orginalUrl + " ");
                if (otherTermTable.getTerms().containsKey(term)) {
                    TermInfo othertermInfo = otherTermTable.getTerms().get(term);
                    TermInfo otherTerm=new TermInfo(term,othertermInfo.getTermUrl());
                    //System.out.println(term + " " + orginalUrl + " " + othertermInfo.getTermUrl());
                    insertDataLinkTable(myterm, otherTerm, matchedTermTable,index);
                    index++;
                }
            }
        } catch (Exception e) {
            System.err.println("Matching table exceptions!");
            System.err.println(e.getMessage());
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        /* String query2 = " SELECT term, originalUrl"
                + " FROM "+myTermTable;

        ResultSet rs2 = stmt.executeQuery(query);

        while (rs2.next()) {
            //String language = rs.getString("language");
            String term = rs2.getString("term");
            String orginalUrl = rs2.getString("originalUrl");
            //term=term.toLowerCase().trim();
             if(orginalUrl.contains("http:"))
             System.out.println(term + " " + orginalUrl + " " ); 
        }*/
        return index;
    }

    /*public void insertDataTermTable(String myTermTable, String otherTermTable, String matchedTermTable) throws SQLException {
        Integer index = 0;
        
        Statement stmt = conn.createStatement();
        String query=" SELECT tbx2rdf_iate_en_A_B.term, tbx2rdf_iate_en_A_B.originalUrl"
                + " FROM tbx2rdf_iate_en_A_B"
                + " INNER JOIN tbx2rdf_atc_en_A_B ON tbx2rdf_iate_en_A_B.term = tbx2rdf_atc_en_A_B.term";
        
       
        
        //String query="SELECT * FROM "+myTermTable+" INTERSECT SELECT * FROM "+otherTermTable;
        
         try {
        ResultSet rs = stmt.executeQuery(query);
        
        
        
        while (rs.next()) {
            //String language = rs.getString("language");
            String term = rs.getString("term");
            String orginalUrl = rs.getString("originalUrl");
            System.out.println(term+ " "+orginalUrl);
            
        }
        
          } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
      
    }*/
    
     public void createLinkingTable(String tableName) throws Exception {

        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE " + tableName + " "
                    + "(id INTEGER not NULL, "
                    + " termEncripted VARCHAR(255), "
                    + " termDecripted VARCHAR(255), "
                    + " myTermUrl VARCHAR(255), "
                    + " myTermAlterUrl VARCHAR(255), "
                    + " otherTermUrl VARCHAR(255), "
                    + " otherTermAlterUrl VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (Exception e) {
           System.out.println("create table not possible!!...");
        }

    }
    
     public void insertDataLinkTable(TermInfo myTerminology, TermInfo linkTerminology, String linkTableName,Integer index) {
       
        try {

            String query = " insert into " + linkTableName
                    + " (id, termEncripted, termDecripted, myTermUrl, myTermAlterUrl, otherTermUrl, otherTermAlterUrl)"
                    + " values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, index);
            preparedStmt.setString(2, myTerminology.getTermOrg());
            preparedStmt.setString(3, myTerminology.getTermStringOrg());
            preparedStmt.setString(4, myTerminology.getTermUrl());
            preparedStmt.setString(5, myTerminology.getAlternativeUrl());
            preparedStmt.setString(6, linkTerminology.getTermUrl());
            preparedStmt.setString(7, linkTerminology.getAlternativeUrl());
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
    /*public void insertDataLinkTable(String myTerminology, String linkTerminology, String linkTableName) {
        Integer index = 0;
        try {

            String query = " insert into " + linkTableName
                    + " (id, term_1, term_1_url, term_1_alter_url, term_2, term_2_url, term_2_alter_url)"
                    + " values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, index++);
            preparedStmt.setString(2, "term_1");
            preparedStmt.setString(3, "term_1_url");
            preparedStmt.setString(4, "term_1_alter_url");
            preparedStmt.setString(5, "term_2");
            preparedStmt.setString(6, "term_2_url");
            preparedStmt.setString(7, "term_2_alter_url");
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }*/

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

    // connect way #1
    /*// Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from feedback.comments");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

            preparedStatement = connect
                    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            resultSet = preparedStatement.executeQuery();
            writeResultSet(resultSet);

            // Remove again the insert comment
            preparedStatement = connect
                    .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("select * from feedback.comments");
            writeMetaData(resultSet);*/
}
