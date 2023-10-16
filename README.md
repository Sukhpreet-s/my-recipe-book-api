# Recipe Book API

API for storing recipes.

# Tech used

- Spring Boot
- MongoDB

# API
- Link: https://my-recipe-book-api.onrender.com/recipe
- **GET** / - Get list of all Recipes
- **POST** / - Add a new Recipe
- **PUT** / - Update a Recipe given by id
- **DELETE** / - Delete a Recipe given by id
- **POST** /upload-multi-images - Upload Recipe images

# Install instructions

- Clone the repo to local
- `docker build -t recipe-book .`
- `docker run -p 8080:8082 recipe-book`
- Open localhost:8080 in browser
