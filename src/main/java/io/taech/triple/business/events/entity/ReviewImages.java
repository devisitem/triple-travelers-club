package io.taech.triple.business.events.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@DiscriminatorValue("IMAGE")
public class ReviewImages extends CommonFile {

    @Id
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String imageLink;

    private LocalDateTime createTime;

}
