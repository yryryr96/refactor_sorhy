package ssafy.sorhy.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
