package org.johnny.repository;

import org.johnny.models.Garcom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GarcomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Garcom> GarcomMapper = (rs, rowNum) -> {
        Garcom garcom = new Garcom();
        garcom.setFk_funcionario_cpf(rs.getString(1));
        garcom.setFk_gerente_cpf(rs.getString(2));
        garcom.setPontos(rs.getInt(3));
        return garcom;
    };

    public void insert(Garcom garcom){
        final String sql = "insert into garcom (fk_funcionario_cpf, fk_gerente_cpf, pontos) values (?, ?, ?)";

        jdbcTemplate.update(sql,
                garcom.getFk_funcionario_cpf(),
                garcom.getFk_gerente_cpf(),
                garcom.getPontos());
    }

    public List<Garcom> findAll(){
        final String sql = "select * from garcom";
        return jdbcTemplate.query(sql, GarcomMapper);
    }

    public void update(String fk_funcionario_cpf, Garcom garcom){
        final String sql = "update garcom " +
                "set fk_funcionario_cpf = ?, fk_gerente_cpf = ?, pontos = ? " +
                "where fk_funcionario_cpf = ?";

        jdbcTemplate.update(sql,
                garcom.getFk_funcionario_cpf(),
                garcom.getFk_gerente_cpf(),
                garcom.getPontos(),
                fk_funcionario_cpf);
    }

    public void delete(String fk_funcionario_cpf){
        final String sql = "delete from garcom where fk_funcionario_cpf = ?";
        jdbcTemplate.update(sql, fk_funcionario_cpf);
    }

    public Garcom findGarcom(String fk_funcionario_cpf){
        final String sql = "select * from garcom where fk_funcionario_cpf = ?";
        return jdbcTemplate.queryForObject(sql, GarcomMapper, fk_funcionario_cpf);
    }

     public void updateGerente(String gerenteCpf) {
        // Definir todos os garçons para apontar para o novo gerente
        final String sqlUpdateGerente = "UPDATE garcom SET fk_gerente_cpf = ? WHERE fk_funcionario_cpf != ?";
        jdbcTemplate.update(sqlUpdateGerente, gerenteCpf, gerenteCpf);

        // Limpar o campo gerente do novo gerente
        final String sqlClearGerente = "UPDATE garcom SET fk_gerente_cpf = NULL WHERE fk_funcionario_cpf = ?";
        jdbcTemplate.update(sqlClearGerente, gerenteCpf);
    }

    public Garcom findGerente() {
        final String sql = "SELECT * FROM garcom WHERE fk_gerente_cpf IS NULL LIMIT 1";
        List<Garcom> result = jdbcTemplate.query(sql, GarcomMapper);
        return result.isEmpty() ? null : result.get(0);
    }

    public Garcom findGarcomComMaisPontos() {
        final String sql = "SELECT * FROM garcom ORDER BY pontos DESC LIMIT 1";
        List<Garcom> result = jdbcTemplate.query(sql, GarcomMapper);
        return result.isEmpty() ? null : result.get(0);
    }


}
