package com.pfs.riskmodel.utils;

import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sajeev on 23-Dec-18.
 */
public class RiskSubFactorAttributesBuilder {

    public List<RiskSubFactorAttributeDTO> buildRiskSubFactorAttributes (List<RiskAttribute> riskAttributes) {

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributes = new ArrayList<>();

        int i = 1;
        for (RiskAttribute riskAttribute: riskAttributes) {

            RiskSubFactorAttributeDTO riskSubFactorAttributeDTO = new RiskSubFactorAttributeDTO();

            riskSubFactorAttributeDTO.setId(null);
            riskSubFactorAttributeDTO.setItemNo(i);
            riskSubFactorAttributeDTO.setDescription(riskAttribute.getDescription());
            riskSubFactorAttributeDTO.setScore(riskAttribute.getScore());
            riskSubFactorAttributeDTO.setWeightage(0D);
            riskSubFactorAttributeDTO.setIsSelected(false);
            riskSubFactorAttributes.add(riskSubFactorAttributeDTO);
            i++;
        }

//        riskSubFactorAttributes =  riskSubFactorAttributes.stream()
//                                        .sorted(Comparator.comparing(RiskSubFactorAttributeDTO::getItemNo))
//                                        .collect(Collectors.toSet());


        return riskSubFactorAttributes;
    }
}
