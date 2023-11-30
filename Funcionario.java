/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

/**
 *
 * @author rodri
 */
public class Funcionario extends Clientes{
    private String senha;
    private String cargo;
    private String nivel_Acesso;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_Acesso() {
        return nivel_Acesso;
    }

    public void setNivel_Acesso(String nivel_Acesso) {
        this.nivel_Acesso = nivel_Acesso;
    }
    
    
}
