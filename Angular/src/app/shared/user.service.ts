import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Response } from "@angular/http";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { User } from './user.model';

@Injectable()
export class UserService {
  readonly rootUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    const body: User = {
      UserName: user.UserName,
      Password: user.Password,
      Email: user.Email,
      FirstName: user.FirstName,
      LastName: user.LastName
    }
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','No-Auth':'True'});
    return this.http.post(this.rootUrl + '/api/register', body,{headers : reqHeader});
  }

  userAuthentication(userName, password) {
    const body: User = {
      UserName: userName,
      Password: password,
      Email: null,
      FirstName: null,
      LastName: null
    }
    //var data = "username=" + userName + "&password=" + password + "&grant_type=password";
    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/json','No-Auth':'True' });
    return this.http.post(this.rootUrl + '/api/login', body, { headers: reqHeader });
  }

}
