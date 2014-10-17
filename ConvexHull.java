package GrahamScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class ConvexHull {

	private Point[] points;
	private double[] polar_theta;
	private int No_of_points;
	private Stack<Point> ConvexHull;
	
	public ConvexHull(int N){
	
		points = new Point[N];
		polar_theta = new double[N];
		No_of_points = N;
		ConvexHull = new Stack<Point>();
	}
	
	public void assignValues() throws NumberFormatException, IOException{
		
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<No_of_points;++i) {
		
			double a = Double.parseDouble(br.readLine());
			double b = Double.parseDouble(br.readLine());
		
			points[i] = new Point(a, b);
			polar_theta[i] = 0.0;
		}*/
		points[0] = new Point(1.0,1.0);
		points[1] = new Point(2.0,2.0);
		points[2] = new Point(2.5,3.0);
		points[3] = new Point(4.0,4.0);
		points[4] = new Point(-1.0,1.0);
		points[5] = new Point(-1.0,-1.0);
		points[6] = new Point(3.0,-4.0);
		points[7] = new Point(1.5,4.0);
	}
	
	public void dispCoOrdinates() {
		for(int i=0; i<No_of_points; ++i){
			System.out.println(points[i].getX() + " " + points[i].getY());
		}
	}
	
	public void dispPolar_theta() {
		for(int i=0;i<No_of_points; ++i){
			System.out.println(points[i].getX()+" "+points[i].getY()+" "+polar_theta[i]+" ");
		}
	}
	
	public void swap(Point a, Point b){
		/*Point temp = new Point(a.getX(), a.getY());   // Shallow copy occurs here which is only local for this block
		a=b;
		b=temp;
		*/
		
		Point temp = new Point(a.getX(), a.getY());     // whereas this piece of code does deep copy
		a.setX(b.getX());
		a.setY(b.getY());
		b.setX(temp.getX());
		b.setY(temp.getY());
	}
	 
	public void findStartingPoint(){
		
		double ymin = points[0].getY();
		int start = 0;
		   for (int i = 1; i < No_of_points; i++)
		   {
		     double y = points[i].getY();
		 
		     // Pick the bottom-most or choose the right most point in case of tie
		     if ((y < ymin) || (ymin == y && points[i].getX() > points[start].getX())) {
		        ymin = points[i].getY();
		     	start = i;
		     }
		   }
		   swap(points[0],points[start]);    // here points[0] gives us the starting location
		
		   

	}
	
	public void calcPolarAngle() {
		
		polar_theta[0] = 0.0;
		for(int i=1; i < No_of_points; i++){
			
			polar_theta[i] = points[i].calcPolar(points[0].getX(),points[0].getY());   // calculating polar angle of all the points w.r.t starting point
			
		}
	}
	
	
	public void sortUsingPolarAngle(){
		Arrays.sort(polar_theta); // Assuming O(n log n)             //point corresponding to polar_theta[0] after sorting gives the starting point
	}
	
	public void sortPointArray(){   // Need to improve upon this peice of code as it is O(n^2)
		Point[] temp = new Point[No_of_points];
		for(int i=0; i<No_of_points;++i){
			temp[i] = new Point(points[i].getX(), points[i].getY());
		}
		
		for(int i=1; i<No_of_points; ++i){  //to sort the array according to increasing polar angle w.r.t starting point
			for(int j=1; j<No_of_points; ++j){
				if(temp[j].calcPolar(temp[0].getX(), temp[0].getY()) == polar_theta[i]){
					points[i] = temp[j];
					break;
				}
			}
		}
	}
	
	public int CounterClockWise(Point a, Point b, Point c){
		
		int delta = (int) (((b.getX() - a.getX())*(c.getY()-a.getY())) - ((b.getY()-a.getY())*(c.getX()-a.getX()))) ;
		
		if(delta < 0)       // clockwise
			return -1; 
		if(delta > 0)       // counterclockwise
			return 1;
		else				// point a,b,c are collinear
			return 0;
	}
	
	public void findPeriphery(){
		
		
		
		ConvexHull.push(points[0]);
		ConvexHull.push(points[1]);     // these two points are definately on the hull
		
		for(int i = 2 ; i < No_of_points ; ++i) {
			
			System.out.println(i);
			Point top = ConvexHull.pop();
			
			while(CounterClockWise(ConvexHull.peek(), top, points[i]) == -1) {  // discarding points that would 
				top = ConvexHull.pop();										  // create clockwise turn
				//dispStackContents(); 
			}
			
			ConvexHull.push(top);
			ConvexHull.push(points[i]);
			dispStackContents();
		}
		
		
		while(!ConvexHull.isEmpty()) {  // prints the points in reverse order
			
			Point temp = ConvexHull.pop();
			System.out.println(temp.getX()+" "+temp.getY());
			
		}
	}
	
	public void dispStackContents(){
		System.out.println();
		Stack<Point> temp = new Stack<Point>();
		while(!ConvexHull.isEmpty()){
			Point top = ConvexHull.pop();
			System.out.println(top.getX()+" "+top.getY());
			temp.push(top);
		}
		
		while(!temp.isEmpty()){
			Point top = temp.pop();
			ConvexHull.push(top);
		}
		
		System.out.println();
	}
	
	public void dispStartingPoint(){
		System.out.println("Starting Point  " + points[0].getX()+" "+points[0].getY());
	}
	
	
	public void dispPointArray() {
		for(int i=0; i<No_of_points; ++i )
			System.out.println(points[i].getX()+" "+points[i].getY());
	}
	
	public static void main(String []args) throws NumberFormatException, IOException{		
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int No_of_points = 8;//Integer.parseInt(br.readLine());
		
		ConvexHull ch = new ConvexHull(No_of_points);
		
			
		ch.assignValues();
		
		ch.findStartingPoint();
		//ch.dispStartingPoint();
		
		ch.calcPolarAngle(); // calculates polar angle of all points with respect to the starting points. 
		//ch.dispPolar_theta();
		
		
		System.out.println();
		ch.sortUsingPolarAngle();
		ch.sortPointArray(); // sorts the point array in increasing polar angle w.r.t first i.e starting point
		ch.dispPolar_theta();
		
		//Now Graham scan Algorithm starts here
		
		System.out.println();
		System.out.println();
		ch.findPeriphery();
	}
}


