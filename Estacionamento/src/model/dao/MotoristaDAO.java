package model.dao;

import model.bean.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class MotoristaDAO {

    public void create(Motorista m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO motorista (nome, homem, rg, cpf, celular, email, senha) VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, m.getNome());
            stmt.setBoolean(2, m.isHomem());
            stmt.setString(3, m.getRg());
            stmt.setString(4, m.getCpf());
            stmt.setString(5, m.getCelular());
            stmt.setString(6, m.getEmail());
            stmt.setString(7, m.getSenha());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motorista salvo com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + e);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Motorista> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Motorista> motoristas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM motorista;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Motorista m = new Motorista();
                m.setIdMotorista(rs.getInt("idMotorista"));
                m.setNome(rs.getString("nome"));
                m.setHomem(rs.getBoolean("homem"));
                m.setRg(rs.getString("rg"));
                m.setCpf(rs.getString("cpf"));
                m.setCelular(rs.getString("celular"));
                m.setEmail(rs.getString("email"));

                motoristas.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os dados: ", e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return motoristas;
    }

    public void delete(Motorista m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM motorista WHERE idMotorista=?");
            stmt.setInt(1, m.getIdMotorista());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Motorista excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
    }

    public Motorista read(int idMotorista) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Motorista m = new Motorista();

        try {
            stmt = con.prepareStatement("SELECT * FROM motorista WHERE idMotorista=? LIMIT 1;");
            stmt.setInt(1, idMotorista);
            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                m.setIdMotorista(rs.getInt("idMotorista"));
                m.setNome(rs.getString("nome"));
                m.setHomem(rs.getBoolean("homem"));
                m.setRg(rs.getString("rg"));
                m.setCpf(rs.getString("cpf"));
                m.setCelular(rs.getString("celular"));
                m.setEmail(rs.getString("email"));
                m.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os dados: ", e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return m;
    }

    public void update(Motorista m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareCall("UPDATE motorista SET nome=?, homem=?, rg=?, cpf=?, celular=?, email=?, senha=? WHERE idMotorista=?");
            stmt.setString(1, m.getNome());
            stmt.setBoolean(2, m.isHomem());
            stmt.setString(3, m.getRg());
            stmt.setString(4, m.getCpf());
            stmt.setString(5, m.getCelular());
            stmt.setString(6, m.getEmail());
            stmt.setString(7, m.getSenha());
            stmt.setInt(8, m.getIdMotorista());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motorista Atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
