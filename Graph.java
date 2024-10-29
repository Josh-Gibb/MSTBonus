import java.text.NumberFormat;
import java.util.*;

/**
 * @author Josh Gibb
 * graph.java
 * A class that uses the minimum spanning tree algorithm to find the minimum path to visit all nodes
 */
public class Graph {
    //Declaring all objects
    public NumberFormat fmt =  NumberFormat.getCurrencyInstance(Locale.US);
    private int[][] adjMatrix;
    private String[] cityNames;
    private int size;
    
    // Class Constructor
    public Graph(String[] cityList){
        size = cityList.length;
        cityNames = cityList;
        adjMatrix = new int[size][size];
        // Setting all values of 2D array to Integer.MAX_VALUE
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++){
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    
    // Setting the weight for an edge in the graph
    public void addEdge(int c1, int c2, int weight){
        adjMatrix[c1][c2] = weight;
        adjMatrix[c2][c1] = weight;
    }
    
    // Finds the minimum weight starting at the first node
    public int prims(){
        return prims(0);
    }
    
    // Finds the minimum weight starting at a specific node
    public int prims(int node){
        if(node < 0 || node >= size)
        {
            System.out.println("City not found in the graph");
            return -1;
        }
        ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(node);
        return prims(start);
    }
    
    // A recursive method that finds the minimum path
    private int prims(ArrayList<Integer> visited){
        // Recursive end case
        if(visited.size() == size){
            return 0;
        }
        int smallestEdge = Integer.MAX_VALUE;
        int starter = -1;
        int vertex = -1;
        // Finds the next unvisited node with the smallest edge
        for(int i = 0; i < visited.size(); i++){
            for(int j = 0; j < size; j++){   
                if(smallestEdge > adjMatrix[visited.get(i)][j]){
                    if(!visited(j, visited)){
                        smallestEdge = adjMatrix[visited.get(i)][j];
                        starter = visited.get(i);
                        vertex = j;
                    }
                }
            }
        }
        // Adds the vertex to visited
        visited.add(vertex);
        int cost = smallestEdge*1000;
        System.out.println(cityNames[starter] + " - " + cityNames[vertex] + "(" + fmt.format(cost) + ")");
        return smallestEdge + prims(visited);
    }
    
    // A method to check if the node has been visited or not
    private boolean visited(int j, ArrayList<Integer> visited){
        for(int i = 0; i < visited.size(); i++)
        {
            if(visited.get(i) == j){
                return true;
            }
        }
        return false;
    }
    
    // Adds an edge when given destination in a String format
    public void addEdge(String c1, String c2, int weight){
        if(c1.equalsIgnoreCase(c2))
            return;
        int v1 = -1;
        int v2 = -1;
        // Finds the number equivalent for the city names list
        for(int i = 0; i < size; i++){
            if(c1.equalsIgnoreCase(cityNames[i]))
                v1 = i;
            if(c2.equalsIgnoreCase(cityNames[i]))
                v2 = i;
        }
        adjMatrix[v1][v2] = weight;
        adjMatrix[v2][v1] = weight;
    }
    
    // Finds the minimum path traveled for a node, given the city name
    public int prims(String cityName){
        ArrayList<Integer> start = new ArrayList<Integer>();
        int pos = -1;
        for(int i = 0; i < size; i++)
        {
            if(cityName.equalsIgnoreCase(cityNames[i]))
                pos = i;
                
        }
        if(pos != -1){
            start.add(pos);
            return prims(start);
        }
        System.out.println("Name not found in the graph");
        return -1;
    }
}  
