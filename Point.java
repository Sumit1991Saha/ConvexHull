package GrahamScan;

public class Point {

	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double z){
		x = z;
	}
	
	public void setY(double z){
		y = z;
	}
	
	public String toString(){
		return this.x+" "+this.y;
	}

	public double calcPolar(double x , double y){   // here x & y represents co-ordinates of starting point 
													// and we need values w.r.t starting point  Therefore val1-val2 gives us our required soln
		/*double val1=0.0, val2=0.0;
		
		if(this.x>0 && this.y>0){
			val1 = Math.toDegrees(Math.atan2(this.y, this.x));
		}
		if(this.x<0 && this.y>0){
			val1 = Math.toDegrees(Math.atan2(this.y,this.x));
		}
		if(this.x<0 && this.y<0){
			val1 = 360 + Math.toDegrees(Math.atan2(this.y,this.x));
		}
		if(this.x>0 && this.y<0){
			val1 = 360.00 + Math.toDegrees(Math.atan2(this.y,this.x));
		}
		
		
		
		
		if(x>0 && y>0){
			val2 = Math.toDegrees(Math.atan2(y, x));
		}
		if(x<0 && y>0){
			val2 = Math.toDegrees(Math.atan2(y,x));
		}
		if(x<0 && y<0){
			val2 = 360 + Math.toDegrees(Math.atan2(y,x));
		}
		if(x>0 && y<0){
			val2 = 360.00 + Math.toDegrees(Math.atan2(y,x));
		}
			
		return val1-val2;
		*/
		
		double val =0.0;
		
		x = this.x - x;
		y = this.y - y;
		
		if(x>0 && y>0){
			val = Math.toDegrees(Math.atan2(y, x));
		}
		if(x<0 && y>0){
			val = Math.toDegrees(Math.atan2(y,x));
		}
		if(x<0 && y<0){
			val = 360 + Math.toDegrees(Math.atan2(y,x));
		}
		if(x>0 && y<0){
			val = 360.00 + Math.toDegrees(Math.atan2(y,x));
		}
		return val;
	}
}
