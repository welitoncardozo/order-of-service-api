package br.com.cardozo.orderofserviceapi.models;

import br.com.cardozo.orderofserviceapi.validations.ClientId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(schema = "public", name = "client")
@Data
@EqualsAndHashCode
public class Client {
    @NotNull(groups = ClientId.class)
    @Id
    @SequenceGenerator(sequenceName = "public.client_clie_id_seq", name = "public.client_clie_id_seq", schema = "public", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "public.client_clie_id_seq")
    @Column(name = "clie_id")
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(name = "clie_name")
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "clie_email")
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "clie_phone")
    private String phone;
}
