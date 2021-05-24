export class RiskReportModel {
    riskModelId: number;
    loanNumber: string;
    projectName: string;
    projectType: string;
    projectPhase: string;
    initiatingDepartment: string;
    loanContractAmount: number;
    totalLoanDisbursedAmount: number;
    initiator: string;
    createDate: Date;
    processDate: Date;
    finalRating: string;

    /**
     * constructor()
     * Initialize the object.
     * @param _riskReport
     */
    constructor( _riskReport: any) {
        this.riskModelId = _riskReport.riskModelId;
        this.loanNumber = _riskReport.loanNumber + '' || '';
        this.projectName = _riskReport.projectName + '' || '';
        this.projectType = _riskReport.projectType + '' || '';
        this.projectPhase = _riskReport.projectPhase+ '' || '';
        this.initiatingDepartment = _riskReport.initiatingDepartment+ '' || '';
        this.loanContractAmount = _riskReport.loanContractAmount ;
        this.totalLoanDisbursedAmount = _riskReport.totalLoanDisbursedAmount ;
        this.initiator = _riskReport.initiator+ '' || '';
        this.createDate = _riskReport.createDate;
        this.processDate = _riskReport.processDate;
        this.finalRating = _riskReport.finalRating+ '' || '';
    }
}