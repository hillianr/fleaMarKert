package service.impl;

import java.util.List;
import java.util.Map;

public interface IndentServiceImpl {

	List<Map<String, Object>> vaildateindent(String account);

	void reindent( String id);

	Map<String, Object> accept_number(String account, String id);

	void insert_indent(Object[] insertRegister);

	void update_indent(Object[] insertRegister3);

	void update_really(int id1);

	List<Map<String, Object>> find_indent(String account, String id);

	void update_indent_shop(Object[] insertRegister3);

	

}
