import java.util.ArrayList;

//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Random;
//import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

//import Graph1.AdjMatIterator;
import processing.core.PApplet;

//import java.util.PriorityQueue;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//from my class
//import java.util.PriorityQueue;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;



//class graph
//This is where I inserted his code

/**
* Code taken from http://www.algolist.com/code/java/Dijkstra's_algorithm
*/

/**
* Implementation of a Vertex object.
* Each vertex has a unique Id.  The first vertex created
* has an Id of 0. 
* Each vertex contains a list of edges that emanate from it.
*/
class Vertex implements Comparable<Vertex>{
      public final int Id;
      
      //public Edge[]adj;
      
      public ArrayList<Edge> adj= new ArrayList<Edge>();    // adjacency list for vertex //i changed this to be 2D
      public double minDistance = Double.POSITIVE_INFINITY;
      public Vertex predecessor;
     // HashSet set=new HashSet();
      public Vertex(int i) {  Id = i; /**predecessor = null; /**adj= new ArrayList<Edge>();**/}
      public int getId(){
    	  return Id;
      }
      
     
      
      public String toString() {      return Id + " "; }
      public int compareTo(Vertex other) {
              return Double.compare(minDistance, other.minDistance);
      }
   
}

/**
* Implementation of an edge.  An edge is directed and goes from
* source vertex to dest vertex.  It has a real weight.
*/
class Edge {
    //  public final Vertex source;
      public final Vertex dest;
      public final double weight;

      public Edge(/**Vertex argSource,**/ Vertex argTarget, double argWeight) {
          //    source = argSource;
              dest   = argTarget;
              weight = argWeight;
      }
}

 
/**
 * Model component of Model-View-Controller system.
 * Implements the graph, holds the vertices (numbered 0 to maxNoVertices)
 * and keeps the edges in a boolean adjacency matrix, that is 
 * maxNoVertices x maxNoVertices;
 * @author D. Thiebaut
 *
 */
public class MVC1_model extends PApplet{
        private final int maxNoVerticesRows = 50;       // geometry
        private final int maxNoVerticesCols = 50;       // 50 x 50 grid = 2500 vertices total
        public int maxNoVertices               = maxNoVerticesRows * maxNoVerticesCols;//changed this from private to public
       // public boolean[][] adjMat              = null;     // adjacency matrix
        public int probability100              = 35;       // probability (as percentage) for an edge (35 means 35%)
        public boolean visited[];
        public Vertex[] vertices;
      
        //public List<Vertex> path = new ArrayList<Vertex>();
        private MVC1_viewer1 viewer         = null;// reference to the viewer of the MVC system
        private MVC1_controller controller = null;// reference to the controller of the MVC system
        
        public void setP(int t){
        	probability100=t;
        }
        /**
         * constructor. Nothing to do.  The building of the network is done
         * by initGraph().
         */
        public MVC1_model( ) {
        	 //vertices
        }
       
        /**
         * mutator. Sets the reference to the controller.
         * @param c
         */
        public void setController(MVC1_controller c) {
                controller = c;         
        }

        /**
         * mutator. Sets the reference to the viewer.
         * @param v
         */
        public void setViewer( MVC1_viewer1 v ) {
                viewer = v;             
        }

        /**
         * accessor
         * @return number of vertices in graph
         */
        public int getNoVertices() {
                return maxNoVertices;
        }
        
        public int getNoCols() {
                return maxNoVerticesCols;
        }
        
        public int getNoRows() {
                return maxNoVerticesRows;
        }
        
        /**
         * add an undirected edge between u and v.
         * @param v
         * @param u
         */
       
        
    
        
       public void addEdge( int v, int u ) { 
        	//Vertex vV=new Vertex(v);
        	//Vertex uV=new Vertex(u);
              
               vertices[v].adj.add(new Edge(vertices[u],1));
               vertices[u].adj.add(new Edge(vertices[v],1));
                //vV.adj=new Edge[]{new Edge(uV,1)};
                //uV.adj=new Edge[]{new Edge(vV,1)};	
        	//vV.adj.add(new Edge(uV,1));
        	//uV.adj.add(new Edge(vV,1));
                
        }
        
       
  
        /**
         * returns vertex at given row and col.  Assumes row & col will always be valid.
         * @param row
         * @param col
         * @return the vertex at row, col
         */
        private int vertexAtRowCol( int row, int col ) {
                return row + col * maxNoVerticesRows;
        }
        
