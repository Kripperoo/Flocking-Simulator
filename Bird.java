class Bird {
    /*
     * From the basis of this class you should implement a working
     * Bird robot.
     * You will need to decide how to finish these methods as well as what fields
     * to provide.
     *
     */

private Canvas canvas;// private field reference to a canvas object
 boolean isPenDown;
 double angle;
 CartesianCoordinate birdPosition; 
    private double xVelocity;
    private double yVelocity;

    public Bird(Canvas canvas) {
        this.canvas = canvas;
        isPenDown = true ;
        angle=0;
        //birdPosition = new CartesianCoordinate(500,300);
    }
 
     public Bird(Canvas canvas, CartesianCoordinate initLocation){
      this.canvas=canvas;
      this.isPenDown=false;
      this.angle = 0;
      this.birdPosition = initLocation;
     }

    public void putPenUp() {
  isPenDown = false;
        // TODO
    }

    public void putPenDown() {
  isPenDown = true;
        // TODO
    }

    public void turn(double amount) {
  angle = angle + amount;
  }
    public double getVelocityX(double input){
      return this.xVelocity=Math.cos(3.1415926*input/180);
 }
   public double getVelocityY(double input){
      return this.yVelocity =Math.sin(3.1415926*input/180);
 }
    public double getX(){
      return this.birdPosition.getX();
 }
    
    public double getY(){
      return this.birdPosition.getY();
 }
    
    public double getangle(){
      return this.angle;
}
    
    public void move(double pixel)
     {
        double radians = Math.toRadians(angle);
        double dx = pixel*Math.cos(radians);
        double dy = pixel*Math.sin(radians);
        
        
        CartesianCoordinate oldlocation=birdPosition.copy();
        birdPosition.add(dx,dy);
        
        if(isPenDown)
        {
         canvas.drawLineBetweenPoints(birdPosition,oldlocation);
        }
    }

 public void draw(){
     putPenDown();
     turn(120);
     move(20);
    
     turn(120);
     move(20);
    
     turn(120);
     move(20);

     //turn(90);
     //move(10);
     //Utils.pause(100);
     //canvas.removeMostRecentLine();
     //move(20);

     putPenUp();
 }
 public void undraw(){
 canvas.removeMostRecentLine();
 canvas.removeMostRecentLine();
 canvas.removeMostRecentLine();
 //canvas.removeMostRecentLine();
 
 }
 
}