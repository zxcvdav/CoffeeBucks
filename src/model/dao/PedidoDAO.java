/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Pedido;

/**
 *
 * @author Samuelson
 */
public class PedidoDAO {

    public void create(Pedido p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pedido (id_usuario,descricao,quantidade,preco)VALUES(?,?,?,?)");
            stmt.setLong(1, p.getId_usuario());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Pedido> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setPreco(rs.getDouble("preco"));
                pedido.setData(rs.getString("data"));
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pedidos;

    }
    public List<Pedido> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

               pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setPreco(rs.getDouble("preco"));
                pedido.setData(rs.getString("data"));
                pedidos.add(pedido);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pedidos;

    }

    public void update(Pedido p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedido SET descricao = ? ,quantidade = ?,preco = ? WHERE id_pedido = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setLong(4, p.getId_pedido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Pedido p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pedido WHERE id_pedido = ?");
            stmt.setLong(1, p.getId_pedido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}