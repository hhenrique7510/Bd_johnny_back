package org.johnny.repository;

import org.johnny.models.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

}
