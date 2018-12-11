package persister;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity(name="users")
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM users u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="user_id")
	private Integer userId;

	@Column(name="username")
	private String username;
	
	@Column(name="c_ts")
	private Timestamp cTs;

	@Column(name="c_user")
	private String cUser;

	@Column(name="create_time")
	private Timestamp createTime;

	private String email;

	private String firstname;

	@Column(name="m_ts")
	private Timestamp mTs;

	@Column(name="m_user")
	private String mUser;

	private String name;

	private String password;

	private String role;

	@Column(name="row_flag")
	private String rowFlag;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Booking> bookings;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCTs() {
		return this.cTs;
	}

	public void setCTs(Timestamp cTs) {
		this.cTs = cTs;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCUser() {
		return this.cUser;
	}
	
	public void setCUser(String cUser) {
		this.cUser = cUser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRowFlag() {
		return this.rowFlag;
	}
	
	public void setRowFlag(String rowFlag) {
		this.rowFlag = rowFlag;
	}

	public User getUserByUsername(String pUsername) {
		return this; 
	}
	
	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setUser(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setUser(null);

		return booking;
	}

}