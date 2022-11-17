package com.residence.api.dataTranferObjects;

import com.residence.api.models.StatePayment;

import lombok.Data;

@Data
public class HouseDTO {
    String tower;
    StatePayment status = StatePayment.NORMAL;
}
