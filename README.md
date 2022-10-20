# Design Recipe 

**Project Proposal**

*What will the application do?*
- The application acts as a "design recipe" or dictionary of sorts for different coding 
  languages. For example, say I was working on a java project, however, I had forgotten what
  an Arraylist looks like. By using this application, I would type in Arraylist and the 
  application will output first the definition of an Arraylist, and then a code template of an
  Arraylist that the user can copy and paste onto their own project. Furthermore, the user will be able 
  to save their own definitions or code templates into the dictionary, which will allow for quick
  and easy access to these templates. 

*Who will use it?*
- Primarily java beginners and learners.

*Why is this project of interest to you?*
- I'm a really forgetful person and I find myself constantly looking up terms and code tidbits because I've forgotten them
  when I'm working. However, googling these questions tends to get a bit tedious, therefore I thought DesignRecipe could help
  centralize the information I'm looking for.

*PHASE 1: User Stories*
- As a user, I want to search for the definition of a recipe.
- As a user, I want to add a recipe to the DesignRecipe.
- As a user, I want to delete a recipe to the DesignRecipe.
- As a user, I want to see a list of recipes.

*PHASE 2: User Stories*
- As a user, I when I add a recipe, I want that recipe to be auto-saved. 
- As a user, I want I want to be able to restore DesignRecipe back to it's default state (load default file).

*PHASE 3: Grader Instructions*
- Start program at "DesignRecipe" in the UI.GUI folder

You can generate the first required event by...
- Clicking the add button either in the menu bar or below the ListView.

You can generate the second required event by...
- Clicking the delete button in the menu bar or below the ListView.
- OR using the search bar

You can locate my visual component by...
- Starting the program and seeing the loading page.

You can trigger my audio component by...
- Starting the program and hearing an elevator ping.
- Pressing the tobs button in the menu bar under "Help".

You can save the state of my application by...
- Pressing the save button in the menu bar under File.

You can reload the state of my application by...
- Pressing the revert button in the menu bar under File.

*PHASE 4: Task 2* 
- "Make appropriate use of the Map interface somewhere in your code"
- Implemented the Map interface in ListofRecipe class. 

*PHASE 4: Task 3*
- Fix 1: Fix coupling issue in ui/fxml/Controller.
    - Prior to phase 4 there were two different methods ("playTobs" and "play") responsible for playing two different
      soundbites.
    - For phase 4 these two methods were refactored and consolidated into one "play" method. 
- Fix 2: Improved coupling in Persistence package 
    - Prior to phase 4 each persistence class was independent from one another despite sharing very similar
      constructors and some methods.
    - For phase 4, these classes now extend the abstract class "Persist" which consolidates constructors and the
      method.close(). 
    

*To be completed...*
- Switch to JSON
- Add "Recipe" class for more functionality 
- Add more base recipes.
- Add and format code templates for all recipes.
- Add favourites list.


  
 
