package nextstep.subway.section.domain;

import nextstep.subway.common.BaseEntity;
import nextstep.subway.line.domain.Line;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;

@Entity
public class Section extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    private Distance distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    protected Section() {
    }

    public Section(Station upStation, Station downStation, int distance, Line line) {
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = new Distance(distance);
        this.line = line;
    }

    public Section(Station upStation, Station downStation, int distance) {
        this(upStation, downStation, distance, null);
    }

    public Long getId() {
        return id;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance.getValue();
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        isExistLine(line);
        this.line = line;
        this.line.addSection(this);
    }

    public void update(Station upStation, Station downStation, int distance) {
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance.plus(distance);
    }

    public boolean isPrevSection(Section section) {
        return upStation.equals(section.getDownStation());
    }

    public boolean isNextSection(Section section) {
        return downStation.equals(section.getUpStation());
    }

    private void isExistLine(Line line) {
        if (this.line != null && !this.line.equals(line)) {
            line.removeSection(this);
        }
    }
}
