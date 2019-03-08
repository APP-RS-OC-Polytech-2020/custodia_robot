import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager {
	private JSONObject obj;
	public JsonManager(JSONObject o){
		this.obj=o;
	}
	public boolean getModeManual()throws JSONException{
		boolean modeManual = (boolean) obj.getBoolean("modeManuel");
		return modeManual;
	}
	public String getIPAddress()throws JSONException{
		String address = obj.getJSONObject("robot").getString("ip");
		return address;
	}
	public float getX() throws JSONException {
		float posX = obj.getJSONObject("data").getInt("x");
		return posX;
	}
	public float getY() throws JSONException {
		float posY = obj.getJSONObject("data").getInt("y");
		return posY;
	}
	public float getForce() throws JSONException {
		float force = obj.getJSONObject("data").getInt("power");
		return force;
	}
	
	public float getRotation() throws JSONException{
		System.out.println("Rotate");
		float rotation =obj.getJSONObject("data").getLong("rotate");
		return rotation;
	}
}
