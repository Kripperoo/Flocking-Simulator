class DynamicBirds extends Bird {
 private int speed=100;
 
 
 DynamicBirds(Canvas canvas) {
    super(canvas);
    //this.draw();
    }
 DynamicBirds(Canvas canvas, double xPosition, double yPosition){
  super(canvas ,new CartesianCoordinate(xPosition,yPosition));
 }

 public int getspeed(){
  return this.speed;
  
 }
 public void setspeed(int speed){
  this.speed=speed;
  
 }
 // Use undraw and draw to draw a moving Bird
 public void update (int time){
  undraw();
  int distance = speed*time/1000;
  move(distance);
    draw();
 }
 }