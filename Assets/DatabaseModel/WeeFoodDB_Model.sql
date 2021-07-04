-- ---------------------------------------------------------------
-- recipe
-- ---------------------------------------------------------------
-- -----------------------------------------------------
-- Table 
-- -----------------------------------------------------
CREATE TABLE recipe(
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  picture TEXT NULL,
  cooking_time INTEGER AS Integer NOT NULL,
  unit TEXT NOT NULL,
  description TEXT NULL,
);

-- -----------------------------------------------------
-- CRUD 
-- -----------------------------------------------------
insertRecipe:
INSERT INTO recipe(id,name, picture,cooking_time, unit, description) 
VALUES(?, ?, ?, ?, ?, ?)

getAllRecipes:
SELECT *
FROM recipe;

deleteRecipeById:
DELETE FROM recipe where id = ?





-- ---------------------------------------------------------------
-- weekRecipe
-- ---------------------------------------------------------------
-- -----------------------------------------------------
-- Table
-- -----------------------------------------------------
CREATE TABLE weekRecipe(
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  weekday INTEGER AS Integer NOT NULL,
  portion INTEGER AS Integer NOT NULL,
  recipe_id INTEGER AS Integer NOT NULL,
  
  FOREIGN KEY (recipe_id) REFERENCES recipe(id)
);

-- -----------------------------------------------------
-- CRUD 
-- -----------------------------------------------------
insertWeekRecip:
INSERT INTO weekRecipe(id,weekday, portion ,recipe_id) 
VALUES(?, ?, ?, ?)

getAllWeekRecipes:
SELECT *
FROM weekRecipe;

deleteWeekRecipeById:
DELETE FROM weekRecipes where id = ?

deleteAllWeekRecipes:
DELETE FROM weekRecipes





-- ---------------------------------------------------------------
-- ingredient
-- ---------------------------------------------------------------
-- -----------------------------------------------------
-- Table
-- -----------------------------------------------------
CREATE TABLE ingredient (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  picture TEXT NULL,
  units TEXT NOT NULL,
  apiId INTEGER AS Integer NOT NULL,
);

-- -----------------------------------------------------
-- CRUD 
-- -----------------------------------------------------
insertIngredient:
INSERT INTO ingredient(id,name, picture,units,apiId) 
VALUES(?, ?, ?, ?, ?)

getAllIngredients:
SELECT *
FROM ingredient;

deleteIngredientById:
DELETE FROM ingredient where id = ?





-- ---------------------------------------------------------------
-- recipeIngredient
-- ---------------------------------------------------------------
-- -----------------------------------------------------
-- Table 
-- -----------------------------------------------------
CREATE TABLE recipeIngredient (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  quantity REAL AS Float NULL,
  unit TEXT NULL,
  recipe_id INTEGER AS Integer NOT NULL,
  ingredient_id INTEGER AS Integer NOT NULL,
  
  FOREIGN KEY (recipe_id) REFERENCES recipe(id)
  FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

-- -----------------------------------------------------
-- CRUD 
-- -----------------------------------------------------
insertRecipeIngredient:
INSERT INTO recipeIngredient(id,quantity, unit ,recipe_id, ingredient_id) 
VALUES(?, ?, ?, ?, ?)

getAllRecipeIngredients:
SELECT *
FROM recipeIngredient;

deleteRecipeIngredientById:
DELETE FROM recipeIngredients where id = ?


