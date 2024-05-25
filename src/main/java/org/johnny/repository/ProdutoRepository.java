package org.johnny.repository;

import org.johnny.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Produto newProduto(Produto newProduto) {
        String sql = "INSERT INTO produtos (nome, valor, produtos_tipo) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newProduto.getNome());
            ps.setFloat(2, newProduto.getValor());
            ps.setString(3, newProduto.getProdutos_tipo());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();
        newProduto.setId_prod(newId);

        return newProduto;
    }

    public List<Produto> getAllProdutos() {
        String sql = "SELECT * FROM produtos";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Produto.class));
    }

    public Produto getProdutoById(int id_prod) {
        String sql = "SELECT * FROM produtos WHERE id_prod = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_prod}, new BeanPropertyRowMapper<>(Produto.class));
    }

    public Produto updateProduto(Produto newProduto, int id_prod) {
        String sql = "UPDATE produtos SET nome = ?, valor = ?, produtos_tipo = ? WHERE id_prod = ?";
        jdbcTemplate.update(sql, newProduto.getNome(), newProduto.getValor(), newProduto.getProdutos_tipo(), id_prod);
        newProduto.setId_prod(id_prod);
        return newProduto;
    }

    public void deleteProduto(int id_prod) {
        String sql = "DELETE FROM produtos WHERE id_prod = ?";
        jdbcTemplate.update(sql, id_prod);
    }

    public List<Produto> findProdutosByIds(List<Integer> produtoIds) {
        String sql = "SELECT * FROM produtos WHERE id_prod IN (" +
                String.join(",", produtoIds.stream().map(String::valueOf).toArray(String[]::new)) + ")";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Produto.class));
    }
    
}