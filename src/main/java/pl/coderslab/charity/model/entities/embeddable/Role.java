package pl.coderslab.charity.model.entities.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Role implements Serializable{

    @Column(nullable = false)
    private String authority;

}
