package pe.com.cibertec.lp2_carrito_compra.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pedidoId;
	
	@Column(name = "fecha_compra")
	private LocalDate fechaCompra;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario", nullable = false)
	private UsuarioEntity usuarioEntity;
	
	@OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
	private List<DetallePedidoEntity>detallePedido;
	
	
	
	
	
	
	
	
	
	
	
}
