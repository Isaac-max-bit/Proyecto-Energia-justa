import { Component,OnInit } from '@angular/core';
import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service/user.service';

@Component({
  selector: 'app-user.component',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {}
/* export class UserComponent implements OnInit{

  users: User[]=[];
  
  newUser: User =new User();

  constructor(private userService: UserService){}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers2().subscribe(data => {
      this.users=data;
    });
  }
  
  saveUser() {
    this.userService.addUser(this.newUser).subscribe(data => {
      this.loadUsers();
      this.newUser = new User();
    });
  }

} */
