package yjc.wdb.bbs.bean;

public class Pagination {
	private int startPage;
	private int endPage;
	private int totalCount;
	private boolean prevLink;
	private boolean nextLink;
	private int currentPage;
	private int numOfArticlesPerPage = 3;
	private int numOfPageLinks = 5;
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		startPage = (int)((currentPage-1)/numOfPageLinks)*numOfPageLinks+1;
		endPage = startPage+numOfPageLinks-1;
		int totalPages = (int)Math.ceil((double)totalCount/numOfArticlesPerPage);
		if(totalPages < endPage) endPage = totalPages; 
		prevLink = true;
		if(startPage == 1) prevLink =false;
		nextLink = true;
		if(endPage == totalPages) nextLink = false;
	}
	public boolean isPrevLink() {
		return prevLink;
	}
	public void setPrevLink(boolean prevLink) {
		this.prevLink = prevLink;
	}
	public boolean isNextLink() {
		return nextLink;
	}
	public void setNextLink(boolean nextLink) {
		this.nextLink = nextLink;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumOfArticlesPerPage() {
		return numOfArticlesPerPage;
	}
	public void setNumOfArticlesPerPage(int numOfArticlesPerPage) {
		this.numOfArticlesPerPage = numOfArticlesPerPage;
	}
	public int getNumOfPageLinks() {
		return numOfPageLinks;
	}
	public void setNumOfPageLinks(int numOfPageLinks) {
		this.numOfPageLinks = numOfPageLinks;
	}

}
