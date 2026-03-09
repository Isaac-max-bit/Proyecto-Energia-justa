import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PowerPlantComponent } from './power-plant.component';

describe('PowerPlantComponent', () => {
  let component: PowerPlantComponent;
  let fixture: ComponentFixture<PowerPlantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PowerPlantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PowerPlantComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
