import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EnquiryApplicationModel } from 'app/main/model/enquiryApplication.model';

@Injectable()
export class LoanEnquiryService {

    public enquirySearchList: BehaviorSubject<any>;

    selectedLoanApplicaton: EnquiryApplicationModel;

    selectedLoanApplicationId: BehaviorSubject<string>;

    testEnquiries: any = [
        {
           'loanApplication': {
              'id': '01f2281d-25de-4c8f-9aef-8e6b2b2c9233',
              'createdOn': '2018-11-26',
              'createdAt': '20:42:46',
              'createdByUserName': 'dennypjose12@gmail.com',
              'enquiryNo': {
                 'id': 7
              },
              'loanContractId': '10003001',
              'loanApplicant': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'loanClass': '007',
              'projectType': '14',
              'financingType': '03',
              'assistanceType': '',
              'projectLocationState': '',
              'projectDistrict': '',
              'tenorYear': 1,
              'tenorMonth': 2,
              'expectedSubDebt': 21.0,
              'pfsDebtAmount': 12.0,
              'pfsSubDebtAmount': 21.0,
              'loanPurpose': 'TEst',
              'leadFIName': '',
              'promoterName': '',
              'promoterAreaOfBusinessNature': '',
              'rating': '',
              'promoterKeyDirector': '',
              'functionalStatus': 1,
              'projectName': 'ABC Wind Mill Project '
           },
           'partner': {
              'id': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'partyRole': 'TR0100',
              'partyName1': 'LeanThoughts Technologies Pvt Ltd',
              'partyName2': 'Sajeev',
              'contactPersonName': 'Denny',
              'addressLine1': '',
              'addressLine2': 'Veldmaarschalk Montgomerylaan',
              'street': '',
              'city': 'Eindhoven',
              'state': '',
              'postalCode': '5612BE',
              'country': 'IN',
              'email': 'dennypjose12@gmail.com',
              'contactNumber': '+31684799745',
              'groupCompany': 'LeanThoughts Technologies Pvt Ltd',
              'userName': 'dennypjose12@gmail.com',
              'pan': 'APVPD5425L',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': '067b4d8c-529f-483d-8192-8c74644f2bb4',
              'createdOn': '2018-11-26',
              'createdAt': '21:12:33',
              'createdByUserName': 'dennypjose12@gmail.com',
              'enquiryNo': {
                 'id': 9
              },
              'loanContractId': '10003002',
              'loanApplicant': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'loanClass': '007',
              'projectType': '14',
              'financingType': '05',
              'assistanceType': 'D',
              'projectCapacity': 9999.0,
              'projectLocationState': 'Assam',
              'projectDistrict': 'Coimbatore',
              'tenorYear': 95,
              'tenorMonth': 11,
              'projectCost': 858.0,
              'projectDebtAmount': 899.0,
              'equity': 999.0,
              'expectedSubDebt': 25.0,
              'pfsDebtAmount': 552.0,
              'pfsSubDebtAmount': 5.0,
              'loanPurpose': 'Thermal Power',
              'leadFIName': 'SBO',
              'leadFILoanAmount': 88.0,
              'expectedInterestRate': 25.0,
              'scheduledCOD': '2018-11-25',
              'promoterName': 'Denny',
              'promoterNetWorthAmount': 556.0,
              'promoterPATAmount': 5556.0,
              'promoterAreaOfBusinessNature': 'Thermal ',
              'rating': '99',
              'promoterKeyDirector': 'Denny',
              'functionalStatus': 9,
              'projectName': 'XYZ Biomass Project'
           },
           'partner': {
              'id': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'partyRole': 'TR0100',
              'partyName1': 'LeanThoughts Technologies Pvt Ltd',
              'partyName2': 'Sajeev',
              'contactPersonName': 'Denny',
              'addressLine1': '',
              'addressLine2': 'Veldmaarschalk Montgomerylaan',
              'street': '',
              'city': 'Eindhoven',
              'state': '',
              'postalCode': '5612BE',
              'country': 'IN',
              'email': 'dennypjose12@gmail.com',
              'contactNumber': '+31684799745',
              'groupCompany': 'LeanThoughts Technologies Pvt Ltd',
              'userName': 'dennypjose12@gmail.com',
              'pan': 'APVPD5425L',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': '198f445a-4fa0-47a5-bc18-8c1be70a45b9',
              'createdOn': '2018-11-26',
              'createdAt': '20:55:47',
              'createdByUserName': 'dennypjose12@gmail.com',
              'enquiryNo': {
                 'id': 8
              },
              'loanContractId': '10003003',
              'loanApplicant': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'loanClass': '005',
              'projectType': '04',
              'financingType': '05',
              'assistanceType': '',
              'projectLocationState': '',
              'projectDistrict': '',
              'tenorYear': 99,
              'tenorMonth': 11,
              'pfsDebtAmount': 99.0,
              'loanPurpose': ' ',
              'leadFIName': '',
              'promoterName': 'Denny',
              'promoterNetWorthAmount': 750.0,
              'promoterPATAmount': 200.0,
              'promoterAreaOfBusinessNature': 'Thermal Power',
              'rating': '999999999999999999999999999999999',
              'promoterKeyDirector': 'Denny',
              'functionalStatus': 1,
              'projectName': 'AM Infra Toll Road & Co'
           },
           'partner': {
              'id': 'd5a00aae-20fd-41cf-bede-496c0c93d5d8',
              'partyRole': 'TR0100',
              'partyName1': 'LeanThoughts Technologies Pvt Ltd',
              'partyName2': 'Sajeev',
              'contactPersonName': 'Denny',
              'addressLine1': '',
              'addressLine2': 'Veldmaarschalk Montgomerylaan',
              'street': '',
              'city': 'Eindhoven',
              'state': '',
              'postalCode': '5612BE',
              'country': 'IN',
              'email': 'dennypjose12@gmail.com',
              'contactNumber': '+31684799745',
              'groupCompany': 'LeanThoughts Technologies Pvt Ltd',
              'userName': 'dennypjose12@gmail.com',
              'pan': 'APVPD5425L',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': '3bb50bed-b67b-4f58-82ac-c2e9967318b7',
              'createdOn': '2018-11-25',
              'createdAt': '11:24:29',
              'createdByUserName': 'ltp@gmail.com',
              'enquiryNo': {
                 'id': 4
              },
              'loanContractId': '10003004',
              'loanApplicant': '511400eb-46fc-4ed3-9be4-4c4ed3be3a85',
              'loanClass': '005',
              'projectType': '14',
              'financingType': '03',
              'assistanceType': 'D',
              'projectCapacity': 10.2,
              'projectLocationState': 'Karnataka',
              'projectDistrict': 'mysore',
              'tenorYear': 1,
              'tenorMonth': 3,
              'projectCost': 12.2,
              'projectDebtAmount': 123.0,
              'equity': 12.33,
              'expectedSubDebt': 102.0,
              'pfsDebtAmount': 5.0,
              'pfsSubDebtAmount': 100.0,
              'loanPurpose': 'Loan required for the purpose of Road Constructions',
              'leadFIName': 'SBI ',
              'leadFILoanAmount': 19.2,
              'expectedInterestRate': 19.0,
              'scheduledCOD': '2018-11-25',
              'promoterName': 'Promoter LTP',
              'promoterNetWorthAmount': 122.3,
              'promoterPATAmount': 12.33,
              'promoterAreaOfBusinessNature': 'Power',
              'rating': 'AAA',
              'promoterKeyDirector': 'LTP key',
              'functionalStatus': 1,
              'projectName': 'LTP Solar Plants '
           },
           'partner': {
              'id': '511400eb-46fc-4ed3-9be4-4c4ed3be3a85',
              'partyRole': 'TR0100',
              'partyName1': 'New LTP',
              'partyName2': 'LTP Second Name',
              'contactPersonName': 'James',
              'addressLine1': '1233',
              'addressLine2': '123',
              'street': 'Koldingweg',
              'city': 'Dadar',
              'state': 'Dadar and Nagar Haveli',
              'postalCode': '560085',
              'country': 'IN',
              'email': 'ltp@gmail.com',
              'contactNumber': '9886496849',
              'groupCompany': 'LTP new Grp',
              'userName': 'ltp@gmail.com',
              'pan': 'ABNPG9212E',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': '70393592-1ea1-4df8-a372-471573397e00',
              'createdOn': '2018-11-24',
              'createdAt': '11:27:37',
              'createdByUserName': 'admin',
              'enquiryNo': {
                 'id': 2
              },
              'loanContractId': '10003162',
              'loanApplicant': '9273d432-9367-48d0-9334-91afd0ec7c5c',
              'loanClass': '007',
              'projectType': '04',
              'financingType': '03',
              'assistanceType': 'D',
              'projectCapacity': 11.2,
              'projectLocationState': 'Karnataka',
              'projectDistrict': 'Bangalore',
              'tenorYear': 5,
              'tenorMonth': 6,
              'projectCost': 1.23,
              'projectDebtAmount': 1.23,
              'equity': 2.23,
              'pfsDebtAmount': 5.0,
              'loanPurpose': 'Loan required for the purpose of Road Constructions',
              'leadFIName': 'SBI Capital',
              'leadFILoanAmount': 10.2,
              'expectedInterestRate': 12.0,
              'scheduledCOD': '2018-11-05',
              'promoterName': 'BP Sol',
              'promoterNetWorthAmount': 10.23,
              'promoterPATAmount': 10.22,
              'promoterAreaOfBusinessNature': 'Power',
              'rating': 'AAA',
              'promoterKeyDirector': 'BP',
              'functionalStatus': 2,
              'userBPNumber': '50000284',
              'productCode': '301',
              'busPartnerNumber': '10000872',
              'projectName': 'NL Hydro Power'
           },
           'partner': {
              'id': '9273d432-9367-48d0-9334-91afd0ec7c5c',
              'partyRole': 'TR0100',
              'partyName1': 'Dotline Solutions',
              'partyName2': '',
              'contactPersonName': 'B R Gopinath',
              'addressLine1': 'Address1',
              'addressLine2': '',
              'street': 'Koldingweg',
              'city': 'Bangalore',
              'state': '',
              'postalCode': '560085',
              'country': 'IN',
              'email': 'admin@gmail.com',
              'contactNumber': '9886496849',
              'groupCompany': '',
              'userName': 'admin',
              'pan': 'ABNPG9212E',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': '8bf29a67-b1b5-41ba-9deb-f5a1e8eba529',
              'createdOn': '2018-11-24',
              'createdAt': '11:40:31',
              'createdByUserName': 'power@pow.com',
              'enquiryNo': {
                 'id': 3
              },
              'loanContractId': '10003161',
              'loanApplicant': 'e8e0ed63-14d1-4f9f-b227-b1185b9128a2',
              'loanClass': '007',
              'projectType': '14',
              'financingType': '03',
              'assistanceType': 'D',
              'projectCapacity': 10.2,
              'projectLocationState': 'Karnataka',
              'projectDistrict': 'Bangalore',
              'tenorYear': 1,
              'tenorMonth': 1,
              'projectCost': 10.23,
              'projectDebtAmount': 102.3,
              'equity': 10.2,
              'expectedSubDebt': 10.23,
              'pfsDebtAmount': 5.0,
              'pfsSubDebtAmount': 10.99,
              'loanPurpose': 'Loan required for the purpose of Road Constructions',
              'leadFIName': 'SBI Capi',
              'leadFILoanAmount': 10.0,
              'expectedInterestRate': 10.2,
              'scheduledCOD': '2018-11-27',
              'promoterName': 'Ness Prom',
              'promoterNetWorthAmount': 10.3,
              'promoterPATAmount': 13.44,
              'promoterAreaOfBusinessNature': 'Power',
              'rating': 'AAA',
              'promoterKeyDirector': 'Ness Key Promoter',
              'functionalStatus': 2,
              'userBPNumber': '50000284',
              'productCode': '301',
              'busPartnerNumber': '10000871',
              'projectName': 'Radiant Energy'
           },
           'partner': {
              'id': 'e8e0ed63-14d1-4f9f-b227-b1185b9128a2',
              'partyRole': 'TR0100',
              'partyName1': 'Ness Power',
              'partyName2': '',
              'contactPersonName': 'B R Gopinath',
              'addressLine1': 'Address1',
              'addressLine2': '',
              'street': 'Koldingweg',
              'city': 'Bangalore',
              'state': '',
              'postalCode': '560085',
              'country': 'IN',
              'email': 'power@pow.com',
              'contactNumber': '9886496849',
              'groupCompany': 'Grp Ness',
              'userName': 'power@pow.com',
              'pan': 'ABNPG9212E',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': 'e75a1cdd-46ab-4b0a-8fcc-baee27d64085',
              'createdOn': '2018-11-25',
              'createdAt': '11:41:12',
              'createdByUserName': 'ltp@gmail.com',
              'enquiryNo': {
                 'id': 5
              },
              'loanContractId': '10003164',
              'loanApplicant': '511400eb-46fc-4ed3-9be4-4c4ed3be3a85',
              'loanClass': '007',
              'projectType': '03',
              'financingType': '03',
              'assistanceType': 'D',
              'projectCapacity': 12.3,
              'projectLocationState': 'Dadar and Nagar Haveli',
              'projectDistrict': 'Dadar',
              'tenorYear': 2,
              'tenorMonth': 2,
              'projectCost': 12.3,
              'projectDebtAmount': 12.3,
              'equity': 12.12,
              'expectedSubDebt': 12.45,
              'pfsDebtAmount': 5.0,
              'pfsSubDebtAmount': 23.4,
              'loanPurpose': 'Loan required for the purpose of Road Constructions',
              'leadFIName': 'HDFC',
              'leadFILoanAmount': 10.34,
              'expectedInterestRate': 10.0,
              'scheduledCOD': '2018-11-11',
              'promoterName': 'LTP Promoter',
              'promoterNetWorthAmount': 12.45,
              'promoterPATAmount': 103.45,
              'promoterAreaOfBusinessNature': 'Power Sector',
              'rating': 'AAA',
              'promoterKeyDirector': 'LTP Promoter Grp New',
              'functionalStatus': 2,
              'userBPNumber': '50000284',
              'productCode': '303',
              'busPartnerNumber': '10000874',
              'projectName': 'Wind Power Mill & Co'
           },
           'partner': {
              'id': '511400eb-46fc-4ed3-9be4-4c4ed3be3a85',
              'partyRole': 'TR0100',
              'partyName1': 'New LTP',
              'partyName2': 'LTP Second Name',
              'contactPersonName': 'James',
              'addressLine1': '1233',
              'addressLine2': '123',
              'street': 'Koldingweg',
              'city': 'Dadar',
              'state': 'Dadar and Nagar Haveli',
              'postalCode': '560085',
              'country': 'IN',
              'email': 'ltp@gmail.com',
              'contactNumber': '9886496849',
              'groupCompany': 'LTP new Grp',
              'userName': 'ltp@gmail.com',
              'pan': 'ABNPG9212E',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': 'ee79aed2-c8cb-4093-8fdf-e5f578c07287',
              'createdOn': '2018-11-25',
              'createdAt': '14:17:55',
              'createdByUserName': 'alonso@gmail.com',
              'enquiryNo': {
                 'id': 6
              },
              'loanContractId': '10003166',
              'loanApplicant': 'dc46e694-83f8-49b0-901b-f693911b2952',
              'loanClass': '007',
              'projectType': '04',
              'financingType': '03',
              'assistanceType': 'D',
              'projectCapacity': 10.23,
              'projectLocationState': 'Nagaland',
              'projectDistrict': 'Kohima',
              'tenorYear': 1,
              'tenorMonth': 3,
              'projectCost': 12.33,
              'projectDebtAmount': 12.33,
              'equity': 1.2,
              'expectedSubDebt': 10.2,
              'pfsDebtAmount': 14.33,
              'pfsSubDebtAmount': 14.33,
              'loanPurpose': 'Coal mining',
              'leadFIName': 'SBI Capital',
              'leadFILoanAmount': 10.23,
              'expectedInterestRate': 12.0,
              'scheduledCOD': '2018-11-25',
              'promoterName': 'Alsonso Promoter',
              'promoterNetWorthAmount': 102.22,
              'promoterPATAmount': 122.0,
              'rating': 'AAA',
              'promoterKeyDirector': 'Alonso Key Promoter',
              'functionalStatus': 2,
              'userBPNumber': '50000284',
              'productCode': '301',
              'busPartnerNumber': '10000876',
              'projectName': 'Venture Transmission Infra'
           },
           'partner': {
              'id': 'dc46e694-83f8-49b0-901b-f693911b2952',
              'partyRole': 'TR0100',
              'partyName1': 'Kohima Power',
              'partyName2': 'Corp',
              'contactPersonName': 'Fusos',
              'addressLine1': '12',
              'addressLine2': 'D 905, Springbeauty Apartment',
              'street': 'Kundalahalli,',
              'city': 'Bangalore',
              'state': 'Karnataka',
              'postalCode': '423233',
              'country': 'IN',
              'email': 'alonso@gmail.com',
              'contactNumber': '82323324',
              'groupCompany': 'Alonso Corp',
              'userName': 'alonso@gmail.com',
              'pan': 'AFSASAE',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        },
        {
           'loanApplication': {
              'id': 'eeeddd65-6131-4066-aaf8-51a4db39cabe',
              'createdOn': '2018-11-22',
              'createdAt': '20:21:56',
              'createdByUserName': 'power@pow.com',
              'enquiryNo': {
                 'id': 1
              },
              'loanApplicant': 'e8e0ed63-14d1-4f9f-b227-b1185b9128a2',
              'loanClass': '007',
              'projectType': '14',
              'financingType': '05',
              'assistanceType': 'D',
              'projectCapacity': 1.23,
              'projectLocationState': 'Karnataka',
              'projectDistrict': 'bangalore',
              'tenorYear': 5,
              'tenorMonth': 6,
              'projectCost': 1.2,
              'projectDebtAmount': 1.34,
              'equity': 2.5,
              'expectedSubDebt': 1.55,
              'pfsDebtAmount': 5.0,
              'pfsSubDebtAmount': 5.0,
              'loanPurpose': '01 32322332323',
              'leadFIName': 'SBI',
              'leadFILoanAmount': 1.34,
              'expectedInterestRate': 1.45,
              'scheduledCOD': '2018-11-19',
              'promoterName': 'Taylor Prom',
              'promoterNetWorthAmount': 1.23,
              'promoterPATAmount': 1.33,
              'rating': 'AAA',
              'promoterKeyDirector': 'Taylor & Co',
              'functionalStatus': 2,
              'userBPNumber': '50000284',
              'productCode': '301',
              'projectName': 'Sonic Transmssion'
           },
           'partner': {
              'id': 'e8e0ed63-14d1-4f9f-b227-b1185b9128a2',
              'partyRole': 'TR0100',
              'partyName1': 'Ness Power',
              'partyName2': '',
              'contactPersonName': 'B R Gopinath',
              'addressLine1': 'Address1',
              'addressLine2': '',
              'street': 'Koldingweg',
              'city': 'Bangalore',
              'state': '',
              'postalCode': '560085',
              'country': 'IN',
              'email': 'power@pow.com',
              'contactNumber': '9886496849',
              'groupCompany': 'Grp Ness',
              'userName': 'power@pow.com',
              'pan': 'ABNPG9212E',
              'handler': {
     
              },
              'hibernateLazyInitializer': {
     
              }
           }
        }
     ];
    
    /**
     * 
     * @param _http: HttpClient
     */
    constructor(private _http: HttpClient) {
    }

    /**
     * searchLoanEnquiries()
     * Fetches a list of loan applications based on the request parameters.
     * @param request: any
     */
    public searchLoanEnquiries(request: any): Observable<any> {
        console.log(request);
        let str = '/api/loanApplications/search?';
        if (request.projectName) {
            str+= 'projectName=' + request.projectName + '&';
        }
        if (request.loanNumberFrom) {
            str+= 'loanNumberFrom=' + request.loanNumberFrom + '&';
        }
        if (request.loanNumberTo) {
            str+= 'loanNumberTo=' + request.loanNumberTo;
        }
        return this._http.get<any>(str);
    }

}
