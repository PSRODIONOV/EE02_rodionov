package be.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeyPos implements Serializable {
    @Column(name = "id_order", nullable = false)
    private Long id_order;
    @Column (name = "id_flower", nullable = false)
    private Long id_flower;

    public KeyPos(){};

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_flower() {
        return id_flower;
    }

    public void setId_flower(Long id_flower) {
        this.id_flower = id_flower;
    }


}
