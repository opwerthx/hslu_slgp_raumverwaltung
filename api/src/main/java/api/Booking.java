package api;
import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Timestamp;

public class Booking implements Serializable, Remote {

    private static final long serialVersionUID = 1190476516911661470L;

	private Integer bookingId;
	private Timestamp bkFrom;
	private Timestamp bkTo;
	private Integer bookingUtil;
	private Timestamp cTs;
	private String cUser;
	private Timestamp mTs;
	private String mUser;
	private String rowFlag;
	private String booking_for;
	private Integer roomId;
	private String roomName;

    public Booking(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Booking( 
    		 Integer bookingId
    		,Integer roomId
    		,Integer bookingUtil
    		,String rowFlag
    		,String booking_for
    		,String cUser
    		,String mUser
    		,Timestamp bkFrom
    		,Timestamp bkTo
    		,Timestamp cTs
    		,Timestamp mTs
    		) 
    {
    	this.bookingId = bookingId;
    	this.bkFrom = bkFrom;
    	this.bkTo = bkTo;
    	this.bookingUtil = bookingUtil;
    	this.cTs = cTs;
    	this.cUser = cUser;
    	this.mTs = mTs;
    	this.mUser = mUser;
    	this.rowFlag = rowFlag;
    	this.booking_for = booking_for;
    	this.roomId = roomId;
    }
    
    public Booking(
   		 Integer bookingId
   		,Integer roomId
   		,Integer bookingUtil
   		,String rowFlag
   		,String booking_for
   		,String cUser
   		,String mUser
   		,Timestamp bkFrom
   		,Timestamp bkTo
   		,Timestamp cTs
   		,Timestamp mTs
   		,String roomName
    		) {
       	this.bookingId = bookingId;
    	this.bkFrom = bkFrom;
    	this.bkTo = bkTo;
    	this.bookingUtil = bookingUtil;
    	this.cTs = cTs;
    	this.cUser = cUser;
    	this.mTs = mTs;
    	this.mUser = mUser;
    	this.rowFlag = rowFlag;
    	this.booking_for = booking_for;
    	this.roomId = roomId;
    	this.roomName = roomName;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Timestamp getBkFrom() {
		return bkFrom;
	}

	public void setBkFrom(Timestamp bkFrom) {
		this.bkFrom = bkFrom;
	}

	public Timestamp getBkTo() {
		return bkTo;
	}

	public void setBkTo(Timestamp bkTo) {
		this.bkTo = bkTo;
	}

	public Integer getBookingUtil() {
		return bookingUtil;
	}

	public void setBookingUtil(Integer bookingUtil) {
		this.bookingUtil = bookingUtil;
	}

	public String getRowFlag() {
		return rowFlag;
	}

	public void setRowFlag(String rowFlag) {
		this.rowFlag = rowFlag;
	}

	public String getBooking_for() {
		return booking_for;
	}

	public void setBooking_for(String booking_for) {
		this.booking_for = booking_for;
	}

	public void setcTs(Timestamp cTs) {
		this.cTs = cTs;
	}

	public void setcUser(String cUser) {
		this.cUser = cUser;
	}

	public void setmTs(Timestamp mTs) {
		this.mTs = mTs;
	}

	public void setmUser(String mUser) {
		this.mUser = mUser;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	public Integer getRoomId() {
		return roomId;
	}

    @Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bkFrom=" + bkFrom + ", bkTo=" + bkTo + ", bookingUtil="
				+ bookingUtil + ", cTs=" + cTs + ", cUser=" + cUser + ", mTs=" + mTs + ", mUser=" + mUser + ", rowFlag="
				+ rowFlag + ", booking_for=" + booking_for + ", room=" + roomId + "]";
	}

	public String getmUser() {
		return this.mUser;
	}

	public String getcUser() {
		// TODO Auto-generated method stub
		return this.cUser;
	}

	public Object getUser() {
		return this.getUser();
	}

	public String getRoomName() {
		return this.roomName;
	}
	
	public String setRoomName() {
		return this.roomName;
	}
}