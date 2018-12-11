package persister;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the bookings database table.
 * 
 */
@Entity
@Table(name="bookings")
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="booking_id")
	private Integer bookingId;

	@Column(name="bk_from")
	private Timestamp bkFrom;

	@Column(name="bk_to")
	private Timestamp bkTo;

	@Column(name="booking_util")
	private Integer bookingUtil;

	@Column(name="c_ts")
	private Timestamp cTs;

	@Column(name="c_user")
	private String cUser;

	@Column(name="m_ts")
	private Timestamp mTs;

	@Column(name="m_user")
	private String mUser;

	@Column(name="row_flag")
	private String rowFlag;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="booking_for", referencedColumnName="username")
	private User user;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="id_room")
	private Room room;

	public Booking() {
	}

	public Integer getBookingId() {
		return this.bookingId;
	}

//	public void setBookingId(Integer bookingId) {
//		this.bookingId = bookingId;
//	}

	public Timestamp getBkFrom() {
		return this.bkFrom;
	}

	public void setBkFrom(Timestamp bkFrom) {
		this.bkFrom = bkFrom;
	}

	public Timestamp getBkTo() {
		return this.bkTo;
	}

	public void setBkTo(Timestamp bkTo) {
		this.bkTo = bkTo;
	}

	public Integer getBookingUtil() {
		return this.bookingUtil;
	}

	public void setBookingUtil(Integer bookingUtil) {
		this.bookingUtil = bookingUtil;
	}

	public Timestamp getCTs() {
		return this.cTs;
	}

	public void setCTs(Timestamp cTs) {
		this.cTs = cTs;
	}

	public String getCUser() {
		return this.cUser;
	}

	public void setCUser(String cUser) {
		this.cUser = cUser;
	}

	public Timestamp getMTs() {
		return this.mTs;
	}

	public void setMTs(Timestamp mTs) {
		this.mTs = mTs;
	}

	public String getMUser() {
		return this.mUser;
	}

	public void setMUser(String mUser) {
		this.mUser = mUser;
	}
	
	public void setRoomId(Integer pRoom) {
		this.setRoomId(pRoom);
	}

	public String getRowFlag() {
		return this.rowFlag;
	}

	public void setRowFlag(String rowFlag) {
		this.rowFlag = rowFlag;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser(String puser) {
		return user.getUserByUsername(puser);
	}

	public Room getRoom() {
		return this.room;
	}
	
	public Room getRoomName() {
		return this.getRoomName();
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	

}