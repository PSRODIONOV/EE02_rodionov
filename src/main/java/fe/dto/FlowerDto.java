package fe.dto;

public class FlowerDto {

    private Long id_flower;
    private String name_flower;
    private Double price;

    public FlowerDto(){
        this.price = 0.0;
    }

    public Long getId_flower() {
        return id_flower;
    }

    public void setId_flower(Long id_flower) {
        this.id_flower = id_flower;
    }

    public String getName_flower() {
        return name_flower;
    }

    public void setName_flower(String name_flower) {
        this.name_flower = name_flower;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
