import { Component } from '@angular/core';
import { User } from './_models';
import { Router } from '@angular/router';
import { AuthenticationService } from './_services/';
import { Title }     from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'angularnew';
  currentUser: User;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private titleService: Title 
    ) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
        this.titleService.setTitle( "Mercubuana Medical Center" );
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
}
