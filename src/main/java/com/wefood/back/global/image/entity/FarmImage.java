package com.wefood.back.global.image.entity;

import com.wefood.back.farm.entity.Farm;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: FarmImage.
 *
 * @author JBumLee
 * @version 2024/08/13
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "farm_images")
public class FarmImage{

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "farm_id", insertable = false, updatable = false)
    private Farm farm;

    @ManyToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    @Column
    private Integer sequence;

    @Column(nullable = false, name = "is_thumbnail")
    private Boolean isThumbnail;

    @Builder
    public FarmImage(Pk pk, Farm farm, Image image,Integer sequence, Boolean isThumbnail) {
        this.pk = pk;
        this.farm = farm;
        this.image = image;
        this.sequence = sequence;
        this.isThumbnail = isThumbnail;
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