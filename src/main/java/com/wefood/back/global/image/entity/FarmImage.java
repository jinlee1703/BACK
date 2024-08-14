package com.wefood.back.global.image.entity;

import com.wefood.back.farm.entity.Farm;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: FarmImage.
 *
 * @author JBum
 * @version 2024/08/13
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FarmImage {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("farmId")
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public FarmImage(Pk pk, Farm farm, Image image) {
        this.pk = pk;
        this.farm = farm;
        this.image = image;
    }


    @Embeddable
    @EqualsAndHashCode
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "farm_id")
        private Long farmId;

        @Column(name = "image_id")
        private Long imageId;

        @Builder
        public Pk(Long farmId, Long imageId) {
            this.farmId = farmId;
            this.imageId = imageId;
        }
    }
}