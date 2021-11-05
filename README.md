# ToDo-List
![To Do List](https://images.unsplash.com/photo-1507925921958-8a62f3d1a50d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1176&q=80)

# How to start
Since we store data in a local SQLite database, be advised to add the jdbc-sqlite driver to your project classpath
 (eclipse) or your project libraries (IntelliJ). We went ahead and placed the 
[jdbc-driver from xerial](https://github.com/xerial/sqlite-jdbc) inside the **lib-directory** of the project. Once you have
added this dependency to (just as you do for JavaFX), you're all set and ready. For a more detailed guide,
read the chapters below.

## Adding the SQLite-JDBC driver in IntelliJ
1. Open the project in IntelliJ
2. On the top left, click on **File** and select **Project Structure** (File -> Project Structure)
3. Move to the **Libraries**-tab and click on the **+** (plus) icon to add a new project library.
4. Select **Java** and navigate to the **lib-directory** of this project. The lib-directory is located inside the
src-directory.
5. Select the **sqlite-jdbc-3.36.0.3.jar** and click on **add**.
6. Click on **apply** and close the window. You're able to use the jdbc-sqlite driver now.

## Adding the SQLite-JDBC driver in Eclipse
- TBD

# Minimal Requirements
- The applicaiton implements the data class "ToDo".
- The "model" of the application maintains an ArrayList of "ToDo" items.
- The user interface allows the user to perform CRUD operations on individual ToDo items.
  - Please note that a ToDo placed inside the "Papierkorb" will be deleted at application close. We designed this
  mechanism this way so that you are able to re-adjust the category of a deleted item - moving it out of the
  "Papierkorb" again.

# Additional Features
- **Functionality**
  - **Database**
    - Store items in a local, [SQLite](https://www.sqlite.org/index.html) database
  - **Categorization**
    - Assign one or multiple categories to a ToDo
  - **Searchbar**
    - The application provides a search bar that scans all items for the input given.
    - The search function will look inside the title, as well as the message
  - **Filters**
    - ToDo-items can be filtered based on their due-date - selecting all items due for today or during the current week.
  - **Bar chart**
    - The application shows a bar chart which states how many toDo's that are open, and how many still need to be done.
- **Design**
  - Customized .CSS styling

## Contributors
- [Margareta Karaqi](https://github.com/mkfhnw)
- [Nico Sträuli](https://github.com/nicSt12)
- [Joel Läubin](https://github.com/Joel-Laeubin) / [MadMowgli](https://github.com/MadMowgli/MadMowgli)
