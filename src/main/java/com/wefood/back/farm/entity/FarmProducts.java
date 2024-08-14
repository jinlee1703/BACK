package com.wefood.back.farm.entity;

import jakarta.persistence.*;
import com.wefood.back.product.entity.Product;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "farm_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FarmProducts {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId(value = "farmId")
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToOne
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public FarmProducts(Pk pk, Farm farm, Product product) {
        this.pk = pk;
        this.farm = farm;
        this.product = product;
    }

    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {
        @Column(name = "product_id")
        private Long productId;

        @Column(name = "farm_id")
        private Long farmId;

        @Builder
        public Pk(Long productId, Long farmId) {
            this.productId = productId;
            this.farmId = farmId;
        }
    }
}
