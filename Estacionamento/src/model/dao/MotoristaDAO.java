package model.dao;

import model.bean.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
}
