import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import rec.robotino.com.Bumper;
import rec.robotino.com.Com;
import rec.robotino.com.Motor;
import rec.robotino.com.OmniDrive;
import rec.robotino.com.Odometry;
/**
 * @author Johann Pistorius
 * @author Thibaud Murtin
 */
public class Robot implements Runnable
{
    protected final String hostname;
    protected final Com com;
    protected final Motor motor1;
    protected final Motor motor2;
    protected final Motor motor3;
    protected final OmniDrive omniDrive;
    protected final Bumper bumper;
    protected final Odometry odometry;
    
    protected final float[] startVector = new float[]
    {
        200.0f, 0.0f
    };
    protected boolean connectionStatus;
    protected boolean isManual;
    
    
    
    
    public boolean isManual() {
		return this.isManual;
	}
	public Robot(String hostname){
        this.hostname = hostname;
        com = new Com();
        motor1 = new Motor();
        motor2 = new Motor();
        motor3 = new Motor();
        omniDrive = new OmniDrive();
        bumper = new Bumper();
        odometry=new Odometry();
        this.isManual=true;
    }
    /**
     * Start robotino
     */
    public void start(){
        System.out.println("Robot started.");
        
        try{
            System.out.println("Initializing...");
            init();
            System.out.println("Connecting...");
            connect(hostname);
            System.out.println("Connected.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            //disconnect();
        }

        System.out.println("Done.");
    }
    /**
     * Initialize omnidrive
     */
    protected void init(){
        motor1.setComId(com.id());
        motor1.setMotorNumber(0);

        motor2.setComId(com.id());
        motor2.setMotorNumber(1);

        motor3.setComId(com.id());
        motor3.setMotorNumber(2);

        omniDrive.setComId(com.id());

        bumper.setComId(com.id());
    }
    /**
     * Connect to robotino
     * @param hostname
     */
    protected void connect(String hostname){
        com.setAddress(hostname);
        com.connect();
    }
    /**
     * Disconnect from robot
     */
    protected void disconnect(){
        com.disconnect();
    }
    /**
     * Returns whether the robot is connected or not
     * @return connection status
     */
    protected boolean isConnected(){
    	return com.isConnected();
    }
	public void run() {
		while(true){
			if(true == bumper.value()){
	    		System.out.println("BUMPER!!!");
				omniDrive.setVelocity(0, 0, 0);
			} 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
   /**
    * Move robot using parameters x and y
    * @param x
    * @param y
    * @param force
    * @throws InterruptedException
    */
    protected void drive(float x, float y, float force) throws InterruptedException{
    	if(com.isConnected() && false == bumper.value() ){
            omniDrive.setVelocity(x*force, y*force, 0);
            //omniDrive.setVelocity((float)(5*Math.sqrt(x*x+y*y)), 0, angle/4);
            //	vx 	Velocity in x-direction in mm/s
        	//	vy 	Velocity in y-direction in mm/s
        	//	omega 	Angular velocity in deg/s     
        }
    }
    
    /**
     * Rotate the robot
     * @param value
     * @throws InterruptedException
     */
    protected void rotate(float rotation) throws InterruptedException{
    	if(com.isConnected() && false == bumper.value()){
    		//  vx 	Velocity in x-direction in mm/s
        	//	vy 	Velocity in y-direction in mm/s
        	//	omega 	Angular velocity in deg/s
            omniDrive.setVelocity(0, 0, 18*rotation);
        }
    }
    protected void burn(){
    	while (!Thread.interrupted() && com.isConnected() && false == bumper.value()){
            omniDrive.setVelocity(0, 0, 90);
        }
    }
    /**
     * Process JSON information coming from server
     * @param JSON
     */
    public void dataProcessing(JSONObject JSON){
    	String mode="";
		try {
			mode = JSON.getString("mode");
			System.out.println(mode);
			if(mode.equals("rotation")){
				try {
					this.drive(0,0,0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				JsonManager obj=new JsonManager(JSON);
				float rotation = obj.getRotation();
				try {
					this.rotate(rotation);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else if(mode.equals("commandeAuto")){
				JsonManager obj=new JsonManager(JSON);
				boolean modeManual = obj.getModeManual();
				this.isManual=modeManual;	
			}else if(mode.equals("burn")){
				this.burn();
			}else{
				JsonManager obj=new JsonManager(JSON);
				float posX = obj.getX();
				float posY = obj.getY();
				float force = obj.getForce();
				try {
					this.drive(posX,posY,force);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
    } 
}