# Course Curate

## Introduction
In this project, a recommendation engine has been proposed which can be used by university students to get course recommendations based on common interests with previously enrolled students. We have used the Neo4j Graph Database to store data and generate recommendations. 

## Data Model
 The database consists of following types of nodes:<br />
**User:**<br />
User nodes represent either Student, Faculty, or Admin depending on its property “role”. They have “name”, “emailID”, “password”, and “role”, using the latter two properties to log in. <br />
**Domain:**<br />
Domain nodes have the property “name” which is to hold a Computer Science-related interest, such as NoSQL.<br />
**Course:**<br />
Course nodes have the property “name” which is to hold a Computer Science course, such as NoSQL.<br />
<br />
<br />
![data model](https://github.com/sumeetghegade/CS157C-team02/blob/main/images/DataModel.png?raw=true)

## Data Instances
![data instances](https://github.com/sumeetghegade/CS157C-team02/blob/main/images/DataInstances.png?raw=true)

## Recommendations
Students can choose their domains of interest from a list of available domains:
![select domains](https://github.com/sumeetghegade/CS157C-team02/blob/main/images/SelectDomains.png?raw=true)
<br/>
Based on the selected domains students will get the recommendations as follows:
![select domains](https://github.com/sumeetghegade/CS157C-team02/blob/main/images/Recommendations.png?raw=true)
<br/>
Query used to get recommendations:
![query](https://github.com/sumeetghegade/CS157C-team02/blob/main/images/Query.png?raw=true)



