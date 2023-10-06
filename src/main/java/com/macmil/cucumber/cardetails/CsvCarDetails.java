package com.macmil.cucumber.cardetails;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * POJO for storing car details found in output file
 */
@Data
public class CsvCarDetails {
    @CsvBindByName(column = "MAKE_MODEL")
    private String makeModel;

    @CsvBindByName(column = "VARIANT_REG")
    private String variantReg;

    @CsvBindByName(column = "YEAR_BODY")
    private String yearBody;
}
