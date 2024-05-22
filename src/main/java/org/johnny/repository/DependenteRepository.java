package org.johnny.repository;

import org.johnny.models.Dependente;
import org.johnny.models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DependenteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Dependente> rowMapper = new RowMapper<Dependente>() {
        public Dependente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dependente dependente = new Dependente(
                    rs.getString("nome"),
                    rs.getString("cpf_dependente"),
                    rs.getString("fk_funcionario_cpf")

            );
            return dependente;
        }
    };

    public Dependente saveDependente(Dependente dependente) {
        String sql = "INSERT INTO dependente (nome,cpf_dependente, fk_funcionario_cpf) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,dependente.getNome(),dependente.getCpf_dependente(),dependente.getFk_funcionario_cpf());
        return dependente;
    }

    public List<Dependente> findByCpfFuncionario(String fk_funcionario_cpf) {
        String sql = "SELECT * FROM dependente WHERE fk_funcionario_cpf = ?";
        return jdbcTemplate.query(sql, rowMapper, fk_funcionario_cpf);
    }

    public List<Dependente> listDependente() {
        String sql = "SELECT * FROM dependente";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public boolean deleteDependeteByCpf(String cpf_dependente) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM dependente WHERE cpf_dependente= ?", cpf_dependente);
        return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada (funcionário excluído)
    }




}
