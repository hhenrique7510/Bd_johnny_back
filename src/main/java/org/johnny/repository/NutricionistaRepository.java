package org.johnny.repository;

import org.johnny.models.Nutricionista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class NutricionistaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Nutricionista> rowMapper = new RowMapper<Nutricionista>() {
        public Nutricionista mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Nutricionista(rs.getString("fk_funcionario_cpf"));
        }
    };

    public Nutricionista saveNutricionista(Nutricionista nutricionista) {
        String sql = "INSERT INTO nutricionista (fk_funcionario_cpf) VALUES (?)";
        jdbcTemplate.update(sql, nutricionista.getFkFuncionarioCpf());
        return nutricionista;
    }

    public Nutricionista updateNutricionista(String oldCpf, Nutricionista nutricionista) {
        String sql = "UPDATE nutricionista SET fk_funcionario_cpf = ? WHERE fk_funcionario_cpf = ?";
        jdbcTemplate.update(sql, nutricionista.getFkFuncionarioCpf(), oldCpf);
        return nutricionista;
    }

    public List<Nutricionista> listNutricionista() {
        String sql = "SELECT * FROM nutricionista";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Nutricionista> findByCpfNutricionista(String cpf) {
        String sql = "SELECT * FROM nutricionista WHERE fk_funcionario_cpf = ?";
        List<Nutricionista> nutricionistas = jdbcTemplate.query(sql, rowMapper, cpf);
        return nutricionistas.isEmpty() ? Optional.empty() : Optional.of(nutricionistas.get(0));
    }

    public boolean deleteNutricionistaByCpf(String cpf) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM nutricionista WHERE fk_funcionario_cpf = ?", cpf);
        return rowsAffected > 0;
    }
}

