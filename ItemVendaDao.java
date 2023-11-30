/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVenda;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class ItemVendaDao {
    private Connection con;
    public ItemVendaDao(){
        this.con= (Connection) new ConnectionFactory().getConnection();
    }
    
    public void cadastraItem(ItemVenda obj){
     try{
         String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)values(?,?,?,?)";
         
         PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
         stmt.setInt(1,obj.getVendas().getId());
         stmt.setInt(2,obj.getProduto().getId());
         stmt.setInt(3,obj.getQtd());
         stmt.setDouble(4,obj.getSubtotal());
         
         stmt.execute();
         stmt.close();
         
         
     
     
     }catch(Exception erro){
         JOptionPane.showMessageDialog(null,"1: "+erro);
     
     }
    
    
    }
    
    
    
    
    
}
