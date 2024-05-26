package org.johnny.repository;

import org.johnny.models.Relatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelatorioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Relatorio> relatorioMapper = (rs, rowNum) -> {
        Relatorio relatorio = new Relatorio();
        relatorio.setPedidoId(rs.getInt("id_pedido"));
        relatorio.setDataHora(rs.getTimestamp("data_hora"));
        relatorio.setMesa(rs.getInt("mesa"));
        relatorio.setValorTotal(rs.getFloat("valor_total"));
        relatorio.setGarcom(rs.getString("garcom"));
        return relatorio;
    };

    public List<Relatorio> findRelatorios() {
        final String sql = "SELECT p.id_pedido, p.data_hora, faz.fk_mesa_id_mesa AS mesa, " +
                "SUM(t.qtd_produto * prod.valor) AS valor_total, " +
                "f.nome AS garcom " +
                "FROM pedido p " +
                "JOIN faz ON p.id_pedido = faz.fk_pedido_id_pedido " +
                "JOIN tem t ON p.id_pedido = t.fk_pedido_id_pedido " +
                "JOIN produtos prod ON t.fk_produtos_id_prod = prod.id_prod " +
                "JOIN funcionario f ON faz.fk_funcionario_cpf = f.cpf " +
                "GROUP BY p.id_pedido, p.data_hora, faz.fk_mesa_id_mesa, f.nome";
        return jdbcTemplate.query(sql, relatorioMapper);
    }
}
