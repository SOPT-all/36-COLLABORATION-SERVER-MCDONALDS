package org.sopt.domain;

import jakarta.persistence.*;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private String singleImgUrl;
    private String setImgUrl;
    private Integer singlePrice;
    private Integer setPrice;

    protected Menu() {
    }

    public Menu(String menuName, String singleImgUrl, String setImgUrl, Integer singlePrice, Integer setPrice) {
        this.menuName = menuName;
        this.singleImgUrl = singleImgUrl;
        this.setImgUrl = setImgUrl;
        this.singlePrice = singlePrice;
        this.setPrice = setPrice;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getSingleImgUrl() {
        return singleImgUrl;
    }

    public String getSetImgUrl() {
        return setImgUrl;
    }

    public Integer getSinglePrice() {
        return singlePrice;
    }

    public Integer getSetPrice() {
        return setPrice;
    }
}
