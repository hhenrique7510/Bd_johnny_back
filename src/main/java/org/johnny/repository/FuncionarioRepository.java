package org.johnny.repository;

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
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Funcionario> rowMapper = new RowMapper<Funcionario>() {
        public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcionario funcionario = new Funcionario(
                    rs.getString("cpf"),
                    rs.getFloat("salario"),
                    rs.getString("celular"),
                    rs.getString("nome"),
                    rs.getString("senha"),
                    rs.getString("email_principal"),
                    rs.getString("email_secundario"),
                    rs.getString("rua"),
                    rs.getInt("numero"),
                    rs.getInt("cep"),
                    rs.getString("bairro")
            );
            return funcionario;
        }
    };

    public Funcionario saveFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf, salario, celular, nome, senha, email_principal, email_secundario, rua, numero, cep, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, funcionario.getCpf(), funcionario.getSalario(), funcionario.getCelular(), funcionario.getNome(), funcionario.getSenha(), funcionario.getEmailPrincipal(), funcionario.getEmailSecundario(), funcionario.getRua(), funcionario.getNumero(), funcionario.getCep(), funcionario.getBairro());
        return funcionario;
    }

    public Funcionario updateFuncionario(String oldCpf, Funcionario funcionario) {
        String sql = "UPDATE funcionario SET cpf = ?, salario = ?, celular = ?, nome = ?, senha = ?, email_principal = ?, email_secundario = ?, rua = ?, numero = ?, cep = ?, bairro = ? WHERE cpf = ?";
        jdbcTemplate.update(sql,
                funcionario.getCpf(),
                funcionario.getSalario(),
                funcionario.getCelular(),
                funcionario.getNome(),
                funcionario.getSenha(),
                funcionario.getEmailPrincipal(),
                funcionario.getEmailSecundario(),
                funcionario.getRua(),
                funcionario.getNumero(),
                funcionario.getCep(),
                funcionario.getBairro(),
                oldCpf);
        return funcionario;
    }

    public List<Funcionario> listFuncionario() {
        String sql = "SELECT * FROM funcionario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Funcionario> findByCpfFuncionario(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        List<Funcionario> funcionarios = jdbcTemplate.query(sql, rowMapper, cpf);
        return funcionarios.isEmpty() ? Optional.empty() : Optional.of(funcionarios.get(0));
    }

    public boolean deleteFuncionarioByCpf(String cpf) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM funcionario WHERE cpf = ?", cpf);
        return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada (funcionário excluído)
    }



}



