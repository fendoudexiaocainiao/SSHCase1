package com.wanghao.domain;

public class PageBean<T> {
	private int currentPage = 1;
	private int currentRecords = 0;
	private int maxRecords = 0;
	private int maxPages = 0;
	private int beginRecords = 0;
	private T target = null;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage != currentPage) {
			if(this.maxPages >0 && currentPage>this.maxPages){
				this.currentPage = this.maxPages;
				this.beginRecords = (this.currentPage - 1) * this.currentRecords;
			}else {
				this.currentPage = currentPage;
				this.beginRecords = (this.currentPage - 1) * this.currentRecords;
			}
		}
	}

	public int getCurrentRecords() {
		return currentRecords;
	}

	public void setCurrentRecords(int currentRecords) {
		if (this.currentRecords != currentRecords && currentRecords > 0) {
			this.currentRecords = currentRecords;
			if (this.maxRecords != 0) {
				this.maxPages = (int) Math.ceil(((double) this.maxRecords / this.currentRecords));
				this.currentPage = (int) Math.ceil(((double) this.beginRecords / this.currentRecords));
			}
			this.beginRecords = (this.currentPage - 1) * this.currentRecords;
		}
	}

	public int getMaxRecords() {
		return maxRecords;
	}

	public void setMaxRecords(int maxRecords) {
		if (this.maxRecords != maxRecords && maxRecords > 0) {
			this.maxRecords = maxRecords;
			this.maxPages = (int) Math.ceil(((double) this.maxRecords / this.currentRecords));
			if (this.currentPage > this.maxPages) {
				this.currentPage = this.maxPages;
			}
		}
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
	}

	public int getBeginRecords() {
		return beginRecords;
	}

	public void setBeginRecords(int beginRecords) {
		if (beginRecords % this.currentRecords == 0 && beginRecords >= 0) {
			this.beginRecords = beginRecords;
		} else {
			this.beginRecords = (beginRecords >= 0) ? (int) ((Math.ceil(((double) beginRecords / this.currentRecords)) - 1) * this.currentRecords) : this.beginRecords;
		}
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PageBean<?> pageBean = (PageBean<?>) o;

		if (getCurrentPage() != pageBean.getCurrentPage()) return false;
		if (getCurrentRecords() != pageBean.getCurrentRecords()) return false;
		if (getMaxRecords() != pageBean.getMaxRecords()) return false;
		if (getMaxPages() != pageBean.getMaxPages()) return false;
		if (getBeginRecords() != pageBean.getBeginRecords()) return false;
		return getTarget() != null ? getTarget().equals(pageBean.getTarget()) : pageBean.getTarget() == null;
	}

	@Override
	public int hashCode() {
		int result = getCurrentPage();
		result = 31 * result + getCurrentRecords();
		result = 31 * result + getMaxRecords();
		result = 31 * result + getMaxPages();
		result = 31 * result + getBeginRecords();
		result = 31 * result + (getTarget() != null ? getTarget().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"currentPage=" + currentPage +
				", currentRecords=" + currentRecords +
				", maxRecords=" + maxRecords +
				", maxPages=" + maxPages +
				", beginRecords=" + beginRecords +
				", target=" + target +
				'}';
	}
}
