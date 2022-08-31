/**
 * 
 */
package until.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 分页信息类
 * @author ZhangXiaohua
 */
public class PageInfo 
{
	private long totalPages;
	private long curPage;
	private long recordsPerPage;
	private long totalRecords;
	private String pageURL;
	private List<Map<String, Object>> records=new ArrayList<Map<String, Object>>();
	private ArrayList<Long> pageNumList = new ArrayList<Long>();
	
	private static final int PAGELISTNUM = 5;//页号是当前页在内的5个，前2页+当前页+后来页
	
	//==============================================================
	public static PageInfo assemblePageInfo(long totalRecords,long recordsPerPage,long totalPages,long curPage,
			List<Map<String, Object>> records,String pageURL)
	{
		PageInfo pageInfo = new PageInfo();
		
		pageInfo.setTotalRecords(totalRecords);
		pageInfo.setRecordsPerPage(recordsPerPage);
		pageInfo.setTotalPages(totalPages);
		pageInfo.setCurPage(curPage);
		pageInfo.setRecords(records);
		pageInfo.setPageURL(pageURL);
		
		//计算导航页面列表
		long curPageNum = curPage;
		long startPageNum = 1;
		if(curPageNum -3<=0)
		{
			startPageNum = 1;
		}
		else
		{
			if(curPageNum + 3 > pageInfo.getTotalPages())
			{
				startPageNum = pageInfo.getTotalPages()-PAGELISTNUM +1;
			}
			else
			{
				startPageNum = curPageNum-PAGELISTNUM/2;
			}
		}
		
		for(int i=0;i<PAGELISTNUM;i++)
		{
			if(startPageNum+i<=pageInfo.getTotalPages())
			{
				pageInfo.getPageNumList().add(startPageNum+i);
			}
			else
			{
				break;
			}
		}
	
		
		return pageInfo;
	}

	
	//==============================================================
	
	
	/**
	 * @return the totalPages
	 */
	public long getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the curPage
	 */
	public long getCurPage() {
		return curPage;
	}

	/**
	 * @param curPage the curPage to set
	 */
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	/**
	 * @return the recordsPerPage
	 */
	public long getRecordsPerPage() {
		return recordsPerPage;
	}

	/**
	 * @param recordsPerPage the recordsPerPage to set
	 */
	public void setRecordsPerPage(long recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	/**
	 * @return the totalRecords
	 */
	public long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the pageURL
	 */
	public String getPageURL() {
		return pageURL;
	}

	/**
	 * @param pageURL the pageURL to set
	 */
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	/**
	 * @return the records
	 */
	public List<Map<String, Object>> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(List<Map<String, Object>> records) {
		this.records = records;
	}

	/**
	 * @return the pageNumList
	 */
	public ArrayList<Long> getPageNumList() {
		return pageNumList;
	}

	/**
	 * @param pageNumList the pageNumList to set
	 */
	public void setPageNumList(ArrayList<Long> pageNumList) {
		this.pageNumList = pageNumList;
	}

	/**
	 * @return the pagelistnum
	 */
	public static int getPagelistnum() {
		return PAGELISTNUM;
	}
	

	
	
	 
}
