/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecmogidascruzes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josias Wattrelos
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id")
    , @NamedQuery(name = "Categoria.findByNome", query = "SELECT c FROM Categoria c WHERE c.nome = :nome")
    , @NamedQuery(name = "Categoria.findByDataCadastro", query = "SELECT c FROM Categoria c WHERE c.dataCadastro = :dataCadastro")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Basic(optional = false) 
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)    
    @Column(name = "nome")
    private String nome;       
    
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @ManyToMany(mappedBy="categorias", cascade=CascadeType.REFRESH)
    List<Livro> livros = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }
    
        public Categoria(String nome) {
        this.nome = nome;        
    }
    
    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;        
    }

    public Categoria(Integer id, String nome, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return nome;
    }

    public void setCategoria(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", categoria=" + nome + ", dataCadastro=" + dataCadastro + '}';
    }
}
