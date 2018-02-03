class CartesianCoordinate
{   
    
 private double xPosition;
 private double yPosition;
 

 public CartesianCoordinate(double x,double y)
 {
  this.xPosition = x;
  this.yPosition = y;
 }
 public double getX()
 {
  // System.out.println("the x value:" + xPosition);
  return this.xPosition;
  
 }
 public double getY()
 {
  // System.out.println("the y value:" + yPosition);
  return this.yPosition;
  
 }
 public CartesianCoordinate copy(){
  return new CartesianCoordinate(xPosition,yPosition);
 }
 public void add(double dx, double dy){
  xPosition= xPosition+dx;
  yPosition= yPosition+dy;
  
 }

}