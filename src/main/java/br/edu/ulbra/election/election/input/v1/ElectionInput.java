package br.edu.ulbra.election.election.input.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Mod11Check;

import java.util.Collections;

@ApiModel(description = "Election Input Information")
public class ElectionInput {

    @ApiModelProperty(example = "2018", notes = "Election Year")
    private Integer year;
    @ApiModelProperty(example = "RS", notes = "State Code")
    private String stateCode;
    @ApiModelProperty(example = "Senator", notes = "Election Description")
    private String description;







    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
