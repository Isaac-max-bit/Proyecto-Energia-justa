import { Component } from '@angular/core';
import { UserService } from '../../../services/user.service/user.service';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-user.component',
  imports: [CommonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
    users: any[] = [];
    selecteduser : any;

    constructor(private UserSer: UserService, private changeP: ChangeDetectorRef){
            UserSer.getUsers().subscribe(data =>{
            this.users = data;
            console.log(this.users);
            this.changeP.detectChanges();
        });
    }

    onSelectedUser(id : number){
        if(id != 0){
            this.UserSer.getUserById(id).subscribe((details : any) =>{
                this.selecteduser = details;
                this.changeP.detectChanges();
            }

            )
        }
    }
}
