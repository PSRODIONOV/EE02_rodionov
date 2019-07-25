package be.utils;

public class FlowerFilter {
    private String minPrice;
    private String maxPrice;
    private String name;

    public FlowerFilter(){
    }

    public FlowerFilter(String minPrice, String maxPrice, String name){
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
    }

    @Override
    public String toString() {
        String criteria = "";
        if(!minPrice.isEmpty() && !maxPrice.isEmpty()){
            criteria += "f.price between " + minPrice + " and " + maxPrice;
        }
        else if(!minPrice.isEmpty() && maxPrice.isEmpty()){
            criteria += "f.price >= "+minPrice;
        } else if(minPrice.isEmpty() && !maxPrice.isEmpty()){
            criteria += "f.price <= "+maxPrice;
        }
        if(!name.isEmpty()){
            if(!criteria.isEmpty()) {
                criteria += " and ";
            }
            criteria += "f.nameFlower like "+ "'%"+name+"%'";
        }
        if(criteria.isEmpty()){
            return criteria;
        }
        return "where " + criteria;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
