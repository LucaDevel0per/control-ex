/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class ProdutosDao {
    private Connection con;
    public ProdutosDao(){
        this.con= (Connection) new ConnectionFactory().getConnection();
    }
    
    public void cadastrar(Produtos obj){
     try{
         String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id)values(?,?,?,?)";
         
         PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
         stmt.setString(1,obj.getDescricao());
         stmt.setDouble(2,obj.getPreco());
         stmt.setInt(3,obj.getQtd_estoque());
         stmt.setInt(4,obj.getFornecedor().getId());
         
         stmt.execute();
         stmt.close();
         
         JOptionPane.showMessageDialog(null,"Produto Cadastrado com sucesso");
     
     
     }catch(Exception erro){
         JOptionPane.showMessageDialog(null,"Erro: "+erro);
     
     }
   };
     


    




        

public List<Produtos>listarProdutos(){ 
    try{
            
            List<Produtos> lista = new ArrayList();
            
            String sql="Select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "+
                    "inner join tb_fornecedores as f on (p.for_id = f.id)";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(f);
                lista.add(obj);
               }
            return lista;
    } catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
               return null;
        }
}

    public void alterar(Produtos obj){
     try{
         String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";
         
         PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
         stmt.setString(1,obj.getDescricao());
         stmt.setDouble(2,obj.getPreco());
         stmt.setInt(3,obj.getQtd_estoque());
         stmt.setInt(4,obj.getFornecedor().getId());
         stmt.setInt(5,obj.getId());
         
         
         stmt.execute();
         stmt.close();
         
         JOptionPane.showMessageDialog(null,"Produto alterado com sucesso");
     
     
     }catch(Exception erro){
         JOptionPane.showMessageDialog(null,"Erro: "+erro);
     
     }
   };
       public void excluir(Produtos obj){
     try{
         String sql = "delete tb_produtos where id=?";
         
         PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);

         stmt.setInt(1,obj.getId());
         
         
         stmt.execute();
         stmt.close();
         
         JOptionPane.showMessageDialog(null,"Produto excluido com sucesso");
     
     
     }catch(Exception erro){
         JOptionPane.showMessageDialog(null,"Erro: "+erro);
     
     }
   };
                       
     public List<Produtos> buscarProdutosPorNome(String nome){
        try {
            
            List<Produtos> lista = new ArrayList();
             String sql="Select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "+
                    "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(f);
                lista.add(obj);
            }
            return lista;
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
    
    
    }
     public Produtos consultaProdutosPorNome(String nome){
        try {
            
             String sql="Select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "+
                    "inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
               Fornecedores f = new Fornecedores();
            if(rs.next()){
              
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString(("f.nome")));
                
                obj.setFornecedor(f);
            }
            return obj;
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
    
    
    }
      public Produtos consultaProdutosPorcodigo(int id){
        try {
            
             String sql="Select * from tb_produtos where id = ?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            if(rs.next()){
              
                
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                
                
            }
            return obj;
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
    
    
    };
        public void baixarEstoque(int id,int qtd_nova){
        try {
            
             String sql="update tb_produtos set qtd_estoque = ? where id=?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            }catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
        }
        
     }
        public int retornaEstoqueAtual(int id){
        try {
            int qtd_estoque=0;
            
             String sql="select qtd_estoque from tb_produtos where id = ?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, id);
           ResultSet rs=stmt.executeQuery();
           if(rs.next()){
            qtd_estoque=(rs.getInt("qtd_estoque"));
           
           }
           return qtd_estoque;
           
            }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
     }
    public void adicionarEstoque(int id,int qtd_nova){
        try {
            
             String sql="update tb_produtos set qtd_estoque = ? where id=?";
            

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            }catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "socorro:"+ erro);
        }
        
     }
    
    
   



}
