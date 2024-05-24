package org.johnny.repository;

import org.johnny.models.Mesa;
import org.johnny.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PedidoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Pedido> PedidoMapper = (rs, rowNum) -> {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(rs.getInt(1));
        Timestamp timestamp = rs.getTimestamp(2);
        if (timestamp != null) {
            pedido.setData_hora(timestamp.toLocalDateTime());
        }
        return pedido;
    };

    public int insert() {
        final String sql = "INSERT INTO pedido () VALUES ()";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id_pedido"});
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<Pedido> findAll(){
        final String sql = "select * from pedido";

        return jdbcTemplate.query(sql, PedidoMapper);
    }

    public void delete(int id_pedido){
        final String sql = "delete from pedido where id_pedido = ?";

        jdbcTemplate.update(sql, id_pedido);
    }

    public Pedido findPedido(int id_pedido){
        final String sql = "select * from pedido where id_pedido = ?";

        return jdbcTemplate.queryForObject(sql, PedidoMapper, id_pedido);
    }

    public void update(int id_pedido, Pedido pedido){
        final String sql = "update pedido set id_pedido = ?, data_hora = ? where id_pedido = ?";

        jdbcTemplate.update(sql,
                pedido.getId_pedido(),
                Timestamp.valueOf(pedido.getData_hora()),
                id_pedido);
    }
}
