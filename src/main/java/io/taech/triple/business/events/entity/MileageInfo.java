package io.taech.triple.business.events.entity;

import io.taech.triple.common.util.Utils;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Getter
@Entity
@DynamicUpdate
@Table(name = "travelers_mileage_info")
public class MileageInfo {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private TripleUser user;

    @Column(nullable = false, length = 11)
    private Integer mileage;

    private LocalDateTime createTime;

    private Predicate<MileageHistory> settleable() {
      return (his) ->
              (his.getUser().getId().equals(this.getUser().getId()) && Utils.isNull(his.getDeleteTime()));
    }

    public void settledMileages() {
        final Integer settled = this.user.getHistories().stream().filter(settleable())
                .mapToInt(his -> his.getMileage())
                .sum();
        setMileage(settled);
    }

    private void setMileage(final Integer mileage) {
        this.mileage = mileage;
    }

    public void connectUser(final TripleUser tripleUser) {
        this.user = tripleUser;
    }
}
