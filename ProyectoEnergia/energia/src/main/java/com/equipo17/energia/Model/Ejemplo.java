/* import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
public class Ejemplo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer cantidad;

	// (opcional) guardas el precio en el momento del pedido
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal precioUnitario;

	// FK: detalles_pedido.pedido_id -> pedidos.id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	// FK: detalles_pedido.producto_id -> productos.id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;


} */