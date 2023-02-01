package my.rest.restController.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tikets")
public class Tiket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lombardName; // Название ломбарда
    private String lombardAddress; // Адрес ломбарда
    private String fio; // ФИО заемщика.
    private Integer passport; // Паспортные данные заемщика.
    private Double price; // Оценочная стоимость вещи, оставленной в залог
    private Double cash; // Размер займа
    private Double stavka; // Процентная ставка
    private Timestamp createDate; // Дата создания договора
    private Timestamp endDate; // Дата окончания договора

}