package kr.co.bithotel;

import kr.co.bithotel.controller.BitHotelController;

public class BitHotel {
	public static void main(String[] args) {
		BitHotelController ctrl = new BitHotelController();
		ctrl.service();
	}
}