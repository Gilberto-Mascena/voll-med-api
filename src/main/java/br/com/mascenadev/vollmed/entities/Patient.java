package br.com.mascenadev.vollmed.entities;

import br.com.mascenadev.vollmed.dto.RegisterPatientDTO;
import br.com.mascenadev.vollmed.dto.UpdatePatientDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Address endereco;
    private Boolean ativo;

    public Patient(RegisterPatientDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Address(dados.endereco());
    }

    public void updatePatient(@Valid UpdatePatientDTO dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.updateAddress(dados.endereco());
        }
    }

    public void deletePatien() {
        this.ativo = false;
    }
}