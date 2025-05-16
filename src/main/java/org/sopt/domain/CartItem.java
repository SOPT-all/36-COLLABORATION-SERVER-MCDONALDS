package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItem {

    @Id
    @GeneratedValue
    private Long cartItemId;

    @Column(nullable = false)
    private Boolean isSet;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public static CartItem create(
            Boolean isSet,
            Integer amount,
            User user,
            Menu menu
    ) {
        return CartItem.builder()
                .isSet(isSet)
                .amount(amount)
                .user(user)
                .menu(menu)
                .build();
    }

}