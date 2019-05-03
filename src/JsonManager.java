import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager {
	private JSONObject obj;
	public JsonManager(JSONObject o){
		this.obj=o;
	}
	/**
	 * Get robot mode
	 * @return manual mode status
	 * @throws JSONException
	 */
	public boolean getModeManual()throws JSONException{
		boolean modeManual = (boolean) obj.getBoolean("manualMode");
		return modeManual;
	}
	/**
	 * Get robot ip address
	 * @return ip address
	 * @throws JSONException
	 */
	public String getIPAddress()throws JSONException{
		String address = obj.getJSONObject("robot").getString("ip");
		return address;
	}
	/**
	 * Get movement in X axis
	 * @return x
	 * @throws JSONException
	 */
	public float getX() throws JSONException {
		float posX = obj.getJSONObject("data").getInt("x");
		return posX;
	}
	/**
	 * Get movement in Y axis
	 * @return y
	 * @throws JSONException
	 */
	public float getY() throws JSONException {
		float posY = obj.getJSONObject("data").getInt("y");
		return posY;
	}
	/**
	 * Get power coefficient
	 * @return power
	 * @throws JSONException
	 */
	public float getForce() throws JSONException {
		float force = obj.getJSONObject("data").getInt("power");
		return force;
	}
	/**
	 * Get rotation
	 * @return rotation
	 * @throws JSONException
	 */
	public float getRotation() throws JSONException{
		float rotation =obj.getJSONObject("data").getInt("rotate");
		return rotation;
	}
	public JSONObject sendPhi(float phi) throws JSONException{
		JSONObject odometry=new JSONObject();
		odometry.put("type", "odometry");
		odometry.put("phi", phi);
		return odometry;
	}
}
