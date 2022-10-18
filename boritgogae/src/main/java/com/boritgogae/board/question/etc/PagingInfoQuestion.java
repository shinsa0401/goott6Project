package com.boritgogae.board.question.etc;

public class PagingInfoQuestion {
	   private int postPerPage = 7; // 1페이지 당 보여줄 글의 갯수
	   private int pageCntPerBlock = 7; // 1개의 블럭에 보여줄 페이지 수
	   
	   private int totalPage; // 전체 페이지 수
	   private int totalPostCnt;  // 전체 글의 갯수
	   
	   private int startNum;  // 출력을 시작할 글의 index번호

	   private int totalPagingBlock;  // 전체 페이징 블럭 수
	   private int currentPagingBlock; // 현재 페이지가 속한 페이징 블럭 번호
	   private int startNumOfCurPagingBlock; // 출력할 블럭의 시작 페이지 번호
	   private int endNumOfCurPagingBlock; // 출력할 블럭의 끝 페이지 번호
	   
	   
	   public int getPostPerPage() {
	      return postPerPage;
	   }

	   public void setPostPerPage(int postPerPage) {
	      this.postPerPage = postPerPage;
	   }

	   public int getPageCntPerBlock() {
	      return pageCntPerBlock;
	   }

	   public void setPageCntPerBlock(int pageCntPerBlock) {
	      this.pageCntPerBlock = pageCntPerBlock;
	   }

	   public int getTotalPage() {
	      return totalPage;
	   }

	   public void setTotalPage(int totalPostCnt) {
//	      전체 페이지 수 구하기 : 전체 글 갯수 / 1페이지 당 보여줄 글의 갯수
//	      --     단, 위의 공식에서 나누어 떨어지지 않으면 전체 페이지수 = 나눈 몫 + 1 이된다
	      if(totalPostCnt % this.postPerPage != 0) {
	         this.totalPage = (totalPostCnt / this.postPerPage) + 1;
	      } else {
	         this.totalPage = totalPostCnt / this.postPerPage;
	      }
	   }

	   public int getTotalPostCnt() {
	      return totalPostCnt;
	   }

	   public void setTotalPostCnt(int totalPostCnt) {
	      this.totalPostCnt = totalPostCnt;
	   }

	   public int getStartNum() {
	      return startNum;
	   }

	   public void setStartNum(int pageNo) {
	      //페이징시작index 번호  = 페이지당 보여줄 글의 갯수 * (현재페이지 번호  - 1)
	      this.startNum = this.postPerPage * (pageNo - 1);
	   }

	   public int getTotalPagingBlock() {
	      return totalPagingBlock;
	   }

	   public void setTotalPagingBlock(int totalPage) {
	      // 전체 페이징 블럭수 : 전체 페이지 수 / 페이징 블럭(단 나누어 떨어지지 않으면 +1)
	      if (totalPage % this.pageCntPerBlock != 0) {
	         this.totalPagingBlock = (totalPage / this.pageCntPerBlock) + 1;
	      } else {
	         this.totalPagingBlock = totalPage / this.pageCntPerBlock;
	      }
	   }

	   public int getCurrentPagingBlock() {
	      return currentPagingBlock;
	   }

	   public void setCurrentPagingBlock(int pageNo) {
	      // 현재 페이징 블럭 : 현재 페이지 번호 / 페이징 블럭 (단 나누어 떨어지지 않으면 올림)
	      this.currentPagingBlock = (int)(Math.ceil((double)pageNo / this.pageCntPerBlock));
	   }

	   public int getStartNumOfCurPagingBlock() {
	      return startNumOfCurPagingBlock;
	   }

	   public void setStartNumOfCurPagingBlock(int currentPagingBlock) {
	      // 현재 페이지의 블럭 시작번호 : ((현재 페이징 블럭 - 1) * 페이징블럭수) + 1
	      this.startNumOfCurPagingBlock = ((currentPagingBlock - 1) * this.pageCntPerBlock) + 1;
	   }

	   public int getEndNumOfCurPagingBlock() {
	      return endNumOfCurPagingBlock;
	   }

	   public void setEndNumOfCurPagingBlock(int startNumOfCurPagingBlock) {
	      // 현재 페이지의 블럭 끝 번호 : (현재 페이징 시작번호 + 페이징 블럭수) - 1
	      this.endNumOfCurPagingBlock = (startNumOfCurPagingBlock + this.pageCntPerBlock) - 1;
	   }

	   @Override
	   public String toString() {
	      return "PagingInfo [postPerPage=" + postPerPage + ", pageCntPerBlock=" + pageCntPerBlock + ", totalPage="
	            + totalPage + ", totalPostCnt=" + totalPostCnt + ", startNum=" + startNum + ", totalPagingBlock="
	            + totalPagingBlock + ", currentPagingBlock=" + currentPagingBlock + ", startNumOfCurPagingBlock="
	            + startNumOfCurPagingBlock + ", endNumOfCurPagingBlock=" + endNumOfCurPagingBlock + "]";
	   }
}
