# Short Reflection

## 1. How did you implement CRUD using SQLite?
To implement CRUD using SQLite, I created a helper class that manages the database operations such as inserting, updating, reading, and deleting notes. Each note is stored with a title, content, and timestamp to keep track of changes. RecyclerView was used to display the list of saved notes, making it easy for users to view and interact with their data.

## 2. What challenges did you face in maintaining data persistence? 
One of the challenges I faced was ensuring that the data persisted after closing the app. I solved this by properly managing database connections and reloading the notes each time the activity resumes. Another challenge was handling updates and deletes smoothly, which required careful handling of RecyclerView refreshes.

## 3. How could you improve performance or Ul design in future versions?
In the future, I plan to improve the app’s performance by adding search functionality and a more modern user interface design using Material components for a better user experience.
