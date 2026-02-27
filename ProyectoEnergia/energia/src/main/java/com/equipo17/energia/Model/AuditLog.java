@Entity
@Table(name = "auditlog")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String action;

	@CreationTimestamp
	@Column( name = "action_date",nullable = false,updatable = false)
	private LocalDateTime action_date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "User_id", nullable = false)
	private User user;

	public AuditLog(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getAction_date() {
		return action_date;
	}

	public void setAction_date(LocalDateTime action_date) {
		this.action_date = action_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}