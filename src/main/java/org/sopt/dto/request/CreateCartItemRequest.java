package org.sopt.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateCartItemRequest(
        @NotNull Boolean isSet,
        @NotNull Integer amount,
        @NotNull Long menuId
) {

}
