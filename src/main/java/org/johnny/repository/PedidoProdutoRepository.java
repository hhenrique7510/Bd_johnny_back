package org.johnny.repository;

import org.johnny.models.PedidoGarcomMesa;
import org.johnny.models.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<PedidoProduto> PedidoProdutoMapper = (rs, rowNum) -> {
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setFk_produtos_id_prod(rs.getInt(1));
        pedidoProduto.setFk_pedido_id_pedido(rs.getInt(2));
        pedidoProduto.setQtd_produto(rs.getInt(3));
        return  pedidoProduto;
    };

    public void insert ( PedidoProduto pedidoProduto) {
        final String sql = "insert into tem (fk_produtos_id_prod, fk_pedido_id_pedido, qtd_produto) values (?, ?, ?)";
        jdbcTemplate.update(sql, pedidoProduto.getFk_produtos_id_prod(), pedidoProduto.getFk_pedido_id_pedido(),pedidoProduto.getQtd_produto());
    }

    public void update (int fk_produtos_id_prod, int fk_pedido_id_pedido,PedidoProduto pedidoProduto){
        final String sql = "update tem " +
                " set fk_produtos_id_prod = ?, fk_pedido_id_pedido = ?, qtd_produto = ?" +
                " where (fk_produtos_id_prod = ?) and (fk_pedido_id_pedido = ?)";
        jdbcTemplate.update(sql, pedidoProduto.getFk_produtos_id_prod(), pedidoProduto.getFk_pedido_id_pedido(),
                pedidoProduto.getQtd_produto(), fk_produtos_id_prod, fk_pedido_id_pedido);
    }

    public void delete(int fk_produtos_id_prod, int fk_pedido_id_pedido){
        final String sql = "delete from tem where (fk_produtos_id_prod = ?) and (fk_pedido_id_pedido = ?)";
        jdbcTemplate.update(sql, fk_produtos_id_prod, fk_pedido_id_pedido);
    }

    public List<PedidoProduto> findAll(){
        final String sql = "select * from tem";
        return jdbcTemplate.query(sql, PedidoProdutoMapper);
    }

    public PedidoProduto findPedidoProduto (int fk_produtos_id_prod, int fk_pedido_id_pedido){
        final String sql = "select * from tem where (fk_produtos_id_prod = ?) and (fk_pedido_id_pedido = ?)";
        return jdbcTemplate.queryForObject(sql, PedidoProdutoMapper, fk_produtos_id_prod,fk_pedido_id_pedido);
    }

    public List<PedidoProduto> findPedidoProdutosByPedidoIds(List<Integer> pedidoIds) {
        String sql = "select * from tem where fk_pedido_id_pedido in (" +
                String.join(",", pedidoIds.stream().map(String::valueOf).toArray(String[]::new)) +
                ") order by fk_produtos_id_prod";
        return jdbcTemplate.query(sql, PedidoProdutoMapper);
    }


}
