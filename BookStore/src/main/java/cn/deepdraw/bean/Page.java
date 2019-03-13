package cn.deepdraw.bean;

public class Page {

	private int pageNo; // 页码
	private int pageCount = 2; // 每页显示的数据个数
	private int totalNum; // 总的数据条数

	private int maxPage; // 最大页数
	private int lastPageSize; // 最后一页的数据个数

	public int getStart() { // 分页查询时的起始位置

		return (pageNo - 1) * pageCount;
	}

	public int getPageSize() { // 分页查询的内容数量

		return maxPage == pageNo && lastPageSize != 0 ? lastPageSize : pageCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (pageCount != other.pageCount)
			return false;
		if (pageNo != other.pageNo)
			return false;
		if (totalNum != other.totalNum)
			return false;
		return true;
	}

	public Page() {
	}

	public Page(int pageNo) {

		this.pageNo = pageNo;
	}

	public Page(int pageNo, int pageCount) {

		this.pageNo = pageNo;
		this.pageCount = pageCount;
	}

	public Page(int pageNo, int pageCount, int totalNum) {

		this.pageNo = pageNo;
		this.pageCount = pageCount;
		this.totalNum = totalNum;
		this.maxPage = totalNum % pageCount == 0 ? totalNum / pageCount : totalNum / pageCount + 1;
		this.lastPageSize = totalNum % pageCount;
	}

	public int getPageNo() {

		return pageNo;
	}

	public void setPageNo(int pageNo) {

		this.pageNo = pageNo;
	}

	public int getMaxPage() {

		return totalNum % pageCount == 0 ? totalNum / pageCount : totalNum / pageCount + 1;
	}

	public void setMaxPage(int maxPage) {

		this.maxPage = maxPage;
	}

	public int getPageCount() {

		return pageCount;
	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;
	}

	public int getTotalNum() {

		return totalNum;
	}

	public void setTotalNum(int totalNum) {

		this.totalNum = totalNum;
		this.maxPage = totalNum % pageCount == 0 ? totalNum / pageCount : totalNum / pageCount + 1;
		this.lastPageSize = totalNum % pageCount;
	}

	public int getLastPageSize() {

		return totalNum % pageCount;
	}
}