        /**
         * generate a random graph.  The seed of the random number generator
         * is set to a fixed number, so that the graph generated is always the same.
         */
        //@SuppressWarnings("unchecked")
		public void initGraph() {
                Random random = new Random();
                random.setSeed( 12345 );                                // seed for random number generator
              
             // createV();
          	vertices= new Vertex[maxNoVertices];
          	for(int i=0;i<maxNoVertices;i++){
          		
          		vertices[i]=new Vertex(i);
          	}
               
          	
                for ( int r=1; r<maxNoVerticesRows; r++ ) {
                        for ( int c=1; c<maxNoVerticesCols; c++ ) {
                                int currentVertex = vertexAtRowCol(r, c); 
                                int upVertex      = vertexAtRowCol(r-1, c);
                                int leftVertex    = vertexAtRowCol(r, c-1);                             
                                
                                // should we create an up connection?
                                if ( random.nextInt(100) <= probability100  ) {
                                        addEdge( currentVertex, upVertex );
                                	
                                     
                                }
                                 //should we create a left connection?
                               if ( random.nextInt(100) <= probability100 ) {
                                      addEdge( currentVertex, leftVertex );
                                       
                                }
                        }
                }
        }
        
        /**
         * returns the adjacency list of Vertex v in an arrayList.
         * @param v for which we want the adjacent vertices.
         * @return an arrayList of vertices.  The vertices numbers are
         * listed in increasing order.
         */
        public ArrayList<Integer> adjList(int v ) {//also changed parameter of int to vertex!!! and<Iteger> i changed also
               Vertex dv=new Vertex(v);
        	ArrayList<Integer> adj = new ArrayList<Integer>();//the same on this line
                for ( Edge e:vertices[v].adj) {
        	adj.add(e.dest.Id);
               }
                               
                		
                
                return adj;
        }

      
       
        private void recurseDFS( int v ) {
        	 visited[ v ] = true;
        	 for ( int w: adjList( v ) )
        	 if ( ! visited[w] )
        	 recurseDFS( w );
        	 }

        	 public void DFS( int v ) {
        	 visited = new boolean[ maxNoVertices ];
        	 recurseDFS( v );
        	 }
        	 public void doAll(){
        		 for(int v=0;v<maxNoVertices;v++){
        			 DFS(v);
        		 }
        	 }

        	 public  void computePaths(Vertex source)
        	    {
        	        source.minDistance = 0.;
        	        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        	      	vertexQueue.add(source);

        		while (!vertexQueue.isEmpty()) {
        		    Vertex u = vertexQueue.poll();

        	            // Visit each edge exiting u
        	            for (Edge e : u.adj)
        	            {
        	                Vertex v = e.dest;
        	                double weight = e.weight;
        	                double distanceThroughU = u.minDistance + weight;
        			if (distanceThroughU < v.minDistance) {
        			    vertexQueue.remove(v);
        			    v.minDistance = distanceThroughU ;
        			    v.predecessor = u;
        			    vertexQueue.add(v);
        			}
        	            }
        	        }
        	    }

          /**
           * Return an ArrayList of vertices that go from source (see Dijkstra)
           * to target.
           * @param target the destination of the path.
           * @return an arraylist of vertices
           */
        	 public  List getShortestPathTo(Vertex target)
        	    {
        	        List<Vertex> path = new ArrayList<Vertex>();
        	        for (Vertex vertex = target; vertex != null; vertex = vertex.predecessor)
        	            path.add(vertex);
        	        Collections.reverse(path);
        	        return path;
        	    }
  //this is where i pasted init graph
          
        

          /**
           * generates a graph with 7 vertices
           * @return array of vertices
           */
         

          /**
           * displays the shortest paths found from the start vertex to ALL
           * reachable vertices.
           * @param start
           */
          public void displayShortestPaths(Vertex start) {
          	//int t=start.Id;//modify to give an int to dijsktra because it was not happy with satrt as a paraameter since I changed that in its declaration
                  computePaths(start);
                  for (Vertex v : vertices) {
                          System.out.println("Distance to " + v + ": " + v.minDistance);
                         @SuppressWarnings("unchecked")
						List<Vertex> path = getShortestPathTo(v);
                         System.out.println("Path: " + path);
                  }
          }  	    
        	
          
         
}



 
      

       

