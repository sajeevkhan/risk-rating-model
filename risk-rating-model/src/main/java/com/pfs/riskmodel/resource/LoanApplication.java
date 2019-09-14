package com.pfs.riskmodel.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class LoanApplication {

    protected UUID id;

    protected Long version;

    protected LocalDate createdOn;

    protected LocalTime createdAt;

    protected String createdByUserName;

    protected LocalDate changedOn;

    protected LocalTime changedAt;

    protected String changedByUserName;

    private EnquiryNo enquiryNo;

    private LocalDate loanEnquiryDate;

    private Long loanEnquiryId;

    private String loanContractId;

    @Type(type = "uuid-char")
    private UUID loanApplicant;

    /**
     * 001     Power
     * 002     Railways
     * 003     Urban Infra
     * 004     Roads
     * 005     Ports
     * 006     Oil & Gas
     * 007     Corporates
     * 008     Infrastructure
     * 009     Others
     */
    private String loanClass;

    /**
     * 01   Thermal - Coal
     * 02   Thermal - Ignite
     * 03   Thermal - Gas
     * 04   Hydro
     * 05   Renewable - Solar
     * 06   Renewable - Wind
     * 07   Renewable - Biomass
     * 08   Renewable - Small Hydro
     * 09   EPC Contractors
     * 10   Coal Mining
     * 11   Power Transmission
     * 12   Railway Siding
     * 13   Ports
     * 14   Corporate
     * 15   Renovation & Modernisation
     * 16   Others
     */
    private String projectType;

    /**
     * 01      Sole Lending
     * 02      Consor. Lending
     * 03      Lead FI
     * 04      Underwriting
     * 05      Lead FI & Synd.
     * 06      Syndication
     */
    private String financingType;

    /**
     * D - Debt
     * E - Equity
     */
    private String assistanceType;

    private Double projectCapacity;

    /**
     * KW-KiloWatt
     * MW-MegaWatt
     */
    private String projectCapacityUnit;


    @Size(max = 100)
    private String projectLocationState;

    @Size(max = 100)
    private String projectDistrict;

    private Integer tenorYear;

    private Integer tenorMonth;

    private Double projectCost;


    private Double projectDebtAmount;

    // Loan Revised Sanction Amount
    private Double loanRevisedSanctionAmount;

    //Loan Current Contract Capital Amt.
    private Double loanCurrentContractAmount;

    // Loan Contract Amount
    private Double loanContractAmount;

    // Loan Disbursed Amount
    private Double loanDisbursedAmount;


    private Double equity;

    private String projectAmountCurrency = "INR";

    private Double expectedSubDebt;

    // Application Capital
    private Double pfsDebtAmount;

    private Double pfsSubDebtAmount;

    @Size(max = 100)
    private String loanPurpose;

    @Size(max = 100)
    private String leadFIName;

    private Double leadFILoanAmount;

    private Double expectedInterestRate;

    private LocalDate scheduledCOD;

    @Size(max = 100)
    private String promoterName;

    private Double promoterNetWorthAmount;

    private Double promoterPATAmount;

    @Size(max = 100)
    private String promoterAreaOfBusinessNature;

    private String rating;

    private String promoterKeyDirector;

    private String keyPromoter;


    /**
     * 01- Created
     * 02-Changed
     * 03-Submitted
     * 04-Approved
     * 05-Cancelled
     * 06-Rejected
     */
    private Integer technicalStatus;

    /**
     * 01-Enquiry Stage
     * 02-ICC ApprovalStage
     * 03-Apprisal Stage
     * 04-Board Approval Stage
     * 05-Loan Documenation Stage
     * 06-Loan Disbursement Stage
     * 07-Approved
     * 08-Rejected
     */
    private Integer functionalStatus;

    /**
     * 01-Approved
     * 02-Rejected
     */
    private Integer finalDecisionStatus;

    @Size(max = 100)
    private String rejectionReason;

    private LocalDateTime rejectionDate;

    private LocalDate decisionDate;

    private String userBPNumber;

    private String groupCompany;

    private String productCode;

    private String busPartnerNumber;

    private String projectName;


    private String projectDepartmentInitiator;

    private String monitoringDepartmentInitiator;

    private String riskDepartmentInitiator;

    private Integer postedInSAP;

    private String contactBranchAddress;
    private String contactDesignation;
    private String contactDepartment;
    private String contactTelePhone;
    private String contactLandLinePhone;
    private String contactEmail;
    private String contactFaxNumber;

    @JsonCreator
    public LoanApplication(@JsonProperty("id") UUID id,
                           @JsonProperty("version") Long version,
                           @JsonProperty("createdOn") LocalDate createdOn,
                           @JsonProperty("createdAt") LocalTime createdAt,
                           @JsonProperty("createdByUserName") String createdByUserName,
                           @JsonProperty("changedOn") LocalDate changedOn,
                           @JsonProperty("changedAt") LocalTime changedAt,
                           @JsonProperty("changedByUserName") String changedByUserName,
                           @JsonProperty("enquiryNo") EnquiryNo enquiryNo,
                           @JsonProperty("loanEnquiryDate") LocalDate loanEnquiryDate,
                           @JsonProperty("loanEnquiryId") Long loanEnquiryId,
                           @JsonProperty("loanContractId") String loanContractId,
                           @JsonProperty("loanApplicant") UUID loanApplicant,
                           @JsonProperty("loanClass") String loanClass,
                           @JsonProperty("projectType") String projectType,
                           @JsonProperty("financingType") String financingType,
                           @JsonProperty("assistanceType") String assistanceType,
                           @JsonProperty("projectCapacity") Double projectCapacity,
                           @JsonProperty("projectCapacityUnit") String projectCapacityUnit,
                           @JsonProperty("projectLocationState") String projectLocationState,
                           @JsonProperty("projectDistrict") String projectDistrict,
                           @JsonProperty("tenorYear") Integer tenorYear,
                           @JsonProperty("tenorMonth") Integer tenorMonth,
                           @JsonProperty("projectCost") Double projectCost,
                           @JsonProperty("projectDebtAmount") Double projectDebtAmount,

                           @JsonProperty("loanRevisedSanctionAmount") Double loanRevisedSanctionAmount,
                           @JsonProperty("loanContractAmount") Double loanContractAmount,
                           @JsonProperty("loanCurrentContractAmount") Double loanCurrentContractAmount,
                           @JsonProperty("loanDisbursedAmount") Double loanDisbursedAmount,

                           @JsonProperty("equity") Double equity,
                           @JsonProperty("projectAmountCurrency") String projectAmountCurrency,
                           @JsonProperty("expectedSubDebt") Double expectedSubDebt,
                           @JsonProperty("pfsDebtAmount") Double pfsDebtAmount,
                           @JsonProperty("pfsSubDebtAmount") Double pfsSubDebtAmount,
                           @JsonProperty("loanPurpose") String loanPurpose,
                           @JsonProperty("leadFIName") String leadFIName,
                           @JsonProperty("leadFILoanAmount") Double leadFILoanAmount,
                           @JsonProperty("expectedInterestRate") Double expectedInterestRate,
                           @JsonProperty("scheduledCOD") LocalDate scheduledCOD,
                           @JsonProperty("promoterName") String promoterName,
                           @JsonProperty("promoterNetWorthAmount") Double promoterNetWorthAmount,
                           @JsonProperty("promoterPATAmount") Double promoterPATAmount,
                           @JsonProperty("promoterAreaOfBusinessNature") String promoterAreaOfBusinessNature,
                           @JsonProperty("rating") String rating,
                           @JsonProperty("promoterKeyDirector") String promoterKeyDirector,
                           @JsonProperty("keyPromoter") String keyPromoter,
                           @JsonProperty("technicalStatus") Integer technicalStatus,
                           @JsonProperty("functionalStatus") Integer functionalStatus,
                           @JsonProperty("finalDecisionStatus") Integer finalDecisionStatus,
                           @JsonProperty("rejectionReason") String rejectionReason,
                           @JsonProperty("rejectionDate") LocalDateTime rejectionDate,
                           @JsonProperty("decisionDate") LocalDate decisionDate,
                           @JsonProperty("userBPNumber") String userBPNumber,
                           @JsonProperty("groupCompany") String groupCompany,
                           @JsonProperty("productCode") String productCode,
                  //         @JsonProperty("busPartnerNumber") String busPartnerNumber,
                           @JsonProperty("projectName") String projectName,
                           @JsonProperty("projectDepartmentInitiator") String projectDepartmentInitiator ,
                           @JsonProperty("monitoringDepartmentInitiator") String monitoringDepartmentInitiator,
                           @JsonProperty("riskDepartmentInitiator") String riskDepartmentInitiator,
                           @JsonProperty("postedInSAP") Integer postedInSAP,

                           @JsonProperty("contactBranchAddress") String contactBranchAddress,
                           @JsonProperty("contactDesignation") String contactDesignation,
                           @JsonProperty("contactDepartment") String contactDepartment,
                           @JsonProperty("contactTelePhone") String contactTelePhone,
                           @JsonProperty("contactLandLinePhone") String contactLandLinePhone,
                           @JsonProperty("contactEmail") String contactEmail,
                           @JsonProperty("contactFaxNumber") String contactFaxNumber



                            ) {
        this.id = id;
        this.version = version;
        this.createdOn = createdOn;
        this.createdAt = createdAt;
        this.createdByUserName = createdByUserName;
        this.changedOn = changedOn;
        this.changedAt = changedAt;
        this.changedByUserName = changedByUserName;
        this.enquiryNo = enquiryNo;
        this.loanEnquiryDate = loanEnquiryDate;
        this.loanEnquiryId = loanEnquiryId;
        this.loanContractId = loanContractId;
        this.loanApplicant = loanApplicant;
        this.loanClass = loanClass;
        this.projectType = projectType;
        this.financingType = financingType;
        this.assistanceType = assistanceType;
        this.projectCapacity = projectCapacity;
        this.projectCapacityUnit = projectCapacityUnit;
        this.projectLocationState = projectLocationState;
        this.projectDistrict = projectDistrict;
        this.tenorYear = tenorYear;
        this.tenorMonth = tenorMonth;
        this.projectCost = projectCost;
        this.projectDebtAmount = projectDebtAmount;

        this.loanRevisedSanctionAmount = loanRevisedSanctionAmount;
        this.loanContractAmount = loanContractAmount;
        this.loanCurrentContractAmount = loanCurrentContractAmount;
        this.loanDisbursedAmount = loanDisbursedAmount;

        this.equity = equity;
        this.projectAmountCurrency = projectAmountCurrency;
        this.expectedSubDebt = expectedSubDebt;
        this.pfsDebtAmount = pfsDebtAmount;
        this.pfsSubDebtAmount = pfsSubDebtAmount;
        this.loanPurpose = loanPurpose;
        this.leadFIName = leadFIName;
        this.leadFILoanAmount = leadFILoanAmount;
        this.expectedInterestRate = expectedInterestRate;
        this.scheduledCOD = scheduledCOD;
        this.promoterName = promoterName;
        this.promoterNetWorthAmount = promoterNetWorthAmount;
        this.promoterPATAmount = promoterPATAmount;
        this.promoterAreaOfBusinessNature = promoterAreaOfBusinessNature;
        this.rating = rating;
        this.promoterKeyDirector = promoterKeyDirector;
        this.keyPromoter = keyPromoter;
        this.technicalStatus = technicalStatus;
        this.functionalStatus = functionalStatus;
        this.finalDecisionStatus = finalDecisionStatus;
        this.rejectionReason = rejectionReason;
        this.rejectionDate = rejectionDate;
        this.decisionDate = decisionDate;
        this.userBPNumber = userBPNumber;
        this.groupCompany = groupCompany;
        this.productCode = productCode;
       // this.busPartnerNumber = busPartnerNumber;
        this.projectName = projectName;
        this.projectDepartmentInitiator  = projectDepartmentInitiator;
        this.monitoringDepartmentInitiator = monitoringDepartmentInitiator;
        this.riskDepartmentInitiator = riskDepartmentInitiator;
        this.postedInSAP = postedInSAP;


        this.contactBranchAddress = contactBranchAddress;
        this.contactDesignation = contactDesignation;
        this.contactDepartment = contactDepartment;
        this.contactTelePhone = contactTelePhone;
        this.contactLandLinePhone =contactLandLinePhone;
        this.contactEmail = contactEmail;
        this.contactFaxNumber = contactFaxNumber;

    }


    public EnquiryNo getEnquiryNo() {
        return this.enquiryNo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public LocalDate getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(LocalDate changedOn) {
        this.changedOn = changedOn;
    }

    public LocalTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalTime changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedByUserName() {
        return changedByUserName;
    }

    public void setChangedByUserName(String changedByUserName) {
        this.changedByUserName = changedByUserName;
    }

    public void setEnquiryNo(EnquiryNo enquiryNo) {
        this.enquiryNo = enquiryNo;
    }

    public LocalDate getLoanEnquiryDate() {
        return loanEnquiryDate;
    }

    public void setLoanEnquiryDate(LocalDate loanEnquiryDate) {
        this.loanEnquiryDate = loanEnquiryDate;
    }

    public Long getLoanEnquiryId() {
        return loanEnquiryId;
    }

    public void setLoanEnquiryId(Long loanEnquiryId) {
        this.loanEnquiryId = loanEnquiryId;
    }

    public String getLoanContractId() {
        return loanContractId;
    }

    public void setLoanContractId(String loanContractId) {
        this.loanContractId = loanContractId;
    }

    public UUID getLoanApplicant() {
        return loanApplicant;
    }

    public void setLoanApplicant(UUID loanApplicant) {
        this.loanApplicant = loanApplicant;
    }

    public String getLoanClass() {
        return loanClass;
    }

    public void setLoanClass(String loanClass) {
        this.loanClass = loanClass;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getFinancingType() {
        return financingType;
    }

    public void setFinancingType(String financingType) {
        this.financingType = financingType;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public Double getProjectCapacity() {
        return projectCapacity;
    }

    public void setProjectCapacity(Double projectCapacity) {
        this.projectCapacity = projectCapacity;
    }

    public String getProjectCapacityUnit() {
        return projectCapacityUnit;
    }

    public void setProjectCapacityUnit(String projectCapacityUnit) {
        this.projectCapacityUnit = projectCapacityUnit;
    }

    public String getProjectLocationState() {
        return projectLocationState;
    }

    public void setProjectLocationState(String projectLocationState) {
        this.projectLocationState = projectLocationState;
    }

    public String getProjectDistrict() {
        return projectDistrict;
    }

    public void setProjectDistrict(String projectDistrict) {
        this.projectDistrict = projectDistrict;
    }

    public Integer getTenorYear() {
        return tenorYear;
    }

    public void setTenorYear(Integer tenorYear) {
        this.tenorYear = tenorYear;
    }

    public Integer getTenorMonth() {
        return tenorMonth;
    }

    public void setTenorMonth(Integer tenorMonth) {
        this.tenorMonth = tenorMonth;
    }

    public Double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Double projectCost) {
        this.projectCost = projectCost;
    }

    public Double getProjectDebtAmount() {
        return projectDebtAmount;
    }

    public void setProjectDebtAmount(Double projectDebtAmount) {
        this.projectDebtAmount = projectDebtAmount;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public String getProjectAmountCurrency() {
        return projectAmountCurrency;
    }

    public void setProjectAmountCurrency(String projectAmountCurrency) {
        this.projectAmountCurrency = projectAmountCurrency;
    }

    public Double getExpectedSubDebt() {
        return expectedSubDebt;
    }

    public void setExpectedSubDebt(Double expectedSubDebt) {
        this.expectedSubDebt = expectedSubDebt;
    }

    public Double getPfsDebtAmount() {
        return pfsDebtAmount;
    }

    public Double getLoanRevisedSanctionAmount() {
        return loanRevisedSanctionAmount;
    }

    public Double getLoanContractAmount() {
        return loanContractAmount;
    }

    public Double getLoanCurrentContractAmount() {
        return loanCurrentContractAmount;
    }

    public Double getLoanDisbursedAmount() {
        return loanDisbursedAmount;
    }

    public void setPfsDebtAmount(Double pfsDebtAmount) {
        this.pfsDebtAmount = pfsDebtAmount;
    }

    public Double getPfsSubDebtAmount() {
        return pfsSubDebtAmount;
    }

    public void setPfsSubDebtAmount(Double pfsSubDebtAmount) {
        this.pfsSubDebtAmount = pfsSubDebtAmount;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLeadFIName() {
        return leadFIName;
    }

    public void setLeadFIName(String leadFIName) {
        this.leadFIName = leadFIName;
    }

    public Double getLeadFILoanAmount() {
        return leadFILoanAmount;
    }

    public void setLeadFILoanAmount(Double leadFILoanAmount) {
        this.leadFILoanAmount = leadFILoanAmount;
    }

    public Double getExpectedInterestRate() {
        return expectedInterestRate;
    }

    public void setExpectedInterestRate(Double expectedInterestRate) {
        this.expectedInterestRate = expectedInterestRate;
    }

    public LocalDate getScheduledCOD() {
        return scheduledCOD;
    }

    public void setScheduledCOD(LocalDate scheduledCOD) {
        this.scheduledCOD = scheduledCOD;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public Double getPromoterNetWorthAmount() {
        return promoterNetWorthAmount;
    }

    public void setPromoterNetWorthAmount(Double promoterNetWorthAmount) {
        this.promoterNetWorthAmount = promoterNetWorthAmount;
    }

    public Double getPromoterPATAmount() {
        return promoterPATAmount;
    }

    public void setPromoterPATAmount(Double promoterPATAmount) {
        this.promoterPATAmount = promoterPATAmount;
    }

    public String getPromoterAreaOfBusinessNature() {
        return promoterAreaOfBusinessNature;
    }

    public void setPromoterAreaOfBusinessNature(String promoterAreaOfBusinessNature) {
        this.promoterAreaOfBusinessNature = promoterAreaOfBusinessNature;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPromoterKeyDirector() {
        return promoterKeyDirector;
    }

    public void setPromoterKeyDirector(String promoterKeyDirector) {
        this.promoterKeyDirector = promoterKeyDirector;
    }

    public String getKeyPromoter() {
        return keyPromoter;
    }

    public void setKeyPromoter(String keyPromoter) {
        this.keyPromoter = keyPromoter;
    }

    public Integer getTechnicalStatus() {
        return technicalStatus;
    }

    public void setTechnicalStatus(Integer technicalStatus) {
        this.technicalStatus = technicalStatus;
    }

    public Integer getFunctionalStatus() {
        return functionalStatus;
    }

    public void setFunctionalStatus(Integer functionalStatus) {
        this.functionalStatus = functionalStatus;
    }

    public Integer getFinalDecisionStatus() {
        return finalDecisionStatus;
    }

    public void setFinalDecisionStatus(Integer finalDecisionStatus) {
        this.finalDecisionStatus = finalDecisionStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(LocalDateTime rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public LocalDate getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDate decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getUserBPNumber() {
        return userBPNumber;
    }

    public void setUserBPNumber(String userBPNumber) {
        this.userBPNumber = userBPNumber;
    }

    public String getGroupCompany() {
        return groupCompany;
    }

    public void setGroupCompany(String groupCompany) {
        this.groupCompany = groupCompany;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBusPartnerNumber() {
        return busPartnerNumber;
    }

    public void setBusPartnerNumber(String busPartnerNumber) {
        this.busPartnerNumber = busPartnerNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDepartmentInitiator() {
        return projectDepartmentInitiator;
    }

    public void setProjectDepartmentInitiator(String projectDepartmentInitiator) {
        this.projectDepartmentInitiator = projectDepartmentInitiator;
    }

    public String getMonitoringDepartmentInitiator() {
        return monitoringDepartmentInitiator;
    }

    public void setMonitoringDepartmentInitiator(String monitoringDepartmentInitiator) {
        this.monitoringDepartmentInitiator = monitoringDepartmentInitiator;
    }

    public String getRiskDepartmentInitiator() {
        return riskDepartmentInitiator;
    }

    public void setRiskDepartmentInitiator(String riskDepartmentInitiator) {
        this.riskDepartmentInitiator = riskDepartmentInitiator;
    }

    public void setLoanRevisedSanctionAmount(Double loanRevisedSanctionAmount) {
        this.loanRevisedSanctionAmount = loanRevisedSanctionAmount;
    }

    public void setLoanCurrentContractAmount(Double loanCurrentContractAmount) {
        this.loanCurrentContractAmount = loanCurrentContractAmount;
    }

    public void setLoanContractAmount(Double loanContractAmount) {
        this.loanContractAmount = loanContractAmount;
    }

    public void setLoanDisbursedAmount(Double loanDisbursedAmount) {
        this.loanDisbursedAmount = loanDisbursedAmount;
    }

    public Integer getPostedInSAP() {
        return postedInSAP;
    }

    public void setPostedInSAP(Integer postedInSAP) {
        this.postedInSAP = postedInSAP;
    }

    public String getContactBranchAddress() {
        return contactBranchAddress;
    }

    public void setContactBranchAddress(String contactBranchAddress) {
        this.contactBranchAddress = contactBranchAddress;
    }

    public String getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(String contactDesignation) {
        this.contactDesignation = contactDesignation;
    }

    public String getContactDepartment() {
        return contactDepartment;
    }

    public void setContactDepartment(String contactDepartment) {
        this.contactDepartment = contactDepartment;
    }

    public String getContactTelePhone() {
        return contactTelePhone;
    }

    public void setContactTelePhone(String contactTelePhone) {
        this.contactTelePhone = contactTelePhone;
    }

    public String getContactLandLinePhone() {
        return contactLandLinePhone;
    }

    public void setContactLandLinePhone(String contactLandLinePhone) {
        this.contactLandLinePhone = contactLandLinePhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactFaxNumber() {
        return contactFaxNumber;
    }

    public void setContactFaxNumber(String contactFaxNumber) {
        this.contactFaxNumber = contactFaxNumber;
    }
}
