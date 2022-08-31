/**
 * 
 */
package until.utils;

/**
 * @author ZhangXiaohua
 *
 */
public class ResultMessage 
{
	private String moduleName;
	private String functionName;
	private String message;
	private String backURL;
	
	public ResultMessage(String moduleName,String functionName,String message,String backURL)
	{
		this.moduleName = moduleName;
		this.functionName = functionName;
		this.message = message;
		this.backURL = backURL;
	}
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}
	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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
	 * @return the backURL
	 */
	public String getBackURL() {
		return backURL;
	}
	/**
	 * @param backURL the backURL to set
	 */
	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}
	
	
	
	
}
