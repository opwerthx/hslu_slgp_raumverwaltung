package persister;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rooms database table.
 * 
 */
@Entity
@Table(name="rooms")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="room_id")
	private Integer roomId;

	@Column(name="room_equipment")
	private String roomEquipment;

	@Column(name="room_location")
	private String roomLocation;

	@Column(name="room_max_util")
	private Integer roomMaxUtil;

	@Column(name="room_min_util")
	private Integer roomMinUtil;

	@Column(name="room_name")
	private String roomName;

	@Column(name="row_flag")
	private String rowFlag;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="room", fetch=FetchType.EAGER)
	private List<Booking> bookings;

	public Room() {
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomEquipment() {
		return this.roomEquipment;
	}

	public void setRoomEquipment(String roomEquipment) {
		this.roomEquipment = roomEquipment;
	}

	public String getRoomLocation() {
		return this.roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	
	public Integer getRoomMaxUtil() {
		return this.roomMaxUtil;
	}

	public void setRoomMaxUtil(Integer roomMaxUtil) {
		this.roomMaxUtil = roomMaxUtil;
	}

	public Integer getRoomMinUtil() {
		return this.roomMinUtil;
	}

	public void setRoomMinUtil(Integer roomMinUtil) {
		this.roomMinUtil = roomMinUtil;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRowFlag() {
		return this.rowFlag;
	}

	public void setRowFlag(String rowFlag) {
		this.rowFlag = rowFlag;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setRoom(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setRoom(null);

		return booking;
	}

}