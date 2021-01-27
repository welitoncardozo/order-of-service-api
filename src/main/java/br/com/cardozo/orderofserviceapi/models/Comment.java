package br.com.cardozo.orderofserviceapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(schema = "public", name = "comment")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @SequenceGenerator(sequenceName = "public.comment_comm_id_seq", name = "public.comment_comm_id_seq", schema = "public", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "public.comment_comm_id_seq")
    @Column(name = "comm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orse_id")
    @JsonBackReference
    private OrderService orderService;

    @Column(name = "comm_description")
    private String description;

    @Column(name = "comm_send_date")
    private LocalDateTime sendDate;
}
