class Velocity{

    
 private double xLength;
 private double yLength;
 
 

 public Velocity(double x,double y)
 {
  this.xLength= x;
  this.yLength = y;
 }

 public double getX()
 {
  return this.xLength;
  }
 
 public double getY()
 {
  
  return this.yLength;
 }
 
 
 public void normalize(double v)
 {
 	double magnitude;
    
    magnitude= Math.sqrt(Math.pow(this.xLength,2)+Math.pow(this.yLength,2));
 	this.xLength= this.xLength/magnitude;
 	this.yLength= this.yLength/magnitude;
 	
 	// x and y both devide by magnitude in order to make magnitute =1 all the time.

 	if(magnitude==0)
 	{
 		this.xLength=0;
 		this.yLength=0;
 	}
 }

 public String toString()
 {
 	return "("+this.xLength + "," + this.yLength+")";
 }
 

}
	
