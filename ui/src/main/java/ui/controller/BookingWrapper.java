package ui.controller;

import java.sql.Timestamp;

import api.Booking;

public class BookingWrapper {

	private Booking booking;

	public BookingWrapper() {

	}

	public BookingWrapper(Booking booking) {
		this.booking = booking;
	}
	
	public void setBookingId(int id) {
		booking.setBookingId(id);
	}

	public int getBookingId() {
		return booking.getBookingId();
	}

	public Timestamp getBkFrom() {
		return booking.getBkFrom();
	}

	public void setBkFrom(Timestamp bkFrom) {
		booking.setBkFrom(bkFrom);
	}

	public Timestamp getBkTo() {
		return booking.getBkTo();
	}

	public void setBkTo(Timestamp bkTo) {
		booking.setBkTo(bkTo);
	}

	public Integer getBookingUtil() {
		return booking.getBookingUtil();
	}

	public void setBookingUtil(Integer bookingUtil) {
		booking.setBookingUtil(bookingUtil);
	}

	public String getRowFlag() {
		return booking.getRowFlag();
	}

	public void setRowFlag(String rowFlag) {
		booking.setRowFlag(rowFlag);
	}

	public String getBooking_for() {
		return booking.getBooking_for();
	}

	public void setBooking_for(String booking_for) {
		booking.setBooking_for(booking_for);
	}

	public void setcTs(Timestamp cTs) {
		booking.setcTs(cTs);
	}

	public void setcUser(String cUser) {
		booking.setcUser(cUser);
	}

	public void setmTs(Timestamp mTs) {
		booking.setmTs(mTs);
	}

	public void setmUser(String mUser) {
		booking.setmUser(mUser);
	}
	
	public String getRoomName() {
		return booking.getRoomName();
	}

	public void setRoomId(Integer room) {
		booking.setRoomId(room);
	}

	public Integer getRoomId() {
		return booking.getRoomId();
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}	