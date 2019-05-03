import java.util.ArrayList;

import org.json.JSONException;

/**
 * @author Johann Pistorius
 * @author Thibaud Murtin
 */
public class Main
{
    public static void main(String[] args) throws InterruptedException, JSONException
    {
    	int port=50008;
		String ipServer="193.48.125.70";
    	ArrayList<String> hostname=new ArrayList<String>();
    	String ipRobot = "193.48.125.37";
    	//String ipRobot = "193.48.125.38";
    	hostname.add(ipRobot);
    	for(String host:hostname) {
    		Robot r=new Robot(System.getProperty("hostname", host));
    		SocketRobotino socketRobotino = new SocketRobotino(ipServer, port,r,host);
    		new Thread(socketRobotino).start();
    		new Thread(r).start();
    		r.start();
    		while(true){
    			Thread.sleep(500);
    			socketRobotino.odometry();
    		}

    	}
    }	
}