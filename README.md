# Kalah Game

[![Build Status](https://travis-ci.org/acaremre7/ea-kalah.svg?branch=master)](https://travis-ci.org/acaremre7/ea-kalah) 
[![Coverage Status](https://coveralls.io/repos/github/acaremre7/ea-kalah/badge.svg?branch=master)](https://coveralls.io/github/acaremre7/ea-kalah?branch=master)

# This is Google Cloud Platform Version for Kalah game, made for bol.com
**For the live application, please refer to: https://kalah-229912.appspot.com**

For more information about the game: https://en.wikipedia.org/wiki/Kalah

### Installation

- Import the code to any IDE
- Wait for Maven to resolve dependencies
- Make sure port 8080 is not being used by any other applications on your computer
- run
```
mvn appengine:run
```
to run the application with maven appengine plugin
### Built With

* [Spring Boot](http://spring.io/projects/spring-boot) - To simplify web application server settings
* [Maven](https://maven.apache.org/) - Dependency Management
* [App Engine](https://cloud.google.com/appengine/) - Serverless cloud platform

### Design Patterns Used
* [State Pattern](https://en.wikipedia.org/wiki/State_pattern) - Learn more on https://en.wikipedia.org/wiki/Finite-state_machine
* [Factory Method Pattern](https://en.wikipedia.org/wiki/Factory_method_pattern)

### Task Description

The goal is for you to showcase your programming skills. It's not so much about finishing and solving the problem, but delivering  a well designed solution and code that you find qualitative. Because we would like to judge your java skills, we would like you to do this in Java.

We would like you to program a webapplication using frameworks you are familiar with, that runs a game. The rules are explained below.
This webapplication should enable to let 2 players play the game (no AI required). It doesn't need a fancy webinterface, we don't need a designer in the team ;-). But whatever you think is nice to show your skills in order to show off your programming skills, is up to you.

#### Board Setup
Each of the two players has his six pits in front of him. To the right of the six pits, each player has a larger pit. In each of the six round pits are put six stones when the game starts.

#### Rules
##### Game Play
The player who begins with the first move picks up all the stones in anyone of his own six pits, and sows the stones on to the right, one in each of the following pits, including his own big pit. No stones are put in the opponents' big pit. If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.

##### Capturing Stones
During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player captures his own stone and all stones in the opposite pit (the other players' pit) and puts them in his own pit.

##### The Game Ends
The game is over as soon as one of the sides run out of stones. The player who still has stones in his pits keeps them and puts them in his/hers big pit. Winner of the game is the player who has the most stones in his big pit.
