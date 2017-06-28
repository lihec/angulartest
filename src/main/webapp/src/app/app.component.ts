import { Component, OnInit } from '@angular/core';
import { Http }       from '@angular/http';
import 'rxjs/add/operator/toPromise';

import '../assets/css/styles.css';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private http:Http)  {}
  ngOnInit(): void {
    this.http.get('/hello')
        .toPromise()
        .then(response => console.log("res="+response));

  }

}
