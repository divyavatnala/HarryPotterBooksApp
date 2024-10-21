# HarryPotterBooks App
This is a sample application to show the list of harry potter books available
by calling api in MVVM architecture.

# Features
1. Displays the list of harry potter books available from network api call
2. Can save the book as favourite book and can be viewed later.

## Api Used
Below is the api used from free github apis
https://potterapi-fedeperin.vercel.app/

api repo
https://github.com/fedeperin/potterapi

### Major Highlights
- MVVM Architecture
- Kotlin
- Hilt Dependency Injection
- Room Database
- Retrofit
- Coroutines
- Flow
- StateFlow
- ViewBinding
- Android Test
- Unit Test
- UI Test


#### Notes

App has one activity which is MainActivity which has BottomNavigationView of two menu items with its fragments

DashboardFragment - displays the list of harrypotter books available(from api, when network is not available gets data from database)
FavoritesFragment - displays the list of user saved books from Room Database


## Tech Slack


1. Room Database - For storing the data database
2. Hilt Dependency - Dependency Injection to reduce boilerplater code, it takes care of the object creation
3. Retrofit - To make network based calls, used this library
4. Glide - To display the images
5. Used Android LifeCycle Aware components 
6. For UI - Recyclerview, BottomNavigationMenu, androidxfragments











