package yjc.wdb.bbs.bean;

public class SearchCondition {
	private String filterBy;
	private String searchKey;
	private int currentPage;
	private int numOfRecordsPerPage;
	
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setNumOfRecordsPerPage(int numOfRecordsPerPage) {
		this.numOfRecordsPerPage = numOfRecordsPerPage;
	}
	public int getNumOfRecordsPerPage() {
		return numOfRecordsPerPage;
	}
	/* BoardMapper.xml�� ���ǵ� SQL���� limit ���� startRecordIndex ���� �����ϱ� ���� �޼ҵ� */
	public int getStartRecordIndex() {
		int startRecordIndex = (currentPage-1)*numOfRecordsPerPage;
		return startRecordIndex;
	}
	/* View���� ��ũ ������ �� ���� �޼ҵ� */
	public String getSearchParam() { 
		if (filterBy != null) {
			return "&filterBy="+filterBy+"&searchKey="+searchKey;
		}
		return "";
	}
}
