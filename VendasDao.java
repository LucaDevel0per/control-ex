/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class VendasDao {
        private Connection con;
    public VendasDao(){
        this.con= (Connection) new ConnectionFactory().getConnection();
    }
    
    public void cadastrarVenda(Vendas obj){
    try{
         String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes)values(?,?,?,?)";
         
         PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
         stmt.setInt(1,obj.getCliente().getId());
         stmt.setString(2,obj.getData_venda());
         stmt.setDouble(3,obj.getTotal_venda());
         stmt.setString(4,obj.getObs());
         
         stmt.execute();
         stmt.close();
         
         
     
     
     }catch(Exception erro){
         JOptionPane.showMessageDialog(null,"35: "+erro);
     
     }
    
    }
      public int retornaultimavenda(){
       try{
           int idvenda=0;
           String sql="select max(id) id from tb_vendas";
           PreparedStatement ps= (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next()){
           Vendas p =new Vendas();
           p.setId(rs.getInt("id"));
           idvenda=p.getId();
           
           }
           return idvenda;
         
     
     }catch(SQLException e){
        throw new RuntimeException(e);
     
     }
    
    }
      
      
      public List<Vendas>listarvendasporperiodo(LocalDate data_inicio, LocalDate data_fim){ 
    try{
            
            List<Vendas> lista = new ArrayList();
            
            String sql="Select v.id,date_format(v.data_venda,'%d/%m/%y') as data_formatada,c.nome,v.total_venda,v.observacoes from tb_vendas as v "+
                    "inner join tb_clientes as c on (v.cliente_id = c.id) where v.data_venda between? and ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1,data_inicio.toString());
            stmt.setString(2,data_fim.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
                obj.setCliente(c);
               
                lista.add(obj);
               }
            return lista;
    } catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
               return null;
        }
}
      public List<ItemVenda>lisitensporvenda(int venda_id){ 
    try{
            
            List<ItemVenda> lista = new ArrayList();
            
            String sql="Select p.descricao,i.qtd,p.preco,i.subtotal from tb_itensvendas as i "+
                    "inner join tb_produtos as p on (i.produto_id = p.id) where i.venda_id= ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1,venda_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ItemVenda item = new ItemVenda();
                Produtos prod= new Produtos();
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                item.setProduto(prod);
               
                lista.add(item);
               }
            return lista;
    } catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
               return null;
        }
}
       public double retornaTotalVendaPorData(LocalDate data_venda){
       try{
           double totalvenda =0;
           String sql="select sum(total_venda)as total from tb_vendas where data_venda=?";
           PreparedStatement ps= (PreparedStatement) con.prepareStatement(sql);
           ps.setString(1,data_venda.toString());
           ResultSet rs=ps.executeQuery();
           
           if(rs.next()){
          
           totalvenda=rs.getDouble("total");
           
           }
           return totalvenda;
         
     
     }catch(SQLException e){
        throw new RuntimeException(e);
     
     }
    
    }
          
    
    
}
