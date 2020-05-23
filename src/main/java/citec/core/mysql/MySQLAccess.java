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
 * //command line location to /opt/lampp/bin/mysql -u root -p
 * //jdbc:mariadb://localhost:3306/revmgt?localSocket=/var/run/mysqld/mysqld.sock
 * //command line location to /opt/lampp/bin/mysql -u root -p
 */
public class MySQLAccess implements DataBaseConst {

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySQLAccess() throws Exception {
        this.connectDataBaseUnix();
    }

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
    }

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
            System.out.println("Created " + tableName + " table in given database...");

        } catch (Exception e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
        }
    }

    public void deleteTable(String tableName) throws Exception {

        try {
            Statement stmt = conn.createStatement();
            String sql = "Drop table " + tableName;
            stmt.executeUpdate(sql);
            System.out.println("delete " + tableName + " table in given database...");

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
            System.out.println("insert to " + tableName + " table is successful!!...");

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public Integer insertDataTermTable(String myTermTable, Termbase otherTermTable, String matchedTermTable) throws SQLException, Exception {
        Integer index = 0;
        Statement stmt = conn.createStatement();
        String query = " SELECT term, originalUrl"
                + " FROM " + myTermTable;

        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {
                //String language = rs.getString("language");
                String term = rs.getString("term");
                String orginalUrl = rs.getString("originalUrl");
                TermInfo myterm = new TermInfo(term, orginalUrl);
                //System.out.println(term + " " + orginalUrl + " ");
                if (otherTermTable.getTerms().containsKey(term)) {
                    TermInfo othertermInfo = otherTermTable.getTerms().get(term);
                    TermInfo otherTerm = new TermInfo(term, othertermInfo.getTermUrl());
                    //System.out.println(term + " " + orginalUrl + " " + othertermInfo.getTermUrl());
                    index++;
                    insertDataLinkTable(myterm, otherTerm, matchedTermTable, index);
                }
            }
            System.out.println("insert to " + matchedTermTable + " table is successful!!...");

        } catch (Exception e) {
            System.err.println("Matching table exceptions!");
            System.err.println(e.getMessage());
        }

        return index;
    }

    public void createLinkingTable(String tableName) throws Exception {

        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE " + tableName + " "
                    + "(id VARCHAR(255) not NULL, "
                    + " termOrg VARCHAR(4000), "
                    + " term VARCHAR(4000), "
                    + " myTermUrl VARCHAR(255), "
                    + " myTermAlterUrl VARCHAR(255), "
                    + " otherTermUrl VARCHAR(255), "
                    + " otherTermAlterUrl VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created " + tableName + " table in given database...");

        } catch (Exception e) {
            System.out.println("create table not possible!!...");
        }

    }

    public void insertDataLinkTable(TermInfo myTerminology, TermInfo linkTerminology, String linkTableName, Integer index) {

        try {

            String query = " insert into link (id, termOrg, term, myTermUrl, myTermAlterUrl, otherTermUrl, otherTermAlterUrl)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, index);
            preparedStmt.setString(2, myTerminology.getTermOrg());
            preparedStmt.setString(3, myTerminology.getTermDecrpt());
            preparedStmt.setString(4, myTerminology.getTermUrl());
            preparedStmt.setString(5, myTerminology.getAlternativeUrl());
            preparedStmt.setString(6, linkTerminology.getTermUrl());
            preparedStmt.setString(7, linkTerminology.getAlternativeUrl());
            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an exception while adding data!");
            System.err.println(e.getMessage());
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
}
