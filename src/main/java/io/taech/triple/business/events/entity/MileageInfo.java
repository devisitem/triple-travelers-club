package io.taech.triple.business.events.entity;

import io.taech.triple.common.util.Utils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Entity
@DynamicUpdate
@Table(schema = "travelers_mileage_info")
public class MileageInfo {

    @Id
    @Column(nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false, length = 36)
    private UUID userId;

    @Column(nullable = false, length = 11)
    private Integer mileage;

    private LocalDateTime createTime;

    private Predicate<MileageHistory> settleable() {
      return (his) ->
              (his.getUserId().equals(this.userId) && Utils.isNull(his.getDeleteTime()));
    }


    public void settledMileages(final List<MileageHistory> histories) {
        final Integer settled = histories.stream().filter(settleable())
                .mapToInt(his -> his.getMileage())
                .sum();
        setMileage(settled);
    }

    public Integer getMileages() {
        return this.mileage;
    }

    private void setMileage(final Integer mileage) {
        this.mileage = mileage;
    }
}
