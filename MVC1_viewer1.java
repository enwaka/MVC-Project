//import java.awt.Button;



//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Label;
//import java.awt.TextField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.List;

//import java.util.ArrayList;
//import java.util.InputMismatchException;
import java.util.List;
//import java.util.Scanner;
//import java.util.Collections;
import processing.core.PApplet;
import processing.core.PFont;
/*
//this is where i start



*/

/**
 * The viewer class of a simple Model-View-Controller (MVC) system.
 * The MVC system displays a 50x50 network of vertices with left,
 * right, up, and down edges to their neighbors.
 * This class is the main class, as it extends PApplet, which will 
 * automatically call setup() on start-up, and will repeatedly call
 * draw(), 30 times a second.  
 * Constantly redraws the network of vertices and edges held by the 
 * model, 30 times a second.  
 * Passes special conditions to the controller for decision.  For
 * example, when the mouse is over a vertex, or when the mouse
 * is clicked somewhere on the canvas of the applet.
 * 
 * @author D. Thiebaut
 *
 */
public class MVC1_viewer1 extends PApplet{
        MVC1_controller         controller = null;
        MVC1_model              model      = null;
        Vertex view=null;
        public  int             WIDTH      = 800; // best generic width
        public  int                     HEIGHT     = 800; // best generic height
        private final int       DELTAX     = 10;  // no pixels between vertices
        private final int       DELTAY     = 10;  // no pixels between vertices
        private int             MINDIST    = 5;   // min dist for vertex detected 
        private int             vertexUnderMouse = -1;
        private int seeYou;
        private Integer realYou=null;
        private Integer realYou1=null;
        private Button1 button1=null; 
        private Button1 button2=null;
        private Button1 button3=null;
        private Button1 button4=null;
        private Button1 button5=null;
        private Button1 button6=null;
        private Button1 button7=null;
        private Button1 button8=null;
       // PFont f;
        //private Integer first=null;
       // private Integer second=null;
int checker=0;
    // Variable to store text currently being typed
    Integer typing=35;

    // Variable to store saved text when return is hit
    //Integer saved =null;
    //Integer t=0;

public int getX(){
return mouseX;
}
public int getY(){
return mouseY;
}
        /**
         * mutator: sets a reference to the model.
         * @param m reference to the model
         */
        public void setModel( MVC1_model m ) {
                model = m;
        }
       
        /**
         * helper function.  Given a vertex number, returns its position
         * (x, y) on applet canvas. Vertex 0 will be at (DELTAX, DELTAY).
         * @param vertex the vertex to position
         * @param noRows the number of rows in the network
         * @param noCols the number of columns in the network
         * @return an int array.  xy[0] is x, xy[1] is y.  In pixels.
         */
        public int[] getXY( int c, int noRows, int noCols ) {//make the int vertex to Vertex vertex
        //	int vertex =c.Id;
                int[] xy = new int[2];
                xy[0] = DELTAX + (int) ( c / noRows ) * DELTAX;
                xy[1] = DELTAY + ( c % noRows ) * DELTAY;
                return xy;
        }

        /**
         * computes the number of vertex located at location (row, col).
         * @param row
         * @param col
         * @return the number (int) of the vertex.
         */
        private int vertexAtRowCol( int row, int col ) {
                int v = row + col * model.getNoRows();
                if ( v < 0 || v > model.getNoVertices() )
                        return -1;
                return v;
        }

        /**
         * the entry point for the MVC system.  The PApplet will automatically
         * call setup() first.  This is the place that initializes everything
         * up, including controller and model.  Gets the controller to link
         * the 3 systems together.  The controller will initialize the model with
         * the graph.
         */
        public void setup() {
                
                controller = new MVC1_controller( );
                controller.initAll( this );
                
                WIDTH = DELTAX * (model.getNoCols() + 1 );
                HEIGHT = 30 + DELTAY * (model.getNoRows() + 1 );
                
                //--- create the button ---
                button1 = new Button1( this, 10, HEIGHT-25, "CC" );
                button2=new Button1(this, 60, HEIGHT-25, "Name");
                button3=new Button1(this, 120, HEIGHT-25, "Dijs");
                button4=new Button1(this,180,HEIGHT-25,"DFS");
              
                button5=new Button1(this, 240, HEIGHT-25, "Prob-5");
                button6=new Button1(this,310,HEIGHT-25,"InitG");
                button7=new Button1(this,370,HEIGHT-25,"Prob+5");
                button8=new Button1(this,450,HEIGHT-25,"FUN");
             
                //--- set Applet geometry ---
                size( WIDTH, HEIGHT );
                smooth();
        }
        
