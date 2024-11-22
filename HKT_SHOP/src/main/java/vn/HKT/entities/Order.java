package vn.HKT.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // _id: Primary key, auto-generated

    @Column(name = "userId", nullable = false)
    private String userId; // userId: Required, reference to User

    @Column(name = "storeId", nullable = false)
    private String storeId; // storeId: Required, reference to Store

    @Column(name = "deliveryId", nullable = false)
    private String deliveryId; // deliveryId: Required, reference to Delivery

    @Column(name = "commissionId", nullable = false)
    private String commissionId; // commissionId: Required, reference to Commission

    @Column(name = "address", nullable = false)
    private String address; // address: Required

    @Column(name = "phone", nullable = false)
    private String phone; // phone: Required

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.NOT_PROCESSED; // status: Enum with default value

    @Column(name = "isPaidBefore", nullable = false)
    @Builder.Default
    private boolean isPaidBefore = false; // isPaidBefore: Default to false

    @Column(name = "amountFromUser", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountFromUser; // amountFromUser: Required, min 0

    @Column(name = "amountFromStore", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountFromStore; // amountFromStore: Required, min 0

    @Column(name = "amountToStore", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToStore; // amountToStore: Required, min 0

    @Column(name = "amountToGD", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToGD; // amountToGD: Required, min 0

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false, updatable = false)
    @Builder.Default
    private Date createdAt = new Date(); // createdAt: Auto-generated

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    @Builder.Default
    private Date updatedAt = new Date(); // updatedAt: Auto-updated

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    // Enum for Order Status
    public enum OrderStatus {
        NOT_PROCESSED("not processed"),
        PROCESSING("processing"),
        SHIPPED("shipped"),
        DELIVERED("delivered"),
        CANCELLED("cancelled");

        private final String value;

        OrderStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
