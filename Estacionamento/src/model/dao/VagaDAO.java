package model.dao;

import model.bean.Vaga;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class VagaDAO {

    public void create(Vaga v) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO vaga (numero, rua, obliqua) VALUES(?,?,?)");
            stmt.setInt(1, v.getNumero());
            stmt.setString(2, v.getRua());
            stmt.setBoolean(3, v.getObliqua());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vaga salva com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar" + e);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Vaga> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vaga> vagas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM vaga;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vaga v = new Vaga();
                v.setIdVaga(rs.getInt("idVaga"));
                v.setNumero(rs.getInt("numero"));
                v.setRua(rs.getString("rua"));
                v.setObliqua(rs.getBoolean("obliqua"));
                vagas.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os dados: ", e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vagas;
    }

    public void delete(Vaga v) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM vaga WHERE idVaga=?");
            stmt.setInt(1, v.getIdVaga());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Vaga excluída com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
    }
}
