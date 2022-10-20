package com.boritgogae.board.prodReply.etc;

public class Paging {
	private int postPerPage; // 1페이지 당 보여줄 글의 갯수
	private int pageCntPerBlock; // 1개의 블럭에 보여줄 페이지 수
	   
	private int totalPage; // 전체 페이지 수
	private int totalPostCnt;  // 전체 글의 갯수
	   
	private int startNum;  // 출력을 시작할 글의 index번호

	private int totalPagingBlock;  // 전체 페이징 블럭 수
	private int currentPagingBlock; // 현재 페이지가 속한 페이징 블럭 번호
	private int startNumOfCurPagingBlock; // 출력할 블럭의 시작 페이지 번호
	private int endNumOfCurPagingBlock; // 출력할 블럭의 끝 페이지 번호
	
	
		public Paging(int postPerPage, int pageCntPerBlock, int totalPostCnt, int pageNo) {
		
			super();
		
			this.postPerPage = postPerPage;
		
			this.pageCntPerBlock = pageCntPerBlock;
		
			this.totalPostCnt = totalPostCnt;
		
			if(totalPostCnt % this.postPerPage != 0) {
				this.totalPage = (totalPostCnt / this.postPerPage) + 1;
			} else {
				this.totalPage = totalPostCnt / this.postPerPage;
			}
		
			this.startNum = this.postPerPage * (pageNo - 1);
		
			if (totalPage % this.pageCntPerBlock != 0) {
		         this.totalPagingBlock = (totalPage / this.pageCntPerBlock) + 1;
		    } else {
		         this.totalPagingBlock = totalPage / this.pageCntPerBlock;
		    }
		
			this.currentPagingBlock = (int)(Math.ceil((double)pageNo / this.pageCntPerBlock));
			this.startNumOfCurPagingBlock = ((currentPagingBlock - 1) * this.pageCntPerBlock) + 1;
			this.endNumOfCurPagingBlock = (startNumOfCurPagingBlock + this.pageCntPerBlock) - 1;
		}


	public int getPostPerPage() {
		return postPerPage;
	}

	public int getPageCntPerBlock() {
		return pageCntPerBlock;
	}


	public int getTotalPage() {
		return totalPage;
	}



	public int getTotalPostCnt() {
		return totalPostCnt;
	}



	public int getStartNum() {
		return startNum;
	}



	public int getTotalPagingBlock() {
		return totalPagingBlock;
	}



	public int getCurrentPagingBlock() {
		return currentPagingBlock;
	}



	public int getStartNumOfCurPagingBlock() {
		return startNumOfCurPagingBlock;
	}



	public int getEndNumOfCurPagingBlock() {
		return endNumOfCurPagingBlock;
	}



	@Override
	public String toString() {
		return "Paging [postPerPage=" + postPerPage + ", pageCntPerBlock=" + pageCntPerBlock + ", totalPage="
				+ totalPage + ", totalPostCnt=" + totalPostCnt + ", startNum=" + startNum + ", totalPagingBlock="
				+ totalPagingBlock + ", currentPagingBlock=" + currentPagingBlock + ", startNumOfCurPagingBlock="
				+ startNumOfCurPagingBlock + ", endNumOfCurPagingBlock=" + endNumOfCurPagingBlock + "]";
	}
	
}
