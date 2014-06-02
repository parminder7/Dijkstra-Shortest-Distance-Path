Dijkstra-Shortest-Distance-Path
===============================

through adjacency matrix


Objective:	
-----------

Write a program that generates an undirected graph and implements Dijkstra’s shortest-path algorithm.

	1.	Take a simple graph with at least 6 vertices and some weighted edges.
	2.	The weight of an edge can be the distance between the two end vertices of the edge.
	3.	Find the shortest path between any two vertices by using Dijkstra’s shortest-path algorithm.
	4.	Show the map through an adjacency list or an adjacency matrix with the weights cleared shown. 
	5.	Display a text-based menu for the user to choose any two vertices for finding the shortest distance between them
	6.	Display the shortest path in a text.


This program is implemented in Java

My solution consists of the following files:
	- ShortestPathMain.java
	- Driver.java
	- graph.jpg


Running my solution:
-------------------
You can run my program as follows:

	javac ShortestPathMain.java Driver.java	
	java Driver


IDE/Compiler:	
------------
Eclipse Kepler


Implementation:
-----------------
	ShortestPathMain.java

	|->ShortestPathMain(): constructor
		Initialization can be done. Displays the three options to the users. 		
		
		1. to select the default graph
		2. to make your own graph
		3. to generate random graph
		As per the user choice, the corresponding function calls will made.

	|->myGraph(): public method
		(on the choice of 1)This method sets the value to the adjancency list of the attached graph (graph.jpg).

	|->impromtuGraph(): public method
		(on the choice of 2)This method forms the graph as per the user entry. The following steps need to be performed:
		*	User will be asked to enter the number of vertices.
		*	To form one edge, user need to give the vertices in the A,D format. 			
		*	then user need to enter the weight on entered edge.
		This way, the graph get formed by the user's choice.
		
	|->prepareRandomGraph(): public method
		(on the choice of 3)This method sets the random values to the edges of the graph.
	
	|->displayGraph(): public method
		This method displays the adjanceny matrix to the console.
	
	|->shortestPath(): public method
		This method asks the user to enter the source and the destination in A-D format and compute the shortest path between them.

	|->Dijkstra(int source): public method
		This method takes the source and compute the shortest path to reach to the every other node.

	|->setDistance(int min_Node, int min_Wt, int source): public method
		This method sets the distance of particular node to adjacent nodes.

	|->displayPath(): public method
		This method displays the path by showing the edges to be followed to reach the destination.

	|->reset(): public method
		This method resets all the variables.
		

Conclusions/Remarks:
-------------------
Run the test cases by inputting the values in given format.
