import java.util.ArrayList;

/*The flocking algorithm idea was learned from: 
http://gamedevelopment.tutsplus.com/tutorials/the-three-simple-rules-of-flocking-behaviors-alignment-cohesion-and-separation--gamedev-3444 
wrote by Vijay Pemmaraju.*/

class Flock extends DynamicBirds{
	private Velocity cohesion;
	private Velocity alignment;
	private Velocity separation;
    

	Flock (Canvas canvas)
	{
		super(canvas);
    }

	Flock (Canvas canvas, double xPosition, double yPosition)
	{
      super(canvas ,xPosition,yPosition);
    }

 public Velocity alignment(ArrayList<Flock> Birds)
    {
        Velocity Velocity= new Velocity(0,0);
        int neighbors = 0;
        double x=0;
        double y=0;

        int radius = 100 ;


        for(Bird t : Birds)
        {
            if((this.birdPosition.getX() != t.getX()) && (this.birdPosition.getY() != t.getY()))

            {
                double distance=Math.sqrt(Math.pow((this.getX()-t.getX()),2 )+Math.pow((this.getY()-t.getY()),2));
                if(distance <radius)

                {    
                	// Calculate the total Velocity of x and y direction
                    x=x + getVelocityX(t.getangle());
                    y=y + getVelocityY(t.getangle());
                    neighbors++;
                }
            }
        }
             if(neighbors > 0)
                {
                	//Calculate average x and y direction
                    x = (x / neighbors);
                    y = (y / neighbors);

                    Velocity newVelocity= new Velocity(x, y);
                    newVelocity.normalize(1);

            
                    return newVelocity;

                }
                 else
                    {
                        return Velocity;
                    }

    }

public Velocity cohesion (ArrayList<Flock> Birds)
    {
        Velocity Velocity= new Velocity(0, 0);
        int neighbors = 0;
        double x=0;
        double y=0;
        int radius = 100;

        for(Bird t : Birds)
        {
            if((this.birdPosition.getX() != t.getX()) && (this.birdPosition.getY() != t.getY()))

            {
                double distance=Math.sqrt(Math.pow((this.birdPosition.getX()-t.getX()),2)+Math.pow((this.birdPosition.getY()-getY()),2));
                if(distance< radius)
                {
                    x=x + t.getX();
                    y=y + t.getY();
                    // calculate total position
                    neighbors++;
                }
            }
        }

        if(neighbors > 0)
        {
            double avgX = (x / neighbors);
            double avgY = (y/ neighbors);
            //calculate average position (the center of mass) 

            Velocity newVelocity= new Velocity(avgX-this.birdPosition.getX(), avgY-this.birdPosition.getY());
            newVelocity.normalize(1);
    


            return newVelocity;
        }

        else
        {
            return Velocity;
        }
    }
public Velocity separation(ArrayList<Flock> Birds)
    {
        Velocity Velocity= new Velocity(0, 0);
        int neighbors = 0;

        int radius = 100;
        double x=0;
        double y=0;

        for(Bird t : Birds)
        {
            if((this.birdPosition.getX() != t.getX()) && (this.birdPosition.getY() != t.getY()))
            {
                double distance=Math.sqrt(Math.pow((this.getX()-t.getX()),2 )+Math.pow((this.getY()-getY()),2));
                if(distance< radius)
                {
                    x=x + t.getX();
                    y=y + t.getY();
                    // calculate the Total Position
                    neighbors++;
                }
            }
        }

        if(neighbors > 0)
        {
            double avgX = x / (1.0*neighbors);
            double avgY = y/ (1.0*neighbors);
            // Calculate the average position of x and y
            double vx = avgX-this.getX();
            double vy = avgY-this.getY();

            vx *= - 1;
            vy *= -1;
            // Times -1 to achieve separation


            Velocity newVelocity= new Velocity(vx, vy);
            newVelocity.normalize(1);
           
            return newVelocity;
        }

        else
        {
            return Velocity;
        }
    }
}
