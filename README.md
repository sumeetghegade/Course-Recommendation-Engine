# Course Curate
CS157C-NoSQL: Team 02 

## Introduction
In this project, a recommendation engine has been proposed which can be used by university students to get course recommendations based on common interests with previously enrolled students. We have used the Neo4j Graph Database to store data and generate recommendations. 

## Data Model
The database consists of following types of nodes:
#### User
User nodes represent either Student, Faculty, or Admin depending on its property “role”. They have “name”, “emailID”, “password”, and “role”, using the latter two properties to log in. 
#### Domain
Domain nodes have the property “name” which is to hold a Computer Science-related interest, such as NoSQL.
#### Course
Course nodes have the property “name” which is to hold a Computer Science course, such as NoSQL.



