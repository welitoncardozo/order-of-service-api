package br.com.cardozo.orderofserviceapi.models;

import br.com.cardozo.orderofserviceapi.enuns.StatusOrderOfService;
import br.com.cardozo.orderofserviceapi.validations.ClientId;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(schema = "public", name = "order_service")
@Data
@EqualsAndHashCode
public class OrderService {
    @Id
    @SequenceGenerator(sequenceName = "public.order_service_orse_id_seq", name = "public.order_service_orse_id_seq", schema = "public", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "public.order_service_orse_id_seq")
    @Column(name = "orse_id")
    private Long id;

    @NotNull
    @Valid
    @ConvertGroup(to = ClientId.class)
    @ManyToOne
    @JoinColumn(name = "clie_id")
    private Client client;

    @NotBlank
    @Column(name = "orse_description")
    private String description;

    @NotNull
    @Column(name = "orse_price")
    private Double price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    @Column(name = "orse_status")
    private StatusOrderOfService status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "orse_start_date")
    private LocalDateTime startDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "orse_end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "orderService")
    @JsonManagedReference
    private List<Comment> comments;
}