        /**
         * return the vertex that is within MINDIST pixels of the current mouse location.
         * @return the vertex number, or -1 if none found.
         */
        public Integer closestVertexToMouse() {
                int row = round( (mouseY-DELTAY) / DELTAY );
                int col = round( (mouseX-DELTAX) / DELTAX );
                int v = vertexAtRowCol( row, col );
                if ( v == -1 ) 
                        return -1;
                //Vertex c=new Vertex(v);//created this to make it compatible with getXY
                int[] xy = getXY( v, model.getNoRows(), model.getNoCols() );//c here used to be v
                if ( dist( xy[0], xy[1], mouseX, mouseY ) < MINDIST ) 
                        return v;
                return -1;
        }
        
//edge under mouse
        /**
         * public int closestEdgeUnderMouse(){
         * int row = round((mouseY-DELTAY)/DELTAY);
         * 
         */
        /**
         * draw() is called automatically 30 times a second by mechanism inside
         * the PApplet that is hidden from the programmer.  The role of draw() is
         * to erase the screen (too fast for the user to notice) and redraw the 
         * graph with vertices and edges, and whatever text needs to be displayed
         * on the canvas.
         * Detects if the mouse pointer is over a vertex and if so, calls the 
         * controller to decide what should be done in this case.
         */
        public void draw() {
                // erase window, make background white
                background( 255, 255, 255 );
                
                // draw button
                button1.draw();
                button2.draw();
                button3.draw();
                button4.draw();
                button5.draw();
                button6.draw();
                
                button7.draw();
                button8.draw();
                // get geometry of grid
                int V = model.getNoVertices();
                int noRows = model.getNoRows();
                int noCols = model.getNoCols();
                
                vertexUnderMouse = closestVertexToMouse();
                if(button3.isON()){
              //  List<Vertex> path=new ArrayList<Vertex>();
                //List<Vertex> path = getShortestPathTo(v);
               if(realYou!=null){
              //drawCircles1(realYou);
              //model.computePaths(model.vertices[realYou])  ; 
               controller.setVertexUnderMouse( realYou );
             
              int[] xy = getXY( realYou, model.getNoRows(), model.getNoCols() );
                
                
                
                ellipse( xy[0], xy[1], 10, 10 );
               // realYou=null;
               }
               if(realYou1!=null){
              @SuppressWarnings("unchecked")
              List <Vertex>	path = model.getShortestPathTo(model.vertices[realYou1]);
               
             
              for ( Vertex u:path ) { //try to change int u to vertex u
             
              int[] zy = getXY( u.Id, model.getNoRows(), model.getNoCols() );
                        
              fill( 0, 255, 0 );
                       ellipse( zy[0], zy[1], 10, 10 );
                       
             
                 } 
               
             
               }
               
                }
if(button3.isOFF()){
realYou1=null;
realYou=null;
}
if(button8.isON()){
//size(500, 400);
 	 if (mousePressed) {
 	    background(10, 80, 100);
 	  } 
 	 background(10, 80, 100);
 	 stroke(255, 255, 255);
 	  fill(160, 220, 90);
 	  ellipse(mouseX, 200, 300, 300);
 	  fill(160, 0, 90);
 	  ellipse(mouseX, 200, 90, 90);
 	  fill(160, 210, 230);
 	  rect(245, mouseY, 10, 240);
 	  
 
}
             
           
              
                for ( int v = 0; v < V; v++ ) {
                        // get coordinates of Vertex v
               
                        int[] xy = getXY( v, noRows, noCols );
                        
                        // draw black disk for Vertex v
                        ellipse( xy[0], xy[1], 4, 4 );
                        
                        // get adjacency list for each vertex and display edges
                        if (button1.isON() ){
                       
                        //int t=0;
                        for ( int u:  model.adjList( v ) ) { //try to change int u to vertex u
                                int[] xy2 = getXY( u, noRows, noCols );//t here used to be u
                                
                                line( xy[0], xy[1], xy2[0], xy2[1] );
                                
                               // t++;
                                
                        }  
                        
                       
                       
                        } 
                     
                       
                       if(button2.isON()){
                     
                      fill(204, 102, 0);
                     rect(20,90,400,400);
                     
                     fill(0, 102, 153);
                     String s="\n Esther Nwaka\n 12/19/2014\n\n Final Project Description\n\n I used papplet to implement vertex class\n\n Use the buttons below to control the program how ever you want"
                     	 + "\n\n Note: My computePathFunction(Dijks button) works but it  computes for the first two mouse clicks instead of the first mouseClicked and \nthe vertex under   mouse"
                     	 + "\n\n My Prob- button allows the user to dynamically reduce the probability by 5 after every click\n"
                     	 + " \nMy Prob+ button allows the user to dynamically increase the probability by 5 after every click\n"
                     	 + " \nUse my FUN button to play when done\n \n I hope you enjoy my program\n"
                     	 + "\nThank you";
                     text(s, 50, 90, 300, 400); 
                   
                       }
                       
                }
              
               //This redraws the graph, to test if this well, remove the seed setting and you will see the graph redrawn
             if(button6.isON()){
                model.initGraph();
             
                }
             
             if(button7.isON()){
         
         
            fill(0);
           
           // Display everything
          fill(204, 102, 0);
          rect(25,40,300,300);
          fill(0, 0, 255);
          text("Click me and type. \n The probabilty increases by 5.  ", 25, 50);
          //keyPressed();
         
          text(typing+" this is what you chose",40,90);
          typing=typing+5;
            model.setP(typing);
            model.initGraph();
         	 button7.switchState();  
         	} 
             
             //This button reduces probability by 5
             if(button5.isON()){
            fill(204, 102, 0);
       	 rect(25,40,300,300);
       	 fill(0, 0, 255);
       	 text("Click me and type. \n The probabilty decreases by 5.  ", 25, 50);
       	 //keyPressed();
       
       	 text(typing+" this is what you chose",40,90);
            typing=typing-5;
            model.setP(typing);
            model.initGraph();
            button5.switchState();
             }
          
               // vertexUnderMouse = closestVertexToMouse();
               if ( vertexUnderMouse != -1 ){
                        controller.setVertexUnderMouse( vertexUnderMouse );
                      
                      
                        if(button4.isON()){
                        for(int i=0;i<model.visited.length;i++){
                        //	Vertex iConverter=new Vertex(i);//created this to help fit getXY
                       
                if(model.visited[i]==true){
                fill( 255, 0, 0 ); // make vertices red
                            strokeWeight( 1 ); 
                            
                            int[] zy = getXY( i, noRows, noCols );//iConverter here used to be i
                            
                            // draw black disk for Vertex v
                            ellipse( zy[0], zy[1], 6, 6 );
                           
                }    // make line width 2 pixels
                else{
               
                fill( 0, 255, 0 ); // make vertices black
                                strokeWeight( 1 ); 
                                int[] xy = getXY( i, noRows, noCols );
                                
                                // draw black disk for Vertex v
                                ellipse( xy[0], xy[1], 6, 6 );
                                
                }
                        }
               }
                }
            
        }
      
