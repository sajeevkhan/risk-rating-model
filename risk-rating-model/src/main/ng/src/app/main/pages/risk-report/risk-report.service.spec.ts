import { TestBed } from '@angular/core/testing';

import { RiskReportService } from './risk-report.service';

describe('RiskReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RiskReportService = TestBed.get(RiskReportService);
    expect(service).toBeTruthy();
  });
});
