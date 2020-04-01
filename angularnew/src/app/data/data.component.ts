import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup} from '@angular/forms';
import {AuthenticationService } from '../_services';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.less']
})
export class DataComponent implements OnInit {
  dataForm: FormGroup;

  constructor(private router: Router,private authenticationService: AuthenticationService,) { 
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/data']);
    }else{
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
  }

  get f() { return this.dataForm.controls; }

}