        /**
         * displays the vertex number next to the circle representing it.
         * This is typically done whenever the mouse pointer is over a vertex.
         * @param vertexNo
         */
        public void displayVertexName(int vertexNo ) {
                // get position of vertex
        Vertex converter=new Vertex(vertexNo);//created this to help fit getXY
                int[] xy = getXY( vertexNo, model.getNoRows(), model.getNoCols() );
                // set text color to red 
                fill( 255, 0, 0 );  
                // display text
                text( " "+vertexNo, xy[0], xy[1] );
        }
        
        public void displayAdjacencyList( int vertexNo ) {
                String s = "";
               // int t=0;
                for ( int u:  model.adjList( vertexNo ) ) {//changed in u: to Vertex u
                        s += u+ ", ";//t here used to be u
                }
                // remove last ", " part of the string
                s = s.trim();
                if ( s.length() > 0 )
                        s = s.substring( 0, s.length()-1 );
                text( s, 100, HEIGHT-25 );
        }
       
       
        public void drawCircles( int vertexNo ) {
        //Vertex converter=new Vertex(vertexNo);//created this to help fit getXY
            int[] xy = getXY( vertexNo, model.getNoRows(), model.getNoCols() );
            int radius = frameCount % 15;
            ellipse( xy[0], xy[1], 2*radius, 2*radius );
           
    }
        public void drawCircles1( int vertexNo ) {
       
            int[] xy = getXY( vertexNo, model.getNoRows(), model.getNoCols() );
            
            
            ellipse( xy[0], xy[1], 10, 10 );
            
    }
 

        /**
         * this is called automatically by the Applet if the user left-clicks the mouse
         * on the canvas of the applet.  The location of the click can be found in mouseX
         * and mouseY.
         */
        public void mouseClicked() {
                System.out.println( "Mouse clicked at "+mouseX + ", " + mouseY );
                if ( button1.containsMouse() ) {
                    button1.switchState();
            }
                if ( button2.containsMouse() ) {
                    button2.switchState();
            }

                if ( button3.containsMouse() ) {
                    button3.switchState();
            }
                if ( button4.containsMouse() ) {
                    button4.switchState();
            }
                if ( button5.containsMouse() ) {
                   button5.switchState();
            } 

                if ( button6.containsMouse() ) {
                    button6.switchState();
            } 
                if ( button7.containsMouse() ) {
                    button7.switchState();
            } 
                if ( button8.containsMouse() ) {
                    button8.switchState();
            } 
                seeYou=closestVertexToMouse();
                
           try{
               
                if(seeYou!=-1){
                System.out.println("This is "+seeYou);
                //controller.setVertexUnderMouse(seeYou);
                if(realYou!=null&&realYou1==null){
                realYou1=seeYou;
                System.out.println("This is realYou1:"+realYou1);
                }
               
                if(realYou==null){
               
                realYou=seeYou;
               
                System.out.println("This is the realyou:"+seeYou);
                }
                }
           }
           catch(ArrayIndexOutOfBoundsException e){
          //do nothing
           }
      
        }
     
       static public void main(String args[]) {
            PApplet.main( "MVC1_viewer1" );
      }

       
}
