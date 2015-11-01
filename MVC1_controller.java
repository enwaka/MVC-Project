import java.awt.Graphics;

import java.awt.event.MouseEvent;

import processing.core.PApplet;



/**

 * MVC1_controller.  

 * The controller of a simple Model-View-Controller (MVC) system.

 * The MVC system displays a 50x50 network of vertices with left,

 * right, up, and down edges to their neighbors.

 * The controller is started by the viewer, which extends PApplet,

 * and which is the first object to become alive.  

 * The controller starts the model which initializes the graph.

 * Every graphical interaction in the viewer is passed on to the

 * controller. 

 * 

 * @author Esther Nwaka

 *

 */

public class MVC1_controller {

        // links to the viewer and model.

        MVC1_viewer1 viewer     = null;

        MVC1_model      model      = null;



        /**

         * constructor.  

         */

        public MVC1_controller() {

        }



        /**

         * mutator

         * @param v reference to the viewer

         */

        public void setViewer( MVC1_viewer1 v ) {

                viewer = v;

        }

        

        /**

         * mutator

         * @param m reference to the model

         */

        public void setModel( MVC1_model m ) {

                model = m;

        }

        

        /**

         * launched by the viewer, which passes a reference to itself in the

         * process.  Creates a set of references between the model, the view, 

         * and the controller.

         * Initializes the graph held by the model (vertices and edges)

         * @param v reference to the viewer.

         */

        public void initAll( MVC1_viewer1 v ) {

                // initialize viewer

                viewer = v;

                

                // create the model 

                model  = new MVC1_model();

                

                // create reference system between MVC components

                model.setController( this );

                viewer.setModel( model );

                model.setViewer( viewer );

                

                // create the graph of vertices and edges

                model.initGraph();

        }



        /**

         * called by viewer when the mouse is over a given vertex.

         * decides what viewer should do, depending on different 

         * modes of operation.

         * @param vertex

         */

        public void setVertexUnderMouse( int vertex ) {

                // display vertex name

                viewer.displayVertexName( vertex );

                // display adjacency list

                viewer.displayAdjacencyList( vertex );

              viewer.drawCircles(vertex);
              //  viewer.containsMouse1(vertex); 
               
              //viewer.drawCircles1( vertex ) ;
                model.DFS(vertex);
                model.computePaths(model.vertices[vertex])  ;  
               // model.getShortestPathTo(new Vertex(vertex));
              //  model.removeEdge(vertex_remove_1, vertex_remove_2);
              //  viewer.containsMouse1(vertex);   
               // model.containsMouse1(vertex);
        }
        
      public void doer(int vertex){
        model.getShortestPathTo(model.vertices[vertex]);
      }

}
