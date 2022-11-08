package com.boritgogae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.boritgogae.domain.AdminOrdersPagingInfo;
import com.boritgogae.domain.CartDTO;
import com.boritgogae.domain.DetailOrderVo;
import com.boritgogae.domain.GuestOrderDTO;
import com.boritgogae.domain.OrderDetailDTO;
import com.boritgogae.domain.OrdersVo;
import com.boritgogae.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Inject
	public OrderDAO dao;

	@Override
	public boolean addCart(CartDTO cart) throws Exception {
		boolean result = false;

		if (dao.addCart(cart) == 1) {
			result = true;
		}

		return result;
	}

	@Override
	public List<CartDTO> getCartList(String memberId) throws Exception {

		List<CartDTO> cart = dao.getCartList(memberId);

		// cartDTO에는 가격정보 없는데,, 가격정보 있는 DTO사용?

		return cart;

	}

	@Override
	public List<DetailOrderVo> popularProd() throws Exception {
		List<DetailOrderVo> lst = dao.getPopular();
		return lst;
	}

	// 비회원 로그인 하기 위해 주문내역 조회하는 메서드
	@Override
	public OrdersVo guestOrderInfo(GuestOrderDTO gdto) throws Exception {
		return dao.selectGuestOrderInfo(gdto);
	}

	// 관리자 주문조회용
	@Override
	public Map<String, Object> getOrders(int pageNo) throws Exception {
		AdminOrdersPagingInfo pi = adminPagingProcess(pageNo);
		Map<String, Object> map = new HashMap<>();
		List<OrdersVo> order = dao.getOrders(pi);
		
		map.put("order", order);
		map.put("pi", pi);
		
		return map;
	}

	private AdminOrdersPagingInfo adminPagingProcess(int pageNo) throws Exception {

		AdminOrdersPagingInfo result = new AdminOrdersPagingInfo();

		result.setTotalPostCnt(dao.countOrder()); // 전체 글의 갯수 setting

		result.setTotalPage(result.getTotalPostCnt()); // 전체 페이지수 setting

		result.setStartNum(pageNo); // 현재페이지에서 출력을 시작할 글 번호(index)

		// --페이징블럭을 위한부분
		result.setTotalPagingBlock(result.getTotalPage()); // 전체 페이지 블럭수 setting
		result.setCurrentPagingBlock(pageNo);// 현재페이지가 속한 페이징 블럭 setting
		result.setStartNumOfCurPagingBlock(result.getCurrentPagingBlock()); // 현재 페이징 블럭의 출력 시작 번호 setting
		result.setEndNumOfCurPagingBlock(result.getStartNumOfCurPagingBlock()); // 현재 페이징 블럭의 출력 끝 번호 setting
		System.out.println(result.toString());

		return result;
	}

	// 관리자 상세주문조회용
	@Override
	public List<OrderDetailDTO> getDetailOrdersInfo(int orderNo) throws Exception {
		System.out.println(dao.getDetailOrderInfo(orderNo) + "이곳은 서비스여@@@");
		return dao.getDetailOrderInfo(orderNo);
	}

	@Override
	public List<OrderDetailDTO> getAdminTodoList() throws Exception {

		return dao.getAdminTodoList();
	}

	@Override
	public int countOrder() throws Exception {

		return dao.countOrder();
	}

	@Override
	public int adminAllowOrders() throws Exception {

		return dao.adminAllowOrders();
	}

}
