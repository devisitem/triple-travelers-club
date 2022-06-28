package io.taech.triple.business.events.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TriplePlaceInfo {

    @Id
    @Column(nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String placeName;

    private LocalDateTime createTime;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TravelersReview> reviews = new ArrayList<>();

}
