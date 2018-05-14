package com.cn.custom_service.pojo;

import java.util.List;

public class PageBean<T> {
	
		private int currentPage;
		private int pageSize; 

		
		private List<T> recordList; 
		private int recordCount; 

		
		private int pageCount; 
		private int beginPageIndex; 
		private int endPageIndex; 

		public PageBean() {
		}

		
		public PageBean(int currentPage, int pageSize, List recordList, int recordCount) {
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.recordList = recordList;
			this.recordCount = recordCount;

			
			pageCount = (recordCount + pageSize - 1) / pageSize;

			
			if (pageCount <= 10) {
				beginPageIndex = 1;
				endPageIndex = pageCount;
			}
			
			else {
				beginPageIndex = currentPage - 4; // 7 - 4 = 3;
				endPageIndex = currentPage + 5; // 7 + 5 = 12; --> 3 ~ 12

				
				if (beginPageIndex < 1) {
					beginPageIndex = 1;
					endPageIndex = 10;
				}
				
				else if (endPageIndex > pageCount) {
					endPageIndex = pageCount;
					beginPageIndex = pageCount - 9;
				}
			}
		}

		public List getRecordList() {
			return recordList;
		}

		public void setRecordList(List recordList) {
			this.recordList = recordList;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getRecordCount() {
			return recordCount;
		}

		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
		}

		public int getBeginPageIndex() {
			return beginPageIndex;
		}

		public void setBeginPageIndex(int beginPageIndex) {
			this.beginPageIndex = beginPageIndex;
		}

		public int getEndPageIndex() {
			return endPageIndex;
		}

		public void setEndPageIndex(int endPageIndex) {
			this.endPageIndex = endPageIndex;
		}
}
