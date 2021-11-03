package services;

import model.ToDo;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/* SqliteManager
 * Doesn't keep an open connection to save resources.
 * Opens up a new connection for each database operation.
 */
public class SqliteManager {

    // Fields
    private String connectionString;
    private Connection connection;


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
            Statement statement = connection.createStatement();

            // Craft sql string & write it
            String message = toDo.getMessage().isEmpty() ? "N/A" : toDo.getMessage();
            String categories = toDo.getCategories().isEmpty() ? "N/A" : toDo.getCategories().toString();
            String tags = toDo.getTags().isEmpty() ? "N/A" : toDo.getTags().toString();
            String writeString = "INSERT INTO Items VALUES ("
                    + Integer.toString(toDo.getID()) + ", "
                    + toDo.getTitle() + ", '"
                    + message + "', '"
                    + toDo.getDateOfCreation().toString() + "', '"
                    + toDo.getDueDate().toString() + "', '"
                    + categories.toString() + "', '"
                    + tags
                    + "')";
            statement.executeUpdate(writeString);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Close database
        finally {
            try {
                this.connection.close();
                this.connection = null;
                System.gc();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void readItem() {

    }

    public void updateItem(ToDo toDo) {

    }

    public void deleteItem(ToDo toDo) {

    }

    public ArrayList<ToDo> loadItems() {
        ArrayList<ToDo> result = new ArrayList<>();

        // Read items & put them into ArrayList

        return result;
    }


    // Custom methods


}
