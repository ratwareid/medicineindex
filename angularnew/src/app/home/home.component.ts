import { Component } from '@angular/core';
import { first } from 'rxjs/operators';
import { FormBuilder, FormGroup} from '@angular/forms';
import { Medicine, Sickness } from '../_models';
import {AuthenticationService,MedicineService,AlertService } from '../_services';
import { Router } from '@angular/router';
import { getLocaleDateFormat } from '@angular/common';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    
    loading = false;
    submitted = false;
    medicines : Medicine[];
    sickness : Sickness[];
    p;
    homeForm : FormGroup;
    
    constructor(
        private medicineService: MedicineService,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,
        private router: Router,
        private formBuilder: FormBuilder
    ) { }

    ngOnInit() {
        this.homeForm = this.formBuilder.group({
            search: ['']
        });
        this.loading = true;
        this.getData();
    }

    onSubmit() {
        this.submitted = true;
        // stop here if form is invalid
        if (this.homeForm.invalid) {
            return;
        }
        this.loading = true;

        if (this.homeForm.value.search === ''){
            this.getData();
        }else{
            this.medicineService.search(this.homeForm.value.search)
            .pipe(first())
            .subscribe(
                data => {
                    this.medicines = data;
                    this.loading = false;
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
        }

        if (this.homeForm.value.search !== ''){
            this.medicineService.findsickness(this.homeForm.value.search)
            .pipe(first())
            .subscribe(
                data => {
                    this.sickness = data;
                    this.loading = false;
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
        }else{
            this.sickness = null;
        }
    }

    getData(){
        this.medicineService.getAll().pipe(first()).subscribe(medicine => {
            this.medicines = medicine;
            this.loading = false;
        });
    }
}