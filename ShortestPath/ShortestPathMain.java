/*
	* ASSIGNMENT#3: DIJKSTRA ALGORITHM
	* AUTHOR: PARMINDER JEET KAUR
	* SID: 87224135
*/
import java.util.Random;
import java.util.Scanner;

public class ShortestPathMain {
	//number of vertices
	int vertices = 5;
	//matrix
	int[][] matrix;
	//array for storing followed path
	int[] path;
	//array for storing visited nodes
	int[] Q;
	//the nodes distance from the processing node
	int[] D;
	//for calculating edges
	int[] res;
	
	//Scanner class object
	Scanner scan = new Scanner(System.in);
	
	//for initalization
	public ShortestPathMain(){
		int i;
		System.out.println("LAB3: WELCOME TO DIJKSTRA IMPLEMENTATION");
		
		//to render the default graph
		String choice;
		System.out.println("1. To select the default graph (refer: graph.jpg)");
		System.out.println("2. To make your own graph");
		System.out.println("3. To generate random graph");
		System.out.println("Choose the option: ");
		choice = scan.next();
		int ch = Integer.parseInt(choice);
		switch(ch){
		case 1:	vertices = 7;
				//matrix
				matrix = new int[vertices][vertices];
				//adjancency list for 7 nodes
				for(i=0; i<vertices; i++){
					for(int j=0; j<vertices; j++)
						matrix[i][j] = -1;
				}
				myGraph();
				displayGraph();
				break;
		case 2: impromtuGraph();
				break;
		case 3: vertices = 7;
				//matrix
				matrix = new int[vertices][vertices];
				//adjancency list for 7 nodes
				for(i=0; i<vertices; i++){
					for(int j=0; j<vertices; j++)
						matrix[i][j] = -1;
				} 
				prepareRandomGraph();
				displayGraph();
				break;
		}
		
		//array for storing followed path
		path = new int[vertices];
		//array for storing visited nodes
		Q = new int[vertices];
		//the nodes distance from the processing node
		D = new int[vertices];
		//for calculating edges
		res = new int[vertices];
		
		//initializing the arrays
		for(i=0; i<vertices; i++)
			path[i] = -1;
		
		for(i=0; i<vertices; i++)
			Q[i] = -1;
		
		for(i=0; i<vertices; i++)
			D[i] = -1;		
		
		for(i=0; i<vertices; i++)
			res[i] = -1;
		
		//start to find shortest path
		String c = "";
		do{
			shortestPath();
			System.out.println("\nWant to make other search on this graph? Y/N: ");
			c = scan.next();
			reset();
		}while(c.equals("y") || c.equals("Y"));
		
		System.out.println("Good Bye! :)");
	}
	
	//Plotting the graph
	public void myGraph(){
		//setting the weight
		System.out.println("A default undirected graph's adancency list\n");
		matrix[0][1] = 19;	matrix[0][2] = 7;
		matrix[1][0] = 19;	matrix[1][2] = 11;	matrix[1][4] = 4;
		matrix[2][0] = 7;	matrix[2][1] = 11;	matrix[2][3] = 5;	matrix[2][4] = 15;	matrix[2][5] = 2;	matrix[2][6] = 14;
		matrix[3][2] = 5;	matrix[3][4] = 13;	matrix[3][6] = 3;
		matrix[4][1] = 4;	matrix[4][2] = 15;	matrix[4][3] = 13;	matrix[4][5] = 8;
		matrix[5][2] = 2;	matrix[5][4] = 8;	matrix[5][6] = 1;
		matrix[6][5] = 1;	matrix[6][2] = 14;	matrix[6][3] = 3;
	}
	
	//to generate the random graph
	public void prepareRandomGraph(){
		System.out.println("A Random undirected graph's adancency list\n");
		//setting the random values
		Random rr = new Random();
		matrix[0][1] = matrix[1][0] = rr.nextInt(90);	matrix[0][2] = matrix[2][0] = rr.nextInt(90);
		matrix[1][2] = matrix[2][1] = rr.nextInt(90);	matrix[1][4] = matrix[4][1] = rr.nextInt(90);
		matrix[2][3] = matrix[3][2] = rr.nextInt(90);	matrix[2][4] = matrix[4][2] = rr.nextInt(90);
		matrix[3][4] = matrix[4][3] = rr.nextInt(90);	
	}
	
	//to draw the graph on demand
	public void impromtuGraph(){
		System.out.println("Enter the number of vertices: ");
		vertices = scan.nextInt();
		//matrix
		matrix = new int[vertices][vertices];
		//adjancency list for 5 nodes
		for(int i=0; i<vertices; i++){
			for(int j=0; j<vertices; j++)
				matrix[i][j] = -1;
		}
		String c="";
		String input;
		int x, y, wt;
		//create adjancency list
		System.out.println("Create adancency list for nodes with Range[A to "+(char)(64+vertices)+"]");
		do{
			System.out.println("Select two nodes in x,y or X,Y format: ");
			input = scan.next();
			String[] inp = input.split(",");
			byte[] xx = inp[0].getBytes();
			byte[] yy = inp[1].getBytes();
			x = xx[0];
			y = yy[0];
			if(xx[0] >= 65 && xx[0] <= (65+vertices))
				 x = x - 65;
			else if(xx[0] >= 97 && xx[0] <= (97+vertices))
				x = x - 97;

			if(yy[0] >= 65 && yy[0] <= (65+vertices))
				 y = y - 65;
			else if(yy[0] >= 97 && yy[0] <= (97+vertices))
				y = y - 97;
			
			else{
				System.out.println("Selected node is out of range! Try again..");
				continue;
			}
			
			System.out.println("you have chosen ("+x+","+y+"), now enter the weight: ");
			wt = scan.nextInt();
			matrix[x][y] = wt;
			System.out.println("more to enter? Y/N: ");
			c = scan.next();
		}while(c.equals("y") || c.equals("Y"));
		
		System.out.println("Custom graph's adancency list\n");
		displayGraph();
	}
	
