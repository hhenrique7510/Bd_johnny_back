package org.johnny.repository;

import org.johnny.models.PedidoGarcomMesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PedidoGarcomMesaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<PedidoGarcomMesa> PedidoGarcomMesaMapper = (rs, rowNum) -> {
        PedidoGarcomMesa pedidoGarcomMesa = new PedidoGarcomMesa();
        pedidoGarcomMesa.setFk_funcionario_cpf(rs.getString(1));
        pedidoGarcomMesa.setFk_mesa_id_mesa(rs.getInt(2));
        pedidoGarcomMesa.setFk_pedido_id_pedido(rs.getInt(3));
        pedidoGarcomMesa.setStatus(rs.getString(4));
        return pedidoGarcomMesa;
    };

    public void insert (PedidoGarcomMesa pedidoGarcomMesa) {
        final String sql = "insert into faz (fk_funcionario_cpf, fk_mesa_id_mesa, fk_pedido_id_pedido, status) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pedidoGarcomMesa.getFk_funcionario_cpf(), pedidoGarcomMesa.getFk_mesa_id_mesa(), pedidoGarcomMesa.getFk_pedido_id_pedido(), pedidoGarcomMesa.getStatus());
    }

    public void update (String fk_funcionario_cpf, int fk_mesa_id_mesa,int fk_pedido_id_pedido, PedidoGarcomMesa pedidoGarcomMesa){
        final String sql = "update faz " +
                " set fk_funcionario_cpf = ?, fk_mesa_id_mesa = ?, fk_pedido_id_pedido = ?, status = ?" +
                " where (fk_funcionario_cpf = ?) and (fk_mesa_id_mesa = ?) and (fk_pedido_id_pedido = ?)";
        jdbcTemplate.update(sql, pedidoGarcomMesa.getFk_funcionario_cpf(), pedidoGarcomMesa.getFk_mesa_id_mesa(), pedidoGarcomMesa.getFk_pedido_id_pedido(),
                pedidoGarcomMesa.getStatus(), fk_funcionario_cpf, fk_mesa_id_mesa,fk_pedido_id_pedido);
    }

    public void delete(String fk_funcionario_cpf, int fk_mesa_id_mesa, int fk_pedido_id_pedido){
        final String sql = "delete from faz where (fk_funcionario_cpf = ?) and (fk_mesa_id_mesa = ?) and (fk_pedido_id_pedido = ?)";
        jdbcTemplate.update(sql, fk_funcionario_cpf,fk_mesa_id_mesa, fk_pedido_id_pedido);
    }

    public List<PedidoGarcomMesa> findAll(){
        final String sql = "select * from faz";
        return jdbcTemplate.query(sql, PedidoGarcomMesaMapper);
    }

    public PedidoGarcomMesa findPedidoGarcomMesa (String fk_funcionario_cpf, int fk_mesa_id_mesa, int fk_pedido_id_pedido){
        final String sql = "select * from faz where (fk_funcionario_cpf = ?) and (fk_mesa_id_mesa = ?) and (fk_pedido_id_pedido = ?)";
        return jdbcTemplate.queryForObject(sql, PedidoGarcomMesaMapper, fk_funcionario_cpf,fk_mesa_id_mesa, fk_pedido_id_pedido);
    }
}
