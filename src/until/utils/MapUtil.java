/**
 * 
 */
package until.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author ZhangXiaohua
 *
 */
public class MapUtil 
{
	/**
	 * ��Map<String,String[]>���͵�����ת��Ϊ Map<String, String>����
	 * @param oldMap
	 * @return
	 */
	public static Map<String, Object> convertFromMutiToSingle(Map<String,String[]> oldMap)
	{
		Map<String, Object> restult = new HashMap<String, Object>();
		
		for(String key:oldMap.keySet())
		{
			String[] values = oldMap.get(key);
			if(values == null || values.length==0)
			{
				restult.put(key, "");
			}
			else if(values.length==1)
			{
				restult.put(key, values[0]);
			}
			else
			{
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<values.length-1;i++)
				{
					sb.append(values[i]+",");
				}
				sb.append(values[values.length-1]);
				
				restult.put(key, sb.toString());
			}
		}

		
		return restult;
	}
	
	//����ҳ���õ����ݴ���Object��
	public static Object[] getInputData(Map<String,String[]> paramsOld) {
	    Object[] result = new Object[paramsOld.size()];
		int i=0;
		for(Object keys: paramsOld.keySet()) {
			result[i] = paramsOld.get(keys)[0];
			i++;
		}
		return result;
	}
	
//	���û���ַ�����ȡ����Ʒ������
	public static List<Map<String, Object>> InsertGoodTable(List<Map<String, Object>> goodTable) {
		UserServiceImpl userService = new UserService();
	    for(int i=0; i<goodTable.size(); i++) {
	    	//��ȡ�û���Ϣ
	    	Map<String, Object> vaildateGet = userService.vaildateGet((String)goodTable.get(i).get("users_account"));
	    	String address = (String)vaildateGet.get("users_address");
	    	goodTable.get(i).put("users_address", address.substring(0,2));
	    }
	    return goodTable;
	}
}
