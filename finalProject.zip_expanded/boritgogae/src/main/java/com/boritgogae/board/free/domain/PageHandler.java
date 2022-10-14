package com.boritgogae.board.free.domain;


import org.springframework.web.util.*;

public class PageHandler {
    private SearchCondition sc;
//    private int pageSize = 10; // 한 페이지당 게시물 갯수
//    private int page; // 현재 페이지
//    private String  option;
//    private String  keyword;
    public  final int NAV_SIZE = 10; // page navigation size
    private int totalCnt; // 게시물의 총 갯수
    private int totalPage; // 전체 페이지의 갯수
    private int beginPage; // 화면에 보여줄 첫 페이지
    private int endPage; // 화면에 보여줄 마지막 페이지
    private boolean showNext = false; // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showPrev = false; // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false

    public PageHandler(int totalCnt, Integer page) {
        this(totalCnt, new SearchCondition(page, 10));
    }

    public PageHandler(int totalCnt, Integer page, Integer pageSize) {
        this(totalCnt, new SearchCondition(page, pageSize));
    }

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc) {
        this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize()==0? 0:1);
        this.sc.setPage(Math.min(sc.getPage(), totalPage));  // page가 totalPage보다 크지 않게
        this.beginPage = (this.sc.getPage() -1) / NAV_SIZE * NAV_SIZE + 1; // 11 -> 11, 10 -> 1, 15->11. 따로 떼어내서 테스트
        this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
        this.showPrev = beginPage!=1;
        this.showNext = endPage!=totalPage;
    }
    
    public String getQueryString() {
        return getQueryString(this.sc.getPage());
    }

    public String getQueryString(Integer page) {
        // ?page=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("page",     page)
                .queryParam("pageSize", sc.getPageSize())
                .queryParam("option",   sc.getOption())
                .queryParam("keyword",  sc.getKeyword())
                .build().toString();
    }
    
    void print() { 
        System.out.println("page="+ sc.getPage());
        System.out.print(showPrev? "PREV " : "");

        for(int i=beginPage;i<=endPage;i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext? " NEXT" : "");
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getNAV_SIZE() {
        return NAV_SIZE;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", showNext=" + showNext +
                ", beginPage=" + beginPage +
                ", NAV_SIZE=" + NAV_SIZE +
                ", totalPage=" + totalPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                '}';
    }
}










//public class PageHandler {
//	
//	private int totalCnt; // 총 개시물 갯수
//	private int pageSize; // 한 페이지의 크기
//	private int naviSize =10; // 페이지내비게이션 크기
//	private int totalPage; // 전체 페이지 갯수
//	private int page; //현재페이지
//	private int beginPage; // 네비게이션 첫번째 페이지
//	private int endPage; // 네비게이션 마지막 페이지
//	private boolean showPrev; // 이전 페이지 이동하는 링크 부여
//	private boolean showNext; // 다음 페이지 이동하는 링크 부
//	
//	
//	public PageHandler(int totalCnt, int page) {
//		this(totalCnt,page,3);
//	}
//	
//	public PageHandler(int totalCnt, int page,int pageSize) {
//	
//		this.totalCnt = totalCnt;
//		this.pageSize = pageSize;
//		this.page = page;
//		
//		
//		
//		totalPage = (int)Math.ceil(totalCnt/(double)pageSize);
//		
//		beginPage= page/naviSize*naviSize+1; // 페이지 앞부분 1~5(1), 6~10(6)
//		endPage =Math.min(beginPage+naviSize-1 , totalPage);//Math.min 둘중 작은값을 써
//		showPrev = beginPage!=1;
//		showNext = endPage!= totalPage;
//		
//		
//		
//		
//	
//	}
//
//
//
//
//	
//
//	@Override
//	public String toString() {
//		return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
//				+ ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
//				+ ", showerPrev=" + showPrev + ", showerNext=" + showNext + "]";
//	}
//
//
//
//
//	public int getTotalCnt() {
//		return totalCnt;
//	}
//
//
//
//
//	public void setTotalCnt(int totalCnt) {
//		this.totalCnt = totalCnt;
//	}
//
//
//
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//
//
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//
//
//
//	public int getNaviSize() {
//		return naviSize;
//	}
//
//
//
//
//	public void setNaviSize(int naviSize) {
//		this.naviSize = naviSize;
//	}
//
//
//
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//
//
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//
//
//
//	public int getPage() {
//		return page;
//	}
//
//
//
//
//	public void setPage(int page) {
//		this.page = page;
//	}
//
//
//
//
//	public int getBeginPage() {
//		return beginPage;
//	}
//
//
//
//
//	public void setBeginPage(int beginPage) {
//		this.beginPage = beginPage;
//	}
//
//
//
//
//	public int getEndPage() {
//		return endPage;
//	}
//
//
//
//
//	public void setEndPage(int endPage) {
//		this.endPage = endPage;
//	}
//
//
//
//
//	public boolean isShowPrev() {
//		return showPrev;
//	}
//
//
//
//
//	public void setShowPrev(boolean showPrev) {
//		this.showPrev = showPrev;
//	}
//
//
//
//
//	public boolean isShowNext() {
//		return showNext;
//	}
//
//
//
//
//	public void setShowNext(boolean showerNext) {
//		this.showNext = showerNext;
//	}
//	
//	
//	
//	
//	
//	
//
//
//
//
//
//
//
//
//
//
//
//	
//	
//	
//	
//
//
//}
