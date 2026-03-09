import { TestBed } from '@angular/core/testing';

import { EnergyTypeService } from './energy-type.service';

describe('EnergyTypeService', () => {
  let service: EnergyTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnergyTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
