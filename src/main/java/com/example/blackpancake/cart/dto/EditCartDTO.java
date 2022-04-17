package com.example.blackpancake.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class EditCartDTO {
    private long cartId;

    @Positive(message = "수량은 1개 이상 입력 가능합니다.")
    private int amount;
}
