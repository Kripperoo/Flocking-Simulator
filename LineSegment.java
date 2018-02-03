class LineSegment
{   
 private CartesianCoordinate startpoint;
 private CartesianCoordinate endpoint;
 
 public LineSegment(CartesianCoordinate startpoint , CartesianCoordinate endpoint)
 {
  this.startpoint=startpoint;
  this.endpoint= endpoint;
 }
 
 public CartesianCoordinate Getstartpoint()
 {
  
  return this.startpoint;
 }
 public CartesianCoordinate Getendpoint()
 {
  
  return this.endpoint;
 }
 public double DistanceBetween(CartesianCoordinate startpoint , CartesianCoordinate endpoint)
 {
  double xlength= endpoint.getX()-startpoint.getX();
  double ylength = endpoint.getY()-startpoint.getY();
  double square = xlength*xlength+ylength*ylength;
  double xylength= Math.sqrt(square);
  return xylength;
 }
}