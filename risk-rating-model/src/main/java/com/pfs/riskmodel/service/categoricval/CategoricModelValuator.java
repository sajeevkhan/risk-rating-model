package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.domain.RiskModelTemplate;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class CategoricModelValuator {


    // Execute Categoric Risk Valuation - Generic
    public RiskModelTemplate executeCategoricValuation (RiskModelTemplate riskModelTemplate) {


        switch (riskModelTemplate.getModelCategory().getCode()) {
            case 1:
                Renewables_BuildPhase_Valuator renewables_buildPhase_valuator =
                        new Renewables_BuildPhase_Valuator();
                riskModelTemplate=renewables_buildPhase_valuator.valuate(riskModelTemplate);
                break;
            case 2:
                Renewables_OperationalPhase_Valuator renewables_operationalPhase_valuator =
                        new Renewables_OperationalPhase_Valuator();
                riskModelTemplate = renewables_operationalPhase_valuator.valuate(riskModelTemplate);
                break;
            case 3:
                InfraTransmission_BuildPhase_Valuator infraTransmission_buildPhase_valuator =
                        new InfraTransmission_BuildPhase_Valuator();
                riskModelTemplate = infraTransmission_buildPhase_valuator.valuate(riskModelTemplate);
                break;
            case 4:
                InfraTransmission_OperationalPhase_Valuator infraTransmission_operationalPhase_valuator =
                        new InfraTransmission_OperationalPhase_Valuator();
                riskModelTemplate = infraTransmission_operationalPhase_valuator.valuate(riskModelTemplate);

                break;
            case 5:
                InfraRoadHAM_BuildPhase_Valuator infraRoadHAM_buildPhase_valuator =
                        new InfraRoadHAM_BuildPhase_Valuator();
                riskModelTemplate = infraRoadHAM_buildPhase_valuator.valuate(riskModelTemplate);
                break;
            case 6:
                InfraRoadHAM_OperationalPhase_Valuator infraRoadHAM_operationalPhase_valuator =
                        new InfraRoadHAM_OperationalPhase_Valuator();
                riskModelTemplate = infraRoadHAM_operationalPhase_valuator.valuate(riskModelTemplate);
                break;
            case 7:
                InfraRoadToll_BuildPhase_Valuator infraRoadToll_buildPhase_valuator =
                        new InfraRoadToll_BuildPhase_Valuator();
                riskModelTemplate = infraRoadToll_buildPhase_valuator.valuate(riskModelTemplate);

                break;
            case 8:
                InfraRoadToll_OperationalPhase_Valuator infraRoadToll_operationalPhase_valuator =
                        new InfraRoadToll_OperationalPhase_Valuator();
                riskModelTemplate = infraRoadToll_operationalPhase_valuator.valuate(riskModelTemplate);

                break;
            case 9:
                break;

        }



        return riskModelTemplate;
    }


    // Execute Parental Notchup Valuation for specific categories
    public RiskModelTemplate executeParentalNotchUpValuation (RiskModelTemplate riskModelTemplate, Integer numberOfNotchesAfterParental) {

        switch (riskModelTemplate.getModelCategory().getCode()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                InfraRoadHAM_BuildPhase_Valuator infraRoadHAM_buildPhase_valuator = new InfraRoadHAM_BuildPhase_Valuator();
                riskModelTemplate = infraRoadHAM_buildPhase_valuator.setGradeAfterParentalNotchup(riskModelTemplate,numberOfNotchesAfterParental);
                break;
            case 6: //TODO Change METHOD
                InfraRoadHAM_OperationalPhase_Valuator infraRoadHAM_operationalPhase_valuator = new InfraRoadHAM_OperationalPhase_Valuator();
                riskModelTemplate = infraRoadHAM_operationalPhase_valuator.valuate(riskModelTemplate);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;

        }


        return riskModelTemplate;
    }


    // Execute Account Conduct Redistribution
    public  RiskModelTemplate executeAccountConductWeightageReDistribution(RiskModelTemplate riskModelTemplate) {


        switch (riskModelTemplate.getModelCategory().getCode()) {
            case 1:
            case 2:
                return riskModelTemplate;
            case 3:
            case 4:
            case 5:
            case 6:
                InfraRoadHAM_Transmission_AccountConduct_Redistribution infraRoadHAM_transmission_accountConduct_redistribution =
                        new InfraRoadHAM_Transmission_AccountConduct_Redistribution();
                riskModelTemplate = infraRoadHAM_transmission_accountConduct_redistribution.executeAccountConductWeightageReDistrobution(riskModelTemplate);
            case 7:
            case 8:
                InfraRoadToll_AccountConduct_Redistribution infraRoadToll_accountConduct_redistribution =
                        new InfraRoadToll_AccountConduct_Redistribution();
                riskModelTemplate = infraRoadToll_accountConduct_redistribution.executeAccountConductWeightageReDistrobution(riskModelTemplate);
                break;
            case 9:
                 return riskModelTemplate;
        }


        return  riskModelTemplate;
    }


}

