/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.WebServiceCep;
import br.com.projeto.view.FrmMenu;
import br.com.projeto.view.frmLogin;
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
public class FuncionarioDao {
    
      private Connection con;
    public FuncionarioDao(){
        this.con= (Connection) new ConnectionFactory().getConnection();
    
    }
    
     public void cadastarFuncionario(Funcionario obj ){
        try {
            String sql="insert into tb_funcionarios(nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_Acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
        
        
        
    }
       public void alterarFuncionario(Funcionario obj){
         try {
            String sql="update tb_funcionarios set nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_Acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            
            stmt.setInt(17, obj.getId());
            
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
        
        
    }
    public void excluirFuncionario(Funcionario obj){
             try {
            String sql="delete from tb_funcionarios where id=?";
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
          
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"erro");
            
            
        }
        
    }
    
    
    
    
    public List<Funcionario> listaFuncionario(){
        try {
            
            List<Funcionario> lista = new ArrayList();
            
            String sql="Select * from tb_funcionarios";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_Acesso(rs.getString("nivel_acesso"));
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
    public List<Funcionario> buscarFuncionarioPorNome(String nome){
        try {
            
            List<Funcionario> lista = new ArrayList();
            
            String sql="Select * from tb_funcionarios where nome like ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_Acesso(rs.getString("nivel_acesso"));
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
    
    public Funcionario ConsultaFuncionarioPorCpf(String Cpf){
        try {
            String sql="Select * from tb_funcionarios where cpf = ?";
            
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, Cpf);
            ResultSet rs = stmt.executeQuery();
            
               Funcionario obj = new Funcionario();
            if(rs.next()){
               obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_Acesso(rs.getString("nivel_acesso"));
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
    
	
	  public Funcionario buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Funcionario obj = new Funcionario();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    
  public void Logar(String email,String senha){
      try {
          String sql="select * from tb_funcionarios where email= ? and senha = ?";
          
           PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
             ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
             if(rs.getString("nivel_acesso").equals("Administrador")){
                 
             JOptionPane.showMessageDialog(null,"Seja bem vindo");
             FrmMenu tela= new FrmMenu();
             tela.usuariologado=rs.getString("nome");
             tela.setVisible(true);   
             
            }else if(rs.getString("nivel_acesso").equals("Usuario")){
            JOptionPane.showMessageDialog(null,"Seja bem vindo");
             FrmMenu tela= new FrmMenu();
             tela.usuariologado=rs.getString("nome");
             tela.menu_posicao.setEnabled(false);
             tela.menu_controle.setEnabled(false);
             
             tela.setVisible(true);  
            
            }
           
            
            }
      else{
               JOptionPane.showMessageDialog(null,"Dados Incorretos");
               new frmLogin().setVisible(true);
            }
          
      } catch (Exception erro) {
          JOptionPane.showMessageDialog(null,"Erro"+erro);
          

      }
  }
     
}
