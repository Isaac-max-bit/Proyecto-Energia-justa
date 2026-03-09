import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnergyTypeComponent } from './energy-type.component';

describe('EnergyTypeComponent', () => {
  let component: EnergyTypeComponent;
  let fixture: ComponentFixture<EnergyTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnergyTypeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnergyTypeComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
