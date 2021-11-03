package services;

import model.ToDo;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* SqliteManager
 * Doesn't keep an open connection to save resources.
 * Opens up a new connection for each database operation.
 */
public class SqliteManager {

    // Fields
    private String connectionString;
    private Connection connection;
    private Statement statement;


    // Constructor
    public SqliteManager() throws SQLException {
        String fileString = System.getProperty("user.dir");
        this.connectionString = fileString + File.separator + "src"
                + File.separator + "database"
                + File.separator + "ToDoBase.db";
        this.connectionString = "jdbc:sqlite:" + connectionString;

        // Create db if it doesn't exist
        try {
            this.initializeDatabase();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // Getter & Setter

    // Database functions
    private void initializeDatabase() throws SQLException {

        // Creating new database if it doesn't exist
        try {
            this.connection = DriverManager.getConnection(this.connectionString);
            Statement statement = connection.createStatement();
            // Create table
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Items ("
                    + "ToDo_ID INTEGER,"
                    + "Title STRING,"
                    + "Message STRING,"
                    + "DoC STRING,"
                    + "DueDate STRING,"
                    + "Categories STRING,"
                    + "Tags STRING"
                    + ")");
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Close database
        finally {
            this.connection.close();
            this.connection = null;
            System.gc();
        }


    }

    public void writeItem(ToDo toDo) {
        System.out.println("Writing to db");

        try {

            // Set up connection & statement
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = connection.createStatement();

            // Craft sql string & write it
            String message = toDo.getMessage().isEmpty() ? "N/A" : toDo.getMessage();
            String categories = toDo.getCategories().isEmpty() ? "N/A" : toDo.getCategories().toString();
            String tags = toDo.getTags().isEmpty() ? "N/A" : toDo.getTags().toString();
            String writeString = "INSERT INTO Items VALUES ("
                    + Integer.toString(toDo.getID()) + ", '"
                    + toDo.getTitle() + "', '"
                    + message + "', '"
                    + toDo.getDateOfCreation().toString() + "', '"
                    + toDo.getDueDate().toString() + "', '"
                    + categories.toString() + "', '"
                    + tags
                    + "')";
            this.statement.executeUpdate(writeString);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Close database
        finally {
            try {
                this.connection.close();
                this.statement.close();
                this.connection = null;
                this.statement = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void readItem() {

        try {
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.connection.close();
                this.statement.close();
                this.connection = null;
                this.statement = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void updateItem(ToDo oldItem, ToDo newItem) {

        try {
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = connection.createStatement();
            this.deleteItem(oldItem);
            this.writeItem(newItem);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.connection.close();
                this.statement.close();
                this.connection = null;
                this.statement = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void deleteItem(ToDo toDo) {

        try {
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = connection.createStatement();
            String deleteString = "DELETE FROM Items WHERE ToDo_ID=" + toDo.getID();
            statement.executeUpdate(deleteString);
            System.out.println("Deleted item from db");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.connection.close();
                this.statement.close();
                this.connection = null;
                this.statement = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /* Method that returns all items stored inside the db
     * Used by the controller on application start
     */
    public ArrayList<ToDo> loadItems() {
        ArrayList<ToDo> result = new ArrayList<>();

        // Read items & put them into ArrayList
        try {

            // Set up db connection
            this.connection = DriverManager.getConnection(this.connectionString);
            this.statement = connection.createStatement();

            // Read items & parse them
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Items");
            while(resultSet.next()) {
                LocalDate dateOfCreation = null;
                LocalDate dueDate = null;
                String title = resultSet.getString("Title");
                String message = resultSet.getString("Message").equals("N/A") ? "" : resultSet.getString("Message");
                String rawCategories = resultSet.getString("Categories");
                String rawTags = resultSet.getString("Tags");
                try {
                    dateOfCreation = LocalDate.parse(resultSet.getString("DoC"));
                    dueDate = LocalDate.parse(resultSet.getString("DueDate"));
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                }

                // Clean outputs
                ArrayList<String> categoryList = new ArrayList<>(Arrays.asList(rawCategories
                        .replaceAll("[\\[\\]]", "")
                        .split(",")));
                ArrayList<String> tagList = new ArrayList<>(Arrays.asList(rawTags
                        .replaceAll("[\\[\\]]", "")
                        .split(",")));

                // Create item and add to result set
                ToDo toDo = new ToDo(title, message, dateOfCreation, dueDate, categoryList, tagList);
                result.add(toDo);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.connection.close();
                this.statement.close();
                this.connection = null;
                this.statement = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }


    // Custom methods


}
