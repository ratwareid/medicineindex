import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup} from '@angular/forms';
import {AuthenticationService,MedicineService,AlertService } from '../_services';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.less']
})
export class DataComponent implements OnInit {
  dataForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private medicineservice: MedicineService,
    private alertService: AlertService
    ) { 
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/data']);
    }else{
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.dataForm = this.formBuilder.group({
      name: [''],
      disease: [''],
      description: [''],
      rulesofuse: ['']
    });
  }

  get f() { return this.dataForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.dataForm.invalid) {
        return;
    }

    this.loading = true;
    this.medicineservice.insert(this.dataForm.value)
        .pipe(first())
        .subscribe(
            data => {
                this.alertService.success('Submit successful', true);
                this.router.navigate(['/']);
            },
            error => {
                this.alertService.error(error);
                this.loading = false;
            });
  }

}
