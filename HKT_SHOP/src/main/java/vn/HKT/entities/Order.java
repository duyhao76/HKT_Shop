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
    private Long id; // _id: Primary key, auto-generated

    @Column(nullable = false)
    private String userId; // userId: Required, reference to User

    @Column(nullable = false)
    private String storeId; // storeId: Required, reference to Store

    @Column(nullable = false)
    private String deliveryId; // deliveryId: Required, reference to Delivery

    @Column(nullable = false)
    private String commissionId; // commissionId: Required, reference to Commission

    @Column(nullable = false)
    private String address; // address: Required

    @Column(nullable = false)
    private String phone; // phone: Required

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.NOT_PROCESSED; // status: Enum with default value

    @Column(nullable = false)
    @Builder.Default
    private boolean isPaidBefore = false; // isPaidBefore: Default to false

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountFromUser; // amountFromUser: Required, min 0

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountFromStore; // amountFromStore: Required, min 0

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToStore; // amountToStore: Required, min 0

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToGD; // amountToGD: Required, min 0

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Date createdAt = new Date(); // createdAt: Auto-generated

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date updatedAt = new Date(); // updatedAt: Auto-updated

    @PrePersist // Trước khi ánh xạ thực thể vào dataabse, gọi phương thức này cập nhật lại thời gian khởi tạo
    			// Đảm bảo tính toàn vẹn, khi đối tượng cha được persisted on persistence context
    			// Các đối tượng con(tham chiếu) cũng được tương tự
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate // Tương tự PrePersist
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

