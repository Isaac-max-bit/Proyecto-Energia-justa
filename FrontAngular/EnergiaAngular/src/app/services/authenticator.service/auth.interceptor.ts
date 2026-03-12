import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const authData = authService.getAuthHeader();

  if (authData) {
    // Si tenemos credenciales, las inyectamos como "Basic [código]"
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Basic ${authData}`
      }
    });
    return next(authReq);
  }

  return next(req);
};