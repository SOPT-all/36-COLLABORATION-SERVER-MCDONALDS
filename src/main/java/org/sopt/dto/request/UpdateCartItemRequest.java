package org.sopt.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateCartItemRequest(
        @NotNull Integer amount
) {
}
