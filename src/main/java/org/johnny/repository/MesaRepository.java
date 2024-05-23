package org.johnny.repository;

import org.johnny.models.Dependente;
import org.johnny.models.Mesa;
import org.johnny.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class MesaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Mesa> MesaMapper = (rs, rowNum) -> {
        Mesa mesa = new Mesa();
        mesa.setId_mesa(rs.getInt(1));

        return mesa;
    };

    public List<Mesa> findAll(){
        final String sql = "select * from mesa";

        return jdbcTemplate.query(sql, MesaMapper);
    }

    public void insert(Mesa mesa){
        final String sql = "INSERT INTO mesa () VALUES ()";
        jdbcTemplate.update(sql);
    }

    public void delete(int id_mesa){
        final String sql = "delete from mesa where id_mesa = ?";

        jdbcTemplate.update(sql, id_mesa);
    }

    public void update(int id_mesa, Mesa mesa){
        final String sql = "update mesa set id_mesa = ? where id_mesa = ?";

        jdbcTemplate.update(sql,
               mesa.getId_mesa(),
                id_mesa);
    }
}
