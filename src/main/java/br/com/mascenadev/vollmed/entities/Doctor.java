package br.com.mascenadev.vollmed.entities;

import br.com.mascenadev.vollmed.dto.RegisterDoctorsDTO;
import br.com.mascenadev.vollmed.dto.UpdateDoctorsDTO;
import br.com.mascenadev.vollmed.entities.enums.Specialties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialties especialidade;

    @Embedded
    private Address endereco;
    private Boolean ativo;

    public Doctor(RegisterDoctorsDTO data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.endereco = new Address(data.endereco());
    }

    public void updateDoctor(UpdateDoctorsDTO data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if (data.endereco() != null) {
            this.endereco.updateAddress(data.endereco());
        }
    }

    public void delete() {
        this.ativo = false;
    }
}