import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { Medicine } from '../_models';
import { MedicineService } from '../_services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    loading = false;
    medicines : Medicine[];

    constructor(private medicineService: MedicineService) { }

    ngOnInit() {
        this.loading = true;
        this.medicineService.getAll().pipe(first()).subscribe(medicines => {
            this.medicines = medicines;
            this.loading = false;
        });
    }
}