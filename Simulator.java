import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//Canvas.class and Utils.class was wrote by Stewart Lacy and modified by lecturer Andrew James Turner.


class Simulator
{
	private JFrame frame;
	private Canvas canvas;
	private JPanel lowerPanel;
	private JButton BirdButton;
	private JButton speedcontrol;
	private ArrayList<Flock> Birds;
	private JSlider BirdSpeed;
	private JSlider Aconstant;
	private JButton alignment;
	private JButton cohesion;
	private JSlider Cconstant;
	private JButton separation ;
	private JSlider Sconstant;

	
	private Boolean clicked;



	public Simulator()
		{
			clicked = false;

            frame = new JFrame();
			canvas = new Canvas();
		    lowerPanel = new JPanel();

			Birds = new ArrayList<Flock>();
			BirdButton = new JButton("Add Birds");
			speedcontrol = new JButton("Speed");
			BirdSpeed = new JSlider(0,1000,100);	
			alignment= new JButton("Alig");
			Aconstant = new JSlider (0,20,10);
			cohesion = new JButton("Coh");
			Cconstant = new JSlider(0,20,10);
			separation  = new JButton("Sepe");
			Sconstant = new JSlider(0,20,10);

			lowerPanel.setLayout(new FlowLayout());
			frame.add(lowerPanel, BorderLayout.PAGE_END);
		    // set the button and slider on the bottom of frame.
			frame.add(canvas, BorderLayout.CENTER);
			lowerPanel.add(BirdButton);
			lowerPanel.add(speedcontrol);
			lowerPanel.add(BirdSpeed);
			lowerPanel.add(alignment);
			lowerPanel.add(Aconstant);
			lowerPanel.add(cohesion);
			lowerPanel.add(Cconstant);
			lowerPanel.add(separation );
			lowerPanel.add(Sconstant);



			frame.setTitle("Flocking Simulator");
			frame.setSize(1400, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

            // Button
			BirdButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					clicked = true;// the action is in game loop
				}
			} );

			// Speed Slider
			BirdSpeed.addChangeListener(new ChangeListener()
			{
				public void stateChanged(ChangeEvent event)
				{
				  for(Flock t: Birds)
				    {
					   int sliderValue = BirdSpeed.getValue();
					   t.setspeed(sliderValue);
				    }
				}
			} );
             
             gameLoop();
		}


   private void gameLoop()
	{
		int deltaTime = 20;
		double phase=0;
        double cohesion_weight=0;
        double separation_weight=0;
        double alignment_weight=0;
        double constant_A=0;
        double constant_C=0;
        double constant_S=0;
		while(true)
		{
			// I set separation_weight cohesion_weight alignment_weight in order to make cohesion separation and alignment to be a changing value
			// As the expect, the period of the value changing, I wish is 4s
			// That means the time cost when max value drop to minmum value then back to max is 4s
            phase=phase+0.0314;// (20ms*2*Pi*f = phase)I want period = 4s so f=0.25 then the phase is 0.0314
            separation_weight=1+Math.sin(phase);//+1 is in order to make sure the number is positive
            cohesion_weight=2-separation_weight;// make sure when separation_weight increse cohesion_weight will decrese 
            alignment_weight=(1+0.5*Math.sin(phase-3.14/2))*3;
            // phase - 3.14/2 because I want alignment shut down when separation goes to max value, then restrat after 1s
            // *0.5 becase when 1+sin() some time will casue the results to 0, then only cohesion and separation will present
            // So I need multiply a 0.5 make the alignment value always > 0
            // finally the alignment weight looks like so small when during running the Program 
            // So I times 3 make the alignment looks like normal when compare with separation and Cohesion
            
            constant_A=Aconstant.getValue()/10;
            constant_C=Cconstant.getValue()/10;
            constant_S=Sconstant.getValue()/10;

			for(Flock t: Birds)
			{
				t.undraw();
			}
            for(Flock t: Birds)
			{
				Velocity velocity = new Velocity(0,0);
				 t.update(deltaTime);

				Velocity alignment = t.alignment(Birds);
				Velocity separation  = t.separation (Birds);
				Velocity cohesion = t.cohesion(Birds);

                // plus cohesion, alignmnet and separation together and give an changing value and constant weight
				double newVelocityX = constant_A*alignment_weight*alignment.getX()+constant_C*cohesion_weight*cohesion.getX()+constant_S*separation_weight*separation .getX();
				double newVelocityY = constant_A*alignment_weight*alignment.getY()+constant_C*cohesion_weight*cohesion.getY()+constant_S*separation_weight*separation .getY();
				
				Velocity newVelocity = new Velocity(newVelocityX,newVelocityY);
				 newVelocity.normalize(1);
				double angleRad = Math.atan2(newVelocity.getY(),newVelocity.getX());
				double movedistance= Math.sqrt(Math.pow((newVelocity.getX()),2 )+Math.pow((newVelocity.getY()),2));
				double angleDeg = angleRad*(180.0/3.1415926);
				
				if(angleDeg < 0)
				{
					angleDeg = 360+angleDeg;
				}
				// set value for birds to move and Angle to move
				double angleTurn=angleDeg-t.getangle();
				t.move(movedistance);
				t.turn(angleTurn);
			    
		  // set the wall limitation, let birds only flying in the range of Jframe	

          if((t.getX()<0)||(t.getX()>1400)||(t.getY()<0)||(t.getY()>1000))
                    t.turn(10);
			}
			for(Flock t: Birds)
			{
				t.draw();
			}

			// Set the button action in gameloop, in order to avoid error.
			if (clicked==true)
			{
			
			        	Birds.add(new Flock(canvas,200, 200));
			        	Birds.add(new Flock(canvas, 330, 250));
			        	Birds.add(new Flock(canvas, 350, 350));
			        	
			        clicked=false;
             }

			Utils.pause(deltaTime);
		}
	}

	public static void main(String [ ] args)
	{
		new Simulator();
	}
}
