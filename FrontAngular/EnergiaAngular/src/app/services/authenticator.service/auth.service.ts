import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

saveCredentials(user: string, pass: string) {
    localStorage.clear(); 
    const authString = btoa(`${user}:${pass}`); 
    localStorage.setItem('auth', authString);
    localStorage.setItem('user', user);
}

  getAuthHeader() {
    return localStorage.getItem('auth');
  }

    isAdmin(): boolean {
    return localStorage.getItem('user') === 'admin@energy.com';
    }

  isLoggedIn(): boolean {
    return !!this.getAuthHeader();
  }

  logout() {
    localStorage.clear();
  }
}