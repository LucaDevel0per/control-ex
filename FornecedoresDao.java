/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
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
public class FornecedoresDao {
     private Connection con;
    public FornecedoresDao(){
        this.con= (Connection) new ConnectionFactory().getConnection();
    }
      public void cadastarFornecedor(Fornecedores obj ){
        try {
            String sql="insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
}
      
      
      public void excluirFornecedores(Fornecedores obj){
             try {
            String sql="delete from tb_Fornecedores where id=?";
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
          
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
        
    }
      
      
       public void alterarFornecedor(Fornecedores obj){
         try {
            String sql="update tb_Fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            stmt.setInt(13, obj.getId());
            
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
        
        
    }
      
       public List<Fornecedores> listaFornecedores(){
        try {
            
            List<Fornecedores> lista = new ArrayList();
            
            String sql="Select * from tb_Fornecedores";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);
            }
            return lista;
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
    
    
    }
       
       public List<Fornecedores> buscarFornecedoresPorNome(String nome){
        try {
            
            List<Fornecedores> lista = new ArrayList();
            
            String sql="Select * from tb_Fornecedores where nome like ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);
            }
            return lista;
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
    
    
    }
       
       public Fornecedores ConsultaFornecedoresPorcnpj(String cnpj){
        try 
        {
            String sql="Select * from tb_Fornecedores where cnpj= ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            
               Fornecedores obj = new Fornecedores();
            if(rs.next()){
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                
            }
            return obj;
            
            
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "erro:"+ erro);
            return null;
        }
        
       
       
      
}
}
