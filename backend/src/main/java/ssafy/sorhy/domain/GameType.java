package ssafy.sorhy.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public enum GameType {
    SINGLE, MULTI
}
