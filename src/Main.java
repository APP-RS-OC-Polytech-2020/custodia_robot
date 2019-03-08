import java.util.ArrayList;

/**
 * @author Johann Pistorius
 * @author Thibaud Murtin
 */
public class Main
{
    public static void main(String[] args)
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
    	}
    }	
}//lire entrée sortie analogique du robot
/*
int f = (int) r.analogInput.numAnalogInputs();
for(int i=0;i<f;i++){
	r.analogInput.setInputNumber(i);
	//System.out.println(r.analogInput);
	System.out.println("Entree numero " + i  + " value " + r.analogInput.value());
}
int g = (int) r.digitalInput.numDigitalInputs();
for(int i=0;i<g;i++){
	r.digitalInput.setInputNumber(i);
	//System.out.println(r.analogInput);
	System.out.println("Entree numero " + i  + " value " + r.digitalInput.value());
}*/