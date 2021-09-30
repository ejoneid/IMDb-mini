# Assignment 3 - IMDb-mini 🎬

This is a repository for a backend assignment for the Experis Accelerated Learning course. This application makes use of
**Spring Boot**, **PostgreSQL**, **Hibernate**, **Java Persistence API** and **Swagger**. We have been tasked with making a datastore to store and manipulate data about movies. 

## What does _IMDb-mini_ do?

The application has three models, _franchise_, _movie_, and _actor_. These also represent the entities found in the 
database. Upon running the application the database will be seeded with pre-made data, this is done so the API
can be tested without having to add data manually. 

The API has functionality for:
- Creating new franchises, movies or actors.
- Updating existing franchises, movies or actors.
- Deleting existing franchises, movies or actors.
- Getting all or ID-specific franchises, movies or actors.
- Getting all actors playing in a franchise.
- Getting all actors playing in a movie.
- Getting all movies in a franchise.
- Updating movies belonging to a franchise.
- Updating actors playing in a movie.

## What do you have to do to run the application?

First, clone the project by writing `git clone git@gitlab.com:EirikJoneid/assignment3.git` in the terminal of
your choice. Make sure you have PostgreSQL installed, with pgAdmin 4. In the file `application.properties` you can
change the credentials to match yours in pgAdmin 4. Then run the application, this seeds the database, and you're free to
test the API.

## Documentation by Swagger

To read more about the API and application, you can run the application and go to 
[this link: localhost:8080/api-docs/v1](http://localhost:8080/api-docs/v1). Here you can try out our API and read more 
about the application. The API documentation is made with Swagger by annotating directly in the code, this is interpreted
by Spring Boot, and generates a visual doc with info about our API.

## Dataflow
The image below is a visual representation of the dataflow. A franchise is made up of one or many movies, and a movie 
can belong to one franchise. An actor can act in one or many movies, and a movie can have one or many actors.

![Dataflow in the application](https://gitlab.com/EirikJoneid/assignment3/-/raw/master/dataflow.png)
