package backend.assinatura.entidades;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_assinatura")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(length = 100)
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(length = 11, unique = true)
    private String celular;

    @NotNull
    @Past
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @NotBlank
    @Column(length = 15, unique = true)
    @Size(min = 3, max = 15)
    private String login;

    @NotBlank
    @Column(length = 255)
    private String senha;

    @NotNull
    @Past
    @Column(name = "status")
    private String status;

    public Assinatura() {
        super();
    }

    public Assinatura(Long id, String nome, String email, String celular, Date dataNascimento, String login,
            String senha, String status) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
        this.status= status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public @NotNull @Past String getStatus() {
        return status;
    }

    public void setStatus(@NotNull @Past String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", celular=" + celular
                + ", dataNascimento=" + dataNascimento + ", login=" + login + ", senha=" + senha + "Assinante" + status
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((celular == null) ? 0 : celular.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Assinatura other = (Assinatura) obj;
        if (celular == null) {
            if (other.celular != null)
                return false;
        } else if (!celular.equals(other.celular))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

}
