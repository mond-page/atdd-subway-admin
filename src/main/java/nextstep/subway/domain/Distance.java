package nextstep.subway.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Distance {

    @Column
    private Long distance;

    protected Distance() {
    }

    private Distance(Long distance) {
        this.distance = distance;
    }

    public static Distance of(Long distance) {
        validateInvalidValue(distance);
        return new Distance(distance);
    }

    private static void validateInvalidValue(Long distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("거리는 0 이하 일 순 없습니다.");
        }
    }

    public void minus(Distance other) {
        if (isMoreThanOrEqual(other)) {
            throw new IllegalArgumentException("추가하려는 구간의 거리는 현재 거리보다 작아야 합니다.");
        }
        this.distance -= other.distance;
    }

    private boolean isMoreThanOrEqual(Distance other) {
        return this.distance <= other.distance;
    }

    public Long getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Distance distance1 = (Distance) o;
        return Objects.equals(distance, distance1.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }
}
