/**
 * 
 */
package until.utils;


public class JSONResult 
{
	private boolean success;
	private String message;
	private Object datas;
	
	public JSONResult(boolean success,String message,Object datas)
	{
		this.success = success;
		this.message = message;
		this.datas = datas;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the datas
	 */
	public Object getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
	
}