	//display the graph
	public void displayGraph(){
		int letter = 65;
		System.out.print("\t");
		for(int i=0; i<vertices; i++)
			System.out.print("  "+(char)(65+i)+" ");
		
		System.out.println();
		System.out.println("\t _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		for(int i=0; i<vertices; i++){
			System.out.print((char)letter+"\t");
			System.out.print(" | ");
			for(int j=0; j<vertices; j++){
				if(String.valueOf(matrix[i][j]).length() == 2)
					System.out.print(matrix[i][j]+"| ");
				else
					System.out.print(matrix[i][j]+" | ");
			}
			System.out.println(" ");
			letter++;
		}
	}
	
	//to find shortest path
	public void shortestPath(){
		System.out.print("Enter the source and the destination in s-d or S-D format: ");
		int source = -1, dest = -1;
		//CONVERTING string input to integer
		String input = scan.next();
		String[] inp = input.split("-");
		byte[] s = inp[0].getBytes();
		byte[] d = inp[1].getBytes();
		
		//mapping input to matrix index
		if(s[0] >= 65 && s[0] <= (65+vertices))
			source = s[0] - 65;
		else if(s[0] >= 97 && s[0] <= (97+vertices))
			source = s[0] - 97;
		else{
			System.out.println("Invalid entry for source! Try again..");
			shortestPath();
		}
			
		if(d[0] >= 65 && d[0] <= (65+vertices))
			dest = d[0] - 65;
		else if(d[0] >= 97 && d[0] <= (97+vertices))
			dest = d[0] - 97;
		else{
			System.out.println("Invalid entry for destination! Try again..");
			shortestPath();
		}
		
		//Dijkstra call
		Dijkstra(source);
		
		//RETRACING the path followed
		res[0] = dest;
		int j = 1, i = dest;
		while(i>=0){
		//	System.out.println("i="+i);
			res[j] = path[i];
		//	System.out.println("res"+j+"="+res[j]);
			i = res[j];
		//	System.out.println("i="+i);
			if((path[i]==-1) || (res[i] == source) || (res[i] == dest))
				break;
			j++;
		}
		
		//display the path followed by edges
		displayPath();
	}
	
	//Implementing Dijkstra Algorithm
	public void Dijkstra(int source){
		int min_Node = source, min_Wt = 0;
		while(true){
			setDistance(min_Node, min_Wt, source);
			min_Node = find_Min(source);
			if(min_Node == -1)
				break;
			Q[min_Node] = min_Node;
			min_Wt = D[min_Node];
		}
	}
	
	//set distance of particular processing node to adjacent nodes
	public void setDistance(int min_Node, int min_Wt, int source){
		int i;
		for(i=0; i<vertices; i++){
			if(i == source)
				continue;
			else if((matrix[min_Node][i] != -1) || (min_Node == source)){
				if(D[i] == -1)
					Replace(i, min_Wt, min_Node);
				else if(D[i] > (matrix[min_Node][i]+min_Wt))
					Replace(i, min_Wt, min_Node);
			}
		}
		/*
		System.out.print("\n");
		for(i=0; i<5; i++)
			System.out.print(" D "+i +"="+D[i]);
		System.out.print("\n");
		for(i=0; i<5; i++)
			System.out.print(" Q "+i+"="+Q[i]);
		for(i=0; i<5; i++)
			System.out.println(" path= "+path[i]);
	*/
	}
	
	//replace the value in 
	public void Replace(int i, int min_Wt, int min_Node){
		path[i] = min_Node;
		D[i] = matrix[min_Node][i]+min_Wt;
	}
	
	//find the node with minimum distance from the source
	public int find_Min(int source){
		int v = 999, node = -1;
		int i;
		for(i=0; i<vertices; i++){
			if(i == source)
				continue;
			else if((D[i] < v) && (Q[i] == -1) && (D[i] != -1)){
				v = D[i];
				node = i;
			}
		}
		//System.out.print("Min node = "+node);
		return node;
	}
	
	//display the path by edges
	public void displayPath(){
		int i=vertices-1, cn=0;
		System.out.println("The shortest path is ");
		while(i>=0){
			if(res[i] == -1){
				i--;
				continue;
			}
			char val= (char)(res[i]+65);
			System.out.print(val);
			if(i != 0)
				System.out.print(" -> ");
			i--;
		}
	}
	
	//to reset the array
	public void reset(){
		int i;
		for(i=0; i<vertices; i++)
			path[i] = -1;
		
		for(i=0; i<vertices; i++)
			Q[i] = -1;
		
		for(i=0; i<vertices; i++)
			D[i] = -1;		
		
		for(i=0; i<vertices; i++)
			res[i] = -1;
		
	}
}
